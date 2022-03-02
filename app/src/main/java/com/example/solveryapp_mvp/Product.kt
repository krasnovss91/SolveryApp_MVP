package com.example.solveryapp_mvp

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(@DrawableRes val avatar: Int, val name: String, val producer: String, val cost: Int, val id: Int):Parcelable
