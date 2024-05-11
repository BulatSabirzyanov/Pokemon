package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.model.PokemonDetail

interface GetPokemonDetailUseCase {
    suspend fun getPokemonDetail(name: String): PokemonDetail
}
