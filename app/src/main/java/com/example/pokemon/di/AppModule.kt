package com.example.pokemon.di

import androidx.lifecycle.ViewModel
import com.example.pokemon.presentation.viewmodels.MainViewModel
import com.example.pokemon.presentation.viewmodels.PokemonDetailViewModel
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppModule{
    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    fun pokemonListViewModel(viewModel: PokemonListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    fun pokemonDetailViewModel(viewModel: PokemonDetailViewModel): ViewModel
}