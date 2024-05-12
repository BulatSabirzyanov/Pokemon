package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.model.Pokemon
import javax.inject.Inject

interface GetAllPokemonUseCase {

    suspend fun getAllPokemon(limit: Int, offset: Int): List<Pokemon>
}

class GetAllPokemonUseCaseImpl @Inject constructor(
    private val repository: PokemonRepository
) : GetAllPokemonUseCase {
    override suspend fun getAllPokemon(limit: Int, offset: Int): List<Pokemon> {
        return repository.getPokemonList(limit, offset)
    }
}
