package com.example.pokemon.domain.usecases

import com.example.pokemon.data.remote.responses.PokemonDetailItem

interface GetPokemonDetailUseCase {
    suspend fun getPokemonDetail(name: String): PokemonDetailItem
}
