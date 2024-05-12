package com.example.pokemon.di

import com.example.pokemon.presentation.ui.MainActivity
import com.example.pokemon.presentation.ui.PokemonDetailFragment
import com.example.pokemon.presentation.ui.PokemonListFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class,
        ViewModelsModule::class,
        DomainModule::class
    ]
)
interface AppComponent {

    fun getRouter(): Router
    fun getNavigatorHolder(): NavigatorHolder
    fun inject(mainActivity: MainActivity)
    fun inject(pokemonListFragment: PokemonListFragment)
    fun inject(pokemonDetailFragment: PokemonDetailFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}
