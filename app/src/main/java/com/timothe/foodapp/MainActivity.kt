package com.timothe.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.timothe.foodapp.API.ApiInterface
import com.timothe.foodapp.API.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://world.openfoodfacts.org/api/v0/product/"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMyData()


    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val code = intent.getStringExtra("codeBare")
        val retroftData = retrofitBuilder.getData("$code")

        retroftData.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                myStringBuilder.append(responseBody.code)
                myStringBuilder.append("\n")
                myStringBuilder.append(responseBody.product.product_name)
                myStringBuilder.append("\n")
                myStringBuilder.append(responseBody.product.ingredients_tags)
                myStringBuilder.append("\n")
                myStringBuilder.append(responseBody.product.image_small_url)


                val text = findViewById<TextView>(R.id.textApi)
                text.text = myStringBuilder
                Log.d("MainActivity", "onResponse: $responseBody")
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })
    }
}