package com.example.pokemon.data.repository

import com.example.pokemon.data.Pokemon
import com.example.pokemon.data.mappers.PokemonResultToPokemonMapper
import com.example.pokemon.data.remote.PokemonApiService
import com.example.pokemon.data.remote.responses.PokemonDetailItem
import com.example.pokemon.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val remoteSource: PokemonApiService,
    private val pokemonResultToPokemonMapper: PokemonResultToPokemonMapper): PokemonRepository {

    override suspend fun getPokemonList(): Flow<List<Pokemon>> = flow {
        val pokemonList = remoteSource.getPokemonList(20, 20)
        val mappedPokemonList = pokemonList.results.map { pokemonResultToPokemonMapper.mapToPokemon(it) }
        emit(mappedPokemonList)
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetailItem {
        return remoteSource.getPokemonDetail(name = name)
    }
}