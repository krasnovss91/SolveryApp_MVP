package com.example.solveryapp_mvp

interface StoreContract {

    interface View{

        fun showProgress()

        fun hideProgress()

        fun showError()

        fun hideError()

        fun hideContent()

        fun showContent(content: List<ProductViewState>)

        fun showContent(show: Boolean)

        fun showError(show: Boolean)

    }

    interface Presenter{
        fun load()
        fun reload()

    }

    interface Repository{
        fun load():List<Product>

    }



}