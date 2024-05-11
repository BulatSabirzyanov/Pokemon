package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllPokemonUseCaseImpl @Inject constructor(private val repository : PokemonRepository) : GetAllPokemonUseCase {

    override suspend fun getAllPokemon(limit : Int, offset : Int): Flow<List<Pokemon>> {
        return repository.getPokemonList(limit, offset)
    }
}
