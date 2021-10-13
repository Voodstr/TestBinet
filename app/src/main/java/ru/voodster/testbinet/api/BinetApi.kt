package ru.voodster.testbinet.api

import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BinetApi {

    companion object {
        const val NS = "a=new_session"

    }

    @Headers("token: D0DFysg-CB-yZLK8Ao")
    @POST("/testAPI/")
    fun newSession(@Body data: RequestBody): Single<NewSessionModel>

    @Headers("token: D0DFysg-CB-yZLK8Ao")
    @POST("/testAPI/")
    fun getEntries(@Body data: RequestBody): Single<EntriesModel>

    @Headers("token: D0DFysg-CB-yZLK8Ao")
    @POST("/testAPI/")
    fun addEntry(@Body data: RequestBody): Single<AddEntryModel>
}