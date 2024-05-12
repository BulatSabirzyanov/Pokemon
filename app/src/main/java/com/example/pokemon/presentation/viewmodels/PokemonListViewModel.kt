package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.usecases.GetAllPokemonUseCase
import com.example.pokemon.presentation.screens.Screens
import com.example.pokemon.presentation.states.PokemonListState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class PokemonListViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    private val router: Router
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState.asStateFlow()

    private var offset = 0
    private var canLoadNext = true
    private val pokemonList = mutableListOf<Pokemon>()

    init {
        loadAllPokemon()
    }

    private fun loadAllPokemon(limit: Int = 20) {
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonListState.value = PokemonListState.Loading
            try {
                val newPokemons = getAllPokemonUseCase.getAllPokemon(limit, offset)
                if (newPokemons.isEmpty()) canLoadNext = false
                pokemonList.addAll(newPokemons)
                _pokemonListState.value = PokemonListState.Success(pokemonList.toList())
                offset += limit

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _pokemonListState.value = PokemonListState.Error(e.message)
            }
        }
    }

    fun navigateToDetail(pokemonName: String) {
        router.navigateTo(Screens.pokemonDetailScreen(pokemonName))
    }

    fun loadNextPage() {
        if (canLoadNext) {
            loadAllPokemon()
        }
    }
}
