package com.example.pokemon.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.PokemonApp
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentMainBinding
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.presentation.adapter.PokemonAdapter
import com.example.pokemon.presentation.states.PokemonListState
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PokemonListViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as PokemonApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonAdapter = PokemonAdapter { viewModel.navigateToDetail(it.name) }
        binding.recycler.adapter = pokemonAdapter
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadNextPage()
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonListState.collect { state ->
                    when (state) {
                        is PokemonListState.Loading -> handleLoading()
                        is PokemonListState.Success -> handleSuccess(state.pokemonList)
                        is PokemonListState.Error -> handleError(state.message)
                    }
                }
            }
        }
    }

    private fun handleLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun handleSuccess(pokemonList: List<Pokemon>) {
        with(binding) {
            progressBar.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }
        pokemonAdapter.submitList(pokemonList)
    }

    private fun handleError(message: String?) {
        // Show error message
    }

    companion object {
        fun newInstance(): Fragment {
            return PokemonListFragment()
        }
    }
}
