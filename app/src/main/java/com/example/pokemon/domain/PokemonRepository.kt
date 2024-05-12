package com.example.pokemon.domain

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetail

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>

    suspend fun getPokemonDetail(name: String): PokemonDetail
}
