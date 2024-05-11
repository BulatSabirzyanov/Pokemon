package com.example.pokemon.domain

import com.example.pokemon.data.remote.responses.PokemonDetailItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(limit : Int, offset : Int): Flow<List<Pokemon>>

    suspend fun getPokemonDetail(name: String): PokemonDetailItem
}
