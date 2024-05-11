package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetAllPokemonUseCase {

    suspend fun getAllPokemon(limit : Int, offset : Int): Flow<List<Pokemon>>


}
