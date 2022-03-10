package com.example.solveryapp_mvp.presenter

import android.os.Build
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
            view.showProgress()
            val products = repository.load()//список, в который добавлять элементы

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
            view.setContent(productsViewState)
            view.hideProgress()
           //   view.showContent(products)
        } catch (e: Throwable) {
            view.hideProgress()
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

    override fun onDelete(productViewState: ProductViewState) {
        repository.delete(mapToProduct(productViewState))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun addProduct(productViewState: ProductViewState) {
        val products = repository.load()
        products.add(mapToProduct(productViewState))
        reload() //обновить список
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
        view.hideError()
        load()
    }

    companion object {
        fun create(storeActivity: StoreActivity, repository: StoreRepository) =
            StorePresenter(storeActivity, repository)

    }
}