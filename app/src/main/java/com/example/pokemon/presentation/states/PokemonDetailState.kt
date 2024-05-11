package com.example.pokemon.presentation.states

import com.example.pokemon.domain.Pokemon

sealed class PokemonDetailState {
    object Loading : PokemonDetailState()
    data class Success(val pokemonList: List<Pokemon>) : PokemonDetailState()
    data class Error(val message: String?) : PokemonDetailState()

}