package com.example.pokemon.data.remote.responses

import com.google.gson.annotations.SerializedName

data class PokemonDetailApiResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("stats")
    val stat: List<PokemonStat>,

    @SerializedName("base_experience")
    val baseExperience: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("weight")
    val weight: Int
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("effort")
    val effort: Int?,

    @SerializedName("stat")
    val stat: Name
)

data class Name(
    @SerializedName("name")
    val name: String
)