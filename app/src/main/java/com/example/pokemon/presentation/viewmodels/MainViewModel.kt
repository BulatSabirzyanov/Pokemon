package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.usecase.GetAllPokemonUseCase
import com.example.pokemon.presentation.PokemonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getAllPokemonUseCase: GetAllPokemonUseCase) : ViewModel() {

    private val _pokemonState = MutableStateFlow<PokemonState>(PokemonState.Loading)
    val pokemonState: StateFlow<PokemonState> = _pokemonState.asStateFlow()

    init {
        loadAllPokemon()
    }

    private fun loadAllPokemon() {
        viewModelScope.launch {
            _pokemonState.value = PokemonState.Loading
            try {
                getAllPokemonUseCase.getAllPokemon().collect { pokemon ->
                    _pokemonState.value = PokemonState.Success(pokemon)
                }
            } catch (e: Exception) {
                _pokemonState.value = PokemonState.Error(e.message)
            }
        }
    }
}