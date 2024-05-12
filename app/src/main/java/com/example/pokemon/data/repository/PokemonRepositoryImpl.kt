package com.example.pokemon.data.repository

import com.example.pokemon.data.mapper.PokemonDataToPokemonDomainMapper
import com.example.pokemon.data.remote.PokemonApiService
import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteSource: PokemonApiService,
    private val pokemonResultToPokemonMapper: PokemonDataToPokemonDomainMapper
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        val pokemonList = remoteSource.getPokemonList(limit, offset)
        return pokemonList.results.map { pokemonResultToPokemonMapper.mapToPokemonListItem(it) }
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetail {
        val pokemonDetail = remoteSource.getPokemonDetail(name = name)
        return pokemonResultToPokemonMapper.mapToPokemonDetail(pokemonDetail)
    }
}
