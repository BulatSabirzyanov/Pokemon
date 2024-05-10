package com.example.pokemon.data.usecase

import com.example.pokemon.data.Pokemon
import com.example.pokemon.data.remote.responses.PokemonDetailItem
import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.usecase.GetAllPokemonUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllPokemonUseCaseImpl @Inject constructor(private val repository : PokemonRepository) : GetAllPokemonUseCase {

    override suspend fun getAllPokemon(): Flow<List<Pokemon>> {
        return repository.getPokemonList()
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetailItem {
        return repository.getPokemonDetail(name = name)
    }
}