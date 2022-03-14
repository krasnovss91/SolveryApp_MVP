package com.example.solveryapp_mvp.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.solveryapp_mvp.view.Generator
import com.example.solveryapp_mvp.R
import com.example.solveryapp_mvp.StoreContract
import com.example.solveryapp_mvp.entity.Product
import com.example.solveryapp_mvp.entity.ProductViewState
import java.lang.RuntimeException
import java.time.OffsetDateTime

class StoreRepository : StoreContract.Repository {

    var i = 2

    @RequiresApi(Build.VERSION_CODES.O)
    val productList = mutableListOf(
        Product(
            R.drawable.ic_launcher_background, "Картофель", "ООО Интегра", 18,
            Generator.generateId(),
            OffsetDateTime.now()
        ),
        Product(
            R.drawable.ic_launcher_foreground, "Чай", "ИП Абрамян А.Г.", 9, Generator.generateId(),
            OffsetDateTime.now()
        ),
        Product(
            R.drawable.ic_launcher_background, "Яйца", "с.Зелёное", 22, Generator.generateId(),
            OffsetDateTime.now()
        ),
        Product(
            R.drawable.ic_launcher_foreground, "Молоко", "с.Зелёное", 20, Generator.generateId(),
            OffsetDateTime.now()
        ),
        Product(
            R.drawable.ic_launcher_background, "Макароны", "Тольяттинский хлебозавод", 15,
            Generator.generateId(),
            OffsetDateTime.now()
        )
    )

    override fun load(): MutableList<Product> {
        if (i % 2 == 0) {
            i++
            throw RuntimeException()
        }
        return productList
    }

    override fun reload() {
        load()
    }

    override fun delete(product: Product) {
        Log.d("Delete2", "внутри delete - repository")
        productList.remove(product)//доходит досюда, но не срабатывает

    }

    companion object {
        fun create() = StoreRepository()
    }

}