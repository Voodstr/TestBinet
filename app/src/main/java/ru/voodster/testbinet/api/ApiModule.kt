package ru.voodster.testbinet.api

import com.google.gson.Gson
import ru.voodster.testbinet.App
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.voodster.testbinet.BuildConfig
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideApi(): BinetApi {

        val provideLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor)
            .build()

        val rxAdapter = RxJava3CallAdapterFactory.create()

        return Retrofit.Builder()
            .baseUrl(App.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinetApi::class.java)
    }
}