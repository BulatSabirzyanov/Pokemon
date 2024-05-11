package com.example.pokemon.presentation.screens


import com.example.pokemon.presentation.ui.PokemonDetailFragment
import com.example.pokemon.presentation.ui.PokemonListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun pokemonListFragment() = FragmentScreen {
        PokemonListFragment.newInstance()
    }

    fun pokemonDetailScreen(name: String) = FragmentScreen {
        PokemonDetailFragment.newInstance(name)
    }
}
