package com.example.solveryapp_mvp.presenter

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.solveryapp_mvp.StoreContract
import com.example.solveryapp_mvp.entity.Product
import com.example.solveryapp_mvp.entity.ProductViewState
import com.example.solveryapp_mvp.repository.StoreRepository
import com.example.solveryapp_mvp.view.StoreActivity

class StorePresenter(
    private val view: StoreContract.View,
    private val repository: StoreContract.Repository
) : StoreContract.Presenter {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun load() {
        try {
            Log.d("Presenter-load", "Работает метод Load")
            Log.d("hideContent1", "Работает HideContent")
            view.hideContent()
            Log.d("showProgress_1", "Работает showProgress")
            view.showProgress()

            Log.d("RepositoryLoad", "Работает метод Repository Load")
            val products = repository.load()

            // Handler(Looper.getMainLooper()).postDelayed({
            Log.d("ViewState", "Product View State")
            val productsViewState: List<ProductViewState> = products.map { product ->
                ProductViewState(
                    product.avatar,
                    product.name,
                    product.producer,
                    product.cost,
                    product.id,
                    product.offsetDateTime
                )
            }
            Log.d("SetContent", "Работает SetContent- ProductViewState")
            view.setContent(productsViewState)
            view.hideProgress()
            view.showContent(true)
            //  view.showContent(products)
            //  })
        } catch (e: Throwable) {
            Log.d("HideProgress_1", "Работает метод HideProgress_1")
            view.hideProgress()
            Log.d("ShowError", "Работает метод ShowError")
            view.showError()
        }

    }

    private fun mapToProduct(productViewState: ProductViewState): Product {
        return Product(
            productViewState.avatar,
            productViewState.name,
            productViewState.producer,
            productViewState.cost,
            productViewState.id,
            productViewState.offsetDateTime
        )
    }

    fun mapToViewState(products: MutableList<Product>): List<ProductViewState> {

        val productsViewState: List<ProductViewState> = products.map { product ->
            ProductViewState(
                product.avatar,
                product.name,
                product.producer,
                product.cost,
                product.id,
                product.offsetDateTime
            )

        }
        return productsViewState
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDelete(productViewState: ProductViewState) {
        Log.d("Delete2", "внутри delete presenter")
        repository.delete(mapToProduct(productViewState))
        reload()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun addProduct(productViewState: ProductViewState) {
        val products = repository.load()
        products.add(mapToProduct(productViewState))
        reload()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun updateProducts(productViewState: ProductViewState) {//берём элемент из списка, обновляем его, и обновляем список
        val products = repository.load()
        val newProduct = mapToProduct(productViewState)
        val oldProduct = products.find { it.id == newProduct.id } ?: return
        val position = products.indexOf(oldProduct)
        products[position] = newProduct

        reload()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun reload() {
        Log.d("Presenter-reload", "Работает метод Reload")
        view.hideError()
        load()
    }

    companion object {
        fun create(storeActivity: StoreActivity, repository: StoreRepository) =
            StorePresenter(storeActivity, repository)

    }
}