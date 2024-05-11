package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.usecases.GetAllPokemonUseCase
import com.example.pokemon.presentation.states.PokemonListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(private val getAllPokemonUseCase: GetAllPokemonUseCase) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState.asStateFlow()

    private var offset = 0
    private var pokemonList = mutableListOf<Pokemon>()

    init {
        loadAllPokemon()
    }

    private fun loadAllPokemon(limit: Int = 20) {
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonListState.value = PokemonListState.Loading
            try {
                getAllPokemonUseCase.getAllPokemon(limit, offset).collect { newPokemon ->
                    pokemonList.addAll(newPokemon)
                    _pokemonListState.value = PokemonListState.Success(pokemonList.toList())
                    offset += limit
                }
            } catch (e: Exception) {
                _pokemonListState.value = PokemonListState.Error(e.message)
            }
        }
    }

    fun loadNextPage() {
        loadAllPokemon()
    }
}