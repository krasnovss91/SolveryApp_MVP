package com.example.solveryapp_mvp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val name = findViewById<EditText>(R.id.nameEdit1)
        val producer = findViewById<EditText>(R.id.producerEdit1)
        val cost = findViewById<EditText>(R.id.costEdit1)

        val editButton = findViewById<Button>(R.id.saveProduct1)
        val product =
            intent.getParcelableExtra<Product>(PRODUCT)


        name.setText(product?.name)
        producer.setText(product?.producer)
        cost.setText(product?.cost.toString())

        val image = product?.avatar

        val intent = Intent(this, MainActivity::class.java)

        editButton.setOnClickListener {
            val result = image?.let {
                Product(
                    it,
                    name.text.toString(),
                    producer.text.toString(),
                    cost.text.toString().toInt(),
                    product.id
                )
            }
            intent.putExtra(PRODUCT, result)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}