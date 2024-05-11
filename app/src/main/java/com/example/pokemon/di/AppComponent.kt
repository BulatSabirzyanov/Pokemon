package com.example.pokemon.di

import com.example.pokemon.presentation.ui.MainActivity
import com.example.pokemon.presentation.ui.PokemonListFragment
import com.example.pokemon.presentation.viewmodels.MainViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class,DataModule::class, AppModule::class])
interface AppComponent {

    fun getRouter(): Router
    fun getNavigatorHolder(): NavigatorHolder

    fun inject(pokemonListFragment: PokemonListFragment)

    fun inject(mainActivity: MainActivity)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}