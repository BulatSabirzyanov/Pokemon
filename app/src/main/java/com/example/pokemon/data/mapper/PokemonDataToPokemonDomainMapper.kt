package com.example.pokemon.data.mapper

import com.example.pokemon.data.remote.responses.PokemonDetailApiResponse
import com.example.pokemon.data.remote.responses.PokemonResult
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonDataToPokemonDomainMapper @Inject constructor() {
    fun mapToPokemonListItem(item: PokemonResult): Pokemon {
        return Pokemon(
            id = getId(item.url),
            name = item.name,
            url = getImageUrlWithApiUrl(item.url)
        )
    }

    fun mapToPokemonDetail(item: PokemonDetailApiResponse): PokemonDetail {
        return PokemonDetail(
            name = item.name,
            imageUrl = getImageUrlWithId(item.id),
            baseExperience = item.baseExperience.toString(),
            height = item.height.toString(),
            weight = item.weight.toString(),
            stat = item.stat
        )
    }

    private fun getImageUrlWithApiUrl(apiUrl: String): String {
        val pokemonIdPattern = """/(\d+)/""".toRegex()
        val pokemonId = pokemonIdPattern.find(apiUrl)?.groupValues?.get(1)?.toInt() ?: 0
        return getImageUrlWithId(pokemonId)
    }

    private fun getId(apiUrl: String): Int {
        val pokemonIdPattern = """/(\d+)/""".toRegex()
        return pokemonIdPattern.find(apiUrl)?.groupValues?.get(1)?.toInt() ?: 0
    }

    private fun getImageUrlWithId(pokemonId: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
    }
}
