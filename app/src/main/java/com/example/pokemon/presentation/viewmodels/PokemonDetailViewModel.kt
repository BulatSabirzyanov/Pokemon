package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pokemon.presentation.states.PokemonDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonDetailViewModel : ViewModel() {
    private val _pokemonDetailState = MutableStateFlow<PokemonDetailState>(PokemonDetailState.Loading)
    val pokemonDetailState: StateFlow<PokemonDetailState> = _pokemonDetailState.asStateFlow()
}