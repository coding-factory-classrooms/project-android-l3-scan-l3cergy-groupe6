package com.timothe.foodapp


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator


class ActivityScan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        val button = findViewById<Button>(R.id.ScanButton)
        button.setOnClickListener{
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
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }


}