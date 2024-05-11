package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.model.PokemonDetail
import javax.inject.Inject

class GetPokemonDetailUseCaseImpl @Inject constructor(private val repository : PokemonRepository) : GetPokemonDetailUseCase{

    override suspend fun getPokemonDetail(name: String): PokemonDetail {
        return repository.getPokemonDetail(name = name)
    }
}
