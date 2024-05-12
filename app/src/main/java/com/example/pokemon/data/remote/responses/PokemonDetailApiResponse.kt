package com.example.pokemon.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailApiResponse(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("stats")
    val stat: List<PokemonStat>,

    @SerialName("base_experience")
    val baseExperience: Int,

    @SerialName("height")
    val height: Int,

    @SerialName("weight")
    val weight: Int
)

@Serializable
data class PokemonStat(
    @SerialName("base_stat")
    val baseStat: Int,

    @SerialName("effort")
    val effort: Int?,

    @SerialName("stat")
    val stat: Name
)

@Serializable
data class Name(
    @SerialName("name")
    val name: String
)
