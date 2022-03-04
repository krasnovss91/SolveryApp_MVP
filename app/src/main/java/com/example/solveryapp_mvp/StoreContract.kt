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

        fun setContent(content: List<ProductViewState>)//view должен работать только с ProductViewState

        fun showContent(show: Boolean)

        fun showError(show: Boolean)


    }

    interface Presenter{
        fun load()
        fun reload()

    }

    interface Repository{//здесь только Product
        fun load():List<Product>
        fun delete(product: Product)
        fun reload()

    }

}