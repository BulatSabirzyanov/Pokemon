package com.example.pokemon.domain

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Flow<List<Pokemon>>

    suspend fun getPokemonDetail(name: String): PokemonDetail
}
