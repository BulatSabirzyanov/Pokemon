package com.example.pokemon.data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class PokemonListItem(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<PokemonResult>
) : Parcelable

@Parcelize
data class PokemonResult(
    val name: String,
    val url: String
): Parcelable