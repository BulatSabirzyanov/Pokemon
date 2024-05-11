package com.example.pokemon.di

import com.example.pokemon.data.mapper.PokemonDataToPokemonDomainMapper
import com.example.pokemon.data.remote.PokemonApiService
import com.example.pokemon.data.remote.PokemonService
import com.example.pokemon.data.repository.PokemonRepositoryImpl
import com.example.pokemon.domain.usecases.GetAllPokemonUseCaseImpl
import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.usecases.GetAllPokemonUseCase
import com.example.pokemon.domain.usecases.GetPokemonDetailUseCase
import com.example.pokemon.domain.usecases.GetPokemonDetailUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providePokemonApiService(): PokemonApiService {
        return PokemonService.instance
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        remoteSource: PokemonApiService,
        pokemonResultToPokemonMapper: PokemonDataToPokemonDomainMapper
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            remoteSource,
            pokemonResultToPokemonMapper
        )
    }

    @Provides
    @Singleton
    fun provideGetAllPokemonUseCase(
        repository: PokemonRepository
    ): GetAllPokemonUseCase {
        return GetAllPokemonUseCaseImpl(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(
        repository: PokemonRepository
    ): GetPokemonDetailUseCase {
        return GetPokemonDetailUseCaseImpl(repository = repository)
    }

}