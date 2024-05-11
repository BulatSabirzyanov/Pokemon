package com.example.pokemon.presentation.states

import com.example.pokemon.domain.model.PokemonDetail

sealed class PokemonDetailState {
    object Loading : PokemonDetailState()
    data class Success(val pokemonDetail: PokemonDetail) : PokemonDetailState()
    data class Error(val message: String?) : PokemonDetailState()

}