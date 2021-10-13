package ru.voodster.testbinet

import android.app.Application

class App:Application() {

    companion object{
        const val BASE_URL = "https://bnet.i-partner.ru/"
        var instance: App? = null
            private set
    }

    init {
        instance = this
    }
}