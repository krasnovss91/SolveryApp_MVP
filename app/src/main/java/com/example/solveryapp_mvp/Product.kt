package com.example.solveryapp_mvp

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime

@Parcelize
data class ProductViewState(
    @DrawableRes val avatar: Int,
    val name: String,
    val producer: String,
    val cost: Int,
    val id: Int,
    val offsetDateTime: OffsetDateTime
):Parcelable

@Parcelize
data class Product(
    @DrawableRes val avatar: Int,
    val name: String,
    val producer: String,
    val cost: Int,
    val id: Int,
    val offsetDateTime: OffsetDateTime
    ):Parcelable

@Parcelize
data class ProductDto(
    @DrawableRes val avatar: Int,
    val name: String,
    val producer: String,
    val cost: Int,
    val id: Int,
    val offsetDateTime: OffsetDateTime,
    val metadata: Metadata
):Parcelable
