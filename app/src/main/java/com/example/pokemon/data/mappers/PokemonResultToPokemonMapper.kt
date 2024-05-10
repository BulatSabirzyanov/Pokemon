package com.example.pokemon.data.mappers

import com.example.pokemon.data.Pokemon
import com.example.pokemon.data.remote.responses.PokemonResult

class PokemonResultToPokemonMapper {
    fun mapToPokemon(item: PokemonResult): Pokemon {
        return Pokemon(
            name = item.name,
            url = getImageUrlFromApiUrl(item.url)
        )
    }

    private fun getImageUrlFromApiUrl(apiUrl: String): String {
        val pokemonIdPattern = """/(\d+)/""".toRegex()
        val pokemonId = pokemonIdPattern.find(apiUrl)?.groupValues?.get(1) ?: return ""
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"
    }
}