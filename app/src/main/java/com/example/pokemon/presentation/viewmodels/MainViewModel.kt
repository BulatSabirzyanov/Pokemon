package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pokemon.presentation.screens.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(private val router: Router) : ViewModel() {

    fun init() {
        router.replaceScreen(Screens.pokemonListFragment())
    }

}
