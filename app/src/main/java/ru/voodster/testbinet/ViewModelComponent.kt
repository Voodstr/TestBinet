package ru.voodster.testbinet

import dagger.Component
import ru.voodster.testbinet.api.ApiModule
import ru.voodster.testbinet.api.BinetApi
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ViewModelComponent {

    fun api():BinetApi

}