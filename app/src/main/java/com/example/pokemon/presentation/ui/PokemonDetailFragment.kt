package com.example.pokemon.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokemon.R
import com.example.pokemon.presentation.viewmodels.PokemonDetailViewModel
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class PokemonDetailFragment : Fragment(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PokemonDetailViewModel by viewModels { viewModelFactory }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun newInstance(name: String): Fragment {
            return PokemonDetailFragment()
        }
    }

}