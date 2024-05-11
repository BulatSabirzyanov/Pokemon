package com.example.pokemon.domain.model

import com.example.pokemon.data.remote.responses.PokemonStat

data class PokemonDetail(
    val name: String,
    val imageUrl: String,
    val stat: List<PokemonStat>,
    val baseExperience: String,
    val height: String,
    val weight: String
)
