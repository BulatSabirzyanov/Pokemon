package com.example.pokemon.data.remote.responses

import com.google.gson.annotations.SerializedName


data class PokemonListItem(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<PokemonResult>
)


data class PokemonResult(
    val name: String,
    val url: String
)