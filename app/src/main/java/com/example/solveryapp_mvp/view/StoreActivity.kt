package com.example.solveryapp_mvp.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.solveryapp_mvp.*
import com.example.solveryapp_mvp.entity.Product
import com.example.solveryapp_mvp.entity.ProductViewState
import com.example.solveryapp_mvp.presenter.StorePresenter
import com.example.solveryapp_mvp.repository.StoreRepository
import com.example.solveryapp_mvp.view.adapter.OnProductDeleted
import com.example.solveryapp_mvp.view.adapter.OnProductSelected
import com.example.solveryapp_mvp.view.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_main.*


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

class StoreActivity : AppCompatActivity(), OnProductSelected, OnProductDeleted, StoreContract.View {

    private val adapter = ProductAdapter(this, this)


    lateinit var productList: RecyclerView
    lateinit var addButton: Button
    lateinit var progress: ProgressBar
    lateinit var errorTitle: TextView
    lateinit var reloadButton: Button

    private val repository by lazy {
        StoreRepository.create()
    }

    private val presenter by lazy {
        StorePresenter.create(this, repository)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SetUpViews()
        presenter.load()


        val addButton = findViewById<Button>(R.id.addProduct)
     //   progress = findViewById(R.id.progress)
        errorTitle = findViewById(R.id.errorTitle)
        reloadButton = findViewById(R.id.reload)


        val saveIntent = Intent(this, ProductActivity::class.java)

        addButton.setOnClickListener {
            startActivityForResult(saveIntent, REQUEST_CODE_ADD)
        }

        reloadButton.setOnClickListener {
            presenter.reload()
        }
    }

    private fun SetUpViews() {
        findViewById<RecyclerView>(R.id.productList).apply {
            layoutManager = LinearLayoutManager(this@StoreActivity)
            adapter = this@StoreActivity.adapter
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
        if (data == null) {
            return
        }
        when (requestCode) {//реализовать реакцию на эти коды состояний
            REQUEST_CODE_ADD -> {
                //activity ничего не должно знать о преобразованиях, просто вызвать соответствующий метод presenter'а
                val product = data.getParcelableExtra<ProductViewState>(PRODUCT) ?: return
                presenter.addProduct(product)
            }
            REQUEST_CODE_EDIT -> {
                val product = data.getParcelableExtra<ProductViewState>(PRODUCT)?: return
                presenter.updateProducts(product)
            }
        }

    }


    override fun onSelect(productViewState: ProductViewState) {
        val editIntent = Intent(this, EditActivity::class.java)
        editIntent.putExtra(PRODUCT, productViewState)
        startActivityForResult(editIntent, REQUEST_CODE_EDIT)
    }


    override fun onDelete(productViewState: ProductViewState) {
        presenter.onDelete(productViewState)
    }

    override fun showProgress() {
        progress.isVisible = true
    }

    override fun hideProgress() {
        progress.isVisible = true
    }


    override fun showError() {
        showError(true)
    }


    override fun hideError() {
        showError(false)
    }

    override fun hideContent() {
        showContent(false)
    }

    override fun setContent(content: List<ProductViewState>) {
        showContent(true)
        adapter.setProducts(content)
    }

    override fun showContent(show: Boolean) {
        productList.isVisible = show
        addButton.isVisible = show
    }

    override fun showError(show: Boolean) {
        errorTitle.isVisible = show
        reloadButton.isVisible = show
    }


}