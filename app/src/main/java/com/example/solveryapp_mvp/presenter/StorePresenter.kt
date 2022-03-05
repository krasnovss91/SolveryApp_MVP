package com.example.solveryapp_mvp.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.solveryapp_mvp.R
import com.example.solveryapp_mvp.StoreContract
import com.example.solveryapp_mvp.entity.ProductViewState
import com.example.solveryapp_mvp.repository.StoreRepository
import com.example.solveryapp_mvp.view.Generator
import com.example.solveryapp_mvp.view.StoreActivity
import java.time.OffsetDateTime

class StorePresenter(
    private val view: StoreContract.View,
    private val repository: StoreContract.Repository
) : StoreContract.Presenter {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun load() {
        try {
            view.showProgress()
            val products = repository.load()
            //мапится таким образом
            val productsViewState: List<ProductViewState> = products.map {
                ProductViewState(
                    R.drawable.ic_launcher_background, "Картофель", "ООО Интегра", 18,
                    Generator.generateId(),
                    OffsetDateTime.now(),
                )
                ProductViewState(
                    R.drawable.ic_launcher_background, "Яйца", "с.Зелёное", 22, Generator.generateId(),
                    OffsetDateTime.now()
                )
                ProductViewState(
                    R.drawable.ic_launcher_foreground, "Чай", "ИП Абрамян А.Г.", 9, Generator.generateId(),
                    OffsetDateTime.now()
                )
                ProductViewState(
                    R.drawable.ic_launcher_foreground, "Молоко", "с.Зелёное", 20, Generator.generateId(),
                    OffsetDateTime.now()
                )
                ProductViewState(
                    R.drawable.ic_launcher_background, "Макароны", "Тольяттинский хлебозавод", 15,
                    Generator.generateId(),
                    OffsetDateTime.now()
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

    fun productMapper(productsViewState: List<ProductViewState>){
        val products = productsViewState.map {  }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun reload() {
        view.hideError()
        load()
    }

    companion object {
        fun create(storeActivity: StoreActivity) = StoreRepository()
    }
}