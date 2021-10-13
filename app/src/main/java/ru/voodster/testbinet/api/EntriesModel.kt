package ru.voodster.testbinet.api

import com.google.gson.annotations.SerializedName

data class EntriesModel(
    @SerializedName("status")val status:Int,
    @SerializedName("data")val data:List<List<ItemModel>>
)

data class NewSessionModel(
    @SerializedName("status")val status: Int,
    @SerializedName("data")val data: DataNS
)

data class DataNS(
        @SerializedName("session")val session:String
)
data class AddEntryModel(
    @SerializedName("status")val status: Int,
    @SerializedName("data")val data: DataEntry
)

data class DataEntry(
    @SerializedName("session")val id:String
)