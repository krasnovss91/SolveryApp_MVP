package com.example.solveryapp_mvp

interface StoreContract {

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun hideError()

    fun hideContent()

    fun showContent(content: List<ProductViewState>)

    fun showContent(show: Boolean)

    fun showError(show: Boolean)

}