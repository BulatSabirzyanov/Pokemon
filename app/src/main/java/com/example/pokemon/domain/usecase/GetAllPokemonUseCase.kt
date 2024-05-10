package com.example.pokemon.domain.usecase

import com.example.pokemon.data.Pokemon
import com.example.pokemon.data.remote.responses.PokemonDetailItem
import kotlinx.coroutines.flow.Flow

interface GetAllPokemonUseCase {

    suspend fun getAllPokemon(): Flow<List<Pokemon>>

    suspend fun getPokemonDetail(name: String): PokemonDetailItem
}
