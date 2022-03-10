package com.example.solveryapp_mvp

import com.example.solveryapp_mvp.entity.Product
import com.example.solveryapp_mvp.entity.ProductViewState

interface StoreContract {

    interface View{

        fun showProgress()

        fun hideProgress()

        fun showError()

        fun hideError()

        fun hideContent()

        fun setContent(content: List<ProductViewState>)

        fun showContent(show: Boolean)

        fun showError(show: Boolean)


    }

    interface Presenter{
        fun load()
        fun reload()
        fun onDelete(productViewState: ProductViewState)
    }

    interface Repository{
        //fun load():List<Product>
        fun load():MutableList<Product>
        fun delete(product: Product)
        fun reload()

    }

}