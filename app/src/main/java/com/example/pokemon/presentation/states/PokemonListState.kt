package com.example.pokemon.presentation.states

import com.example.pokemon.domain.Pokemon

sealed class PokemonListState {
    object Loading : PokemonListState()
    data class Success(val pokemonList: List<Pokemon>) : PokemonListState()
    data class Error(val message: String?) : PokemonListState()
}