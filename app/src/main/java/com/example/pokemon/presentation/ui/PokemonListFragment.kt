package com.example.pokemon.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.pokemon.presentation.adapter.VerticalSpaceItemDecoration
import com.example.pokemon.presentation.states.PokemonListState
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PokemonListViewModel by viewModels { viewModelFactory }
    private var binding: FragmentMainBinding? = null
    private var pokemonAdapter: PokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as PokemonApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonAdapter = PokemonAdapter { viewModel.navigateToDetail(it.name) }
        val spacingDp = 10f
        val itemVerticalDecoration = VerticalSpaceItemDecoration(spacingDp)
        binding?.run {
            recycler.adapter = pokemonAdapter
            recycler.addItemDecoration(itemVerticalDecoration)
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.loadNextPage()
                    }
                }
            })
        }


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
        binding?.run  {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun handleSuccess(pokemonList: List<Pokemon>) {
        binding?.run  {
            progressBar.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }
        pokemonAdapter?.submitList(pokemonList)
    }

    private fun handleError(message: String?) {
        Toast.makeText(context, message ?: "An unknown error occurred", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): Fragment {
            return PokemonListFragment()
        }
    }
}
