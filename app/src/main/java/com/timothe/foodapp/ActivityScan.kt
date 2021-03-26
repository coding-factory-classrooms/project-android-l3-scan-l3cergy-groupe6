package com.timothe.foodapp


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.timothe.foodapp.ViewModel.ActivityScanViewModel
import com.timothe.foodapp.databinding.ActivityScanBinding


class ActivityScan : AppCompatActivity() {
    private val model: ActivityScanViewModel by viewModels()
    private lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ScanButton.setOnClickListener{
            val scanner = IntentIntegrator(this)

            scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            scanner.setBeepEnabled(false)


            scanner.initiateScan()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                        .show()

                    val intent = Intent(this@ActivityScan,MainActivity::class.java)
                    intent.putExtra("codeBare",result.contents)
                    startActivity(intent)

                }

            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

}