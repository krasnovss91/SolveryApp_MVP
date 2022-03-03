package com.example.solveryapp_mvp.presenter

import com.example.solveryapp_mvp.StoreContract
import com.example.solveryapp_mvp.repository.StoreRepository
import com.example.solveryapp_mvp.view.StoreActivity

class StorePresenter(
    private val view: StoreContract.View,
    private val repository: StoreContract.Repository
) : StoreContract.Presenter {
    override fun load() {
        try {
            view.showProgress()
            val products = repository.load()
            view.hideProgress()
            view.showContent(products)
        } catch (e: Throwable) {
            view.hideProgress()
            view.showError()
        }

    }

    override fun reload() {
        view.hideError()
        load()
    }

    companion object{
        fun create(storeActivity: StoreActivity) = StoreRepository()
    }
}