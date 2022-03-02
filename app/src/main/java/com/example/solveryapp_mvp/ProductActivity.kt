package com.example.solveryapp_mvp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.OffsetDateTime

class ProductActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val name = findViewById<EditText>(R.id.nameEdit)
        val producer = findViewById<EditText>(R.id.producerEdit)
        val cost = findViewById<EditText>(R.id.costEdit)

        val saveProduct = findViewById<Button>(R.id.saveProduct)

        saveProduct.setOnClickListener {
            val product = ProductViewState(
                R.drawable.ic_launcher_background,
                name.text.toString(),
                producer.text.toString(),
                cost.text.toString().toInt(),
                Generator.generateId(),
                OffsetDateTime.now()
            )
            val intent = Intent()

            intent.putExtra(PRODUCT, product)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

}