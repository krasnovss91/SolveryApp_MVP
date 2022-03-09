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
            val products = repository.load()

            val productsViewState: List<ProductViewState> = products.map {
                ProductViewState(//задать маппинг. Пройтись по списку и смапить каждый элемент
                /*  products.forEach {

                  }
                 */
                )
            }
            view.setContent(productsViewState)
            view.hideProgress()
            //  view.showContent(products)
        } catch (e: Throwable) {
            view.hideProgress()
            view.showError()
        }

    }
    private fun mapToProduct(productViewState: ProductViewState):Product{
       return Product(productViewState.avatar,productViewState.name,productViewState.producer, productViewState.cost,
        productViewState.id,productViewState.offsetDateTime)
    }

    override fun onDelete(productViewState: ProductViewState) {
        repository.delete(mapToProduct(productViewState))
    }
/*
  private  fun productMapper(productsViewState: List<ProductViewState>): List<Product> {
        val products = productsViewState.map { productViewState ->
            Product(
                productViewState.id,
                productViewState.name,
                productViewState.producer,
                productViewState.cost,
                productViewState.avatar,
                productViewState.offsetDateTime
            )
        }
        return products

    }

 */

    @RequiresApi(Build.VERSION_CODES.O)
    override fun reload() {
        view.hideError()
        load()
    }

    companion object {
        fun create(storeActivity: StoreActivity) = StoreRepository()
    }
}