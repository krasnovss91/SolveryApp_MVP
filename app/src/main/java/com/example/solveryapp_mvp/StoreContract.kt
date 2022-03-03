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

        fun showContent(content: List<Product>)

        fun showContent(show: Boolean)

        fun showError(show: Boolean)


    }

    interface Presenter{
        fun load()
        fun reload()

    }

    interface Repository{
        fun load():List<Product>
        fun delete(productViewState: ProductViewState)
        fun reload()

    }

}