package com.example.solveryapp_mvp

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.solveryapp_mvp.Generator.generateId


const val PRODUCT = "KEY_PRODUCT"
const val REQUEST_CODE_ADD = 101
const val REQUEST_CODE_EDIT = 102

object Generator {
    private var id: Int = 0

    fun generateId(): Int {
        id += 1
        return id
    }
}

class StoreActivity : AppCompatActivity(), OnProductSelected, OnProductDeleted {

    private val adapter = ProductAdapter(this, this)

    /*
val productList = mutableListOf(
    Product(R.drawable.ic_launcher_background, "Картофель", "ООО Интегра", 18, generateId()),
    Product(R.drawable.ic_launcher_foreground, "Чай", "ИП Абрамян А.Г.", 9, generateId()),
    Product(R.drawable.ic_launcher_background, "Яйца", "с.Зелёное", 22, generateId()),
    Product(R.drawable.ic_launcher_foreground, "Молоко", "с.Зелёное", 20, generateId()),
    Product(
        R.drawable.ic_launcher_background,
        "Макароны",
        "Тольяттинский хлебозавод",
        15,
        generateId()
    )
)
     */
    lateinit var productList: RecyclerView
    lateinit var addButton: Button
    lateinit var progress:ProgressBar
    lateinit var errorTitle: TextView
    lateinit var reloadButton: Button

    private val presenter by lazy {
        //StorePresenter.create(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.productList).apply {
            layoutManager = LinearLayoutManager(this@StoreActivity)
            adapter = this@StoreActivity.adapter
            this@StoreActivity.adapter.setProducts(productList)
            
        }


        val addButton = findViewById<Button>(R.id.addProduct)


        val saveIntent = Intent(this, ProductActivity::class.java)

        addButton.setOnClickListener {
            startActivityForResult(saveIntent, REQUEST_CODE_ADD)
        }
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }
        when (requestCode) {
            REQUEST_CODE_ADD -> {
                val product = data.getParcelableExtra<Product>(PRODUCT) ?: return
                productList.add(product)
                adapter.setProducts(productList)
            }
            REQUEST_CODE_EDIT -> {
                val product = data.getParcelableExtra<Product>(PRODUCT) ?: return
                val oldProduct = productList.find { it.id == product.id } ?: return
                val position = productList.indexOf(oldProduct)
                productList[position] = product
                adapter.setProducts(productList)
            }
        }

    }


    override fun onSelect(productViewState: ProductViewState) {
        val editIntent = Intent(this, EditActivity::class.java)
        editIntent.putExtra(PRODUCT, productViewState)
        startActivityForResult(editIntent, REQUEST_CODE_EDIT)
    }


    override fun onDelete(productViewState: ProductViewState) {
        productList.remove(productViewState)
        //presenter.delete
    }

}