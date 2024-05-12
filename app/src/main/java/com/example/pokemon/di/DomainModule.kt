package com.example.pokemon.di

import com.example.pokemon.data.repository.PokemonRepositoryImpl
import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.usecases.GetAllPokemonUseCase
import com.example.pokemon.domain.usecases.GetAllPokemonUseCaseImpl
import com.example.pokemon.domain.usecases.GetPokemonDetailUseCase
import com.example.pokemon.domain.usecases.GetPokemonDetailUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindPokemonRepository(
        repositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository

    @Binds
    fun bindGetAllPokemonUseCase(
        useCaseImpl: GetAllPokemonUseCaseImpl
    ): GetAllPokemonUseCase

    @Binds
    fun bindGetPokemonDetailUseCase(
        useCaseImpl: GetPokemonDetailUseCaseImpl
    ): GetPokemonDetailUseCase
}
