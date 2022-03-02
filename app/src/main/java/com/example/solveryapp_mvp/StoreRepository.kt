package com.example.solveryapp_mvp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime

class StoreRepository: StoreContract.Repository {

    @RequiresApi(Build.VERSION_CODES.O)
    val productList = mutableListOf(
        Product(R.drawable.ic_launcher_background, "Картофель", "ООО Интегра", 18, Generator.generateId(),
            OffsetDateTime.now()),
        Product(R.drawable.ic_launcher_foreground, "Чай", "ИП Абрамян А.Г.", 9, Generator.generateId(),
            OffsetDateTime.now()),
        Product(R.drawable.ic_launcher_background, "Яйца", "с.Зелёное", 22, Generator.generateId(),
            OffsetDateTime.now()),
        Product(R.drawable.ic_launcher_foreground, "Молоко", "с.Зелёное", 20, Generator.generateId(),
            OffsetDateTime.now()),
        Product(R.drawable.ic_launcher_background, "Макароны", "Тольяттинский хлебозавод", 15, Generator.generateId(),
            OffsetDateTime.now())
    )

    override fun load(): List<Product> = productList

    companion object{
        fun create() = StoreRepository()
    }
}