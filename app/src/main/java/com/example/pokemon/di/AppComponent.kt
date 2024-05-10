package com.example.pokemon.di

import android.app.Application
import android.content.Context
import com.example.pokemon.PokemonApp
import com.example.pokemon.data.mappers.PokemonResultToPokemonMapper
import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.usecase.GetAllPokemonUseCase
import com.example.pokemon.presentation.screens.MainFragment
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class,AppModule::class])
interface AppComponent {

    fun getPokemonRepository() : PokemonRepository

    fun getAllPokemonUseCase() : GetAllPokemonUseCase

    fun getPokemonResultToPokemonMapper() : PokemonResultToPokemonMapper

    fun inject(mainFragment: MainFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }
}