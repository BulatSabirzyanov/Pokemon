package com.example.pokemon.di

import com.example.pokemon.data.mappers.PokemonResultToPokemonMapper
import com.example.pokemon.data.remote.PokemonApiService
import com.example.pokemon.data.remote.PokemonService
import com.example.pokemon.data.repository.PokemonRepositoryImpl
import com.example.pokemon.data.usecase.GetAllPokemonUseCaseImpl
import com.example.pokemon.domain.PokemonRepository
import com.example.pokemon.domain.usecase.GetAllPokemonUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providePandaScoreApiService(): PokemonApiService {
        return PokemonService.instance
    }

    @Provides
    @Singleton
    fun providePokemonResultToPokemonMapper(): PokemonResultToPokemonMapper {
        return PokemonResultToPokemonMapper()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        remoteSource: PokemonApiService,
        pokemonResultToPokemonMapper: PokemonResultToPokemonMapper
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

}