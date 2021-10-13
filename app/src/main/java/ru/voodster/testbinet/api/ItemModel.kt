package ru.voodster.testbinet.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    @SerializedName("id") val id:String,
    @SerializedName("body")val body:String,
    @SerializedName("da")val da:Long,
    @SerializedName("dm")val dm:Long
) : Parcelable