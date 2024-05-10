package com.example.pokemon.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.PokemonApp
import com.example.pokemon.R
import com.example.pokemon.data.Pokemon
import com.example.pokemon.databinding.FragmentMainBinding
import com.example.pokemon.presentation.PokemonState
import com.example.pokemon.presentation.adapter.OnItemClickListener
import com.example.pokemon.presentation.adapter.PokemonAdapter
import com.example.pokemon.presentation.viewmodels.MainViewModel
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main),OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonAdapter = PokemonAdapter(this@MainFragment) // Инициализация здесь
        (requireActivity().application as PokemonApp).appComponent.inject(this)
        binding.recycler.adapter = pokemonAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonState.collect { state ->
                when (state) {
                    is PokemonState.Loading -> handleLoading()
                    is PokemonState.Success -> handleSuccess(state.pokemonList)
                    is PokemonState.Error -> handleError(state.message)
                }
            }
        }
    }

    private fun handleLoading() {
        with(binding){
            progressBar.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        }
    }

    private fun handleSuccess(pokemonList: List<Pokemon>) {
        with(binding){
            progressBar.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }
        pokemonAdapter.submitList(pokemonList)
    }

    private fun handleError(message: String?) {
        // Show error message
    }

    override fun onItemClick(item: Any) {
        TODO("Not yet implemented")
    }
}