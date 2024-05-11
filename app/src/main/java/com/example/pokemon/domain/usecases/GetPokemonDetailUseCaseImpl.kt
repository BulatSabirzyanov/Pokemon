package com.example.pokemon.domain.usecases

import com.example.pokemon.data.remote.responses.PokemonDetailItem
import com.example.pokemon.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCaseImpl @Inject constructor(private val repository : PokemonRepository) : GetPokemonDetailUseCase{

    override suspend fun getPokemonDetail(name: String): PokemonDetailItem {
        return repository.getPokemonDetail(name = name)
    }
}
