package com.timothe.foodapp.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    //@GET("3124480182647")
    @GET("{barcode}")

    fun getData(@Path("barcode")barcode: String): Call<MyData>


}