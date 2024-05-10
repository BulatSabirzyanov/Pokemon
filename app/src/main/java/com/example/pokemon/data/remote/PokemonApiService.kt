package com.example.pokemon.data.remote

import com.example.pokemon.data.remote.responses.PokemonDetailItem
import com.example.pokemon.data.remote.responses.PokemonListItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 20
    ): PokemonListItem

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailItem
}