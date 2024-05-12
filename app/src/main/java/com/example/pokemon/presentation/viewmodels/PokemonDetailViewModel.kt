package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.usecases.GetPokemonDetailUseCase
import com.example.pokemon.presentation.states.PokemonDetailState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _pokemonDetailState =
        MutableStateFlow<PokemonDetailState>(PokemonDetailState.Loading)
    val pokemonDetailState: StateFlow<PokemonDetailState> = _pokemonDetailState.asStateFlow()

    private var _pokemonName: String? = null

    fun initWithPokemonName(pokemonName: String) {
        _pokemonName = pokemonName
        loadPokemonDetail(pokemonName)
    }

    private fun loadPokemonDetail(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonDetailState.value = PokemonDetailState.Loading
            try {
                _pokemonDetailState.value =
                    PokemonDetailState.Success(getPokemonDetailUseCase.getPokemonDetail(pokemonName))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _pokemonDetailState.value = PokemonDetailState.Error(e.message)
            }
        }
    }
}
