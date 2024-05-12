package com.example.pokemon

import android.app.Application
import com.example.pokemon.di.AppComponent
import com.example.pokemon.di.DaggerAppComponent

class PokemonApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .build()
    }
}
