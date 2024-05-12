package com.example.pokemon.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItem(
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<PokemonResult>
)

@Serializable
data class PokemonResult(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
