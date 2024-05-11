package com.example.pokemon.data.mapper

import com.example.pokemon.domain.Pokemon
import com.example.pokemon.data.remote.responses.PokemonResult
import javax.inject.Inject

class PokemonDataToPokemonDomainMapper @Inject constructor(){
    fun mapToPokemon(item: PokemonResult): Pokemon {
        return Pokemon(
            name = item.name,
            url = getImageUrlFromApiUrl(item.url)
        )
    }

    private fun getImageUrlFromApiUrl(apiUrl: String): String {
        val pokemonIdPattern = """/(\d+)/""".toRegex()
        val pokemonId = pokemonIdPattern.find(apiUrl)?.groupValues?.get(1) ?: return ""
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
    }
}