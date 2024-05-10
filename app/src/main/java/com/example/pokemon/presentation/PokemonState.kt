package com.example.pokemon.presentation

import com.example.pokemon.data.Pokemon

sealed class PokemonState {
    object Loading : PokemonState()
    data class Success(val pokemonList: List<Pokemon>) : PokemonState()
    data class Error(val message: String?) : PokemonState()
}