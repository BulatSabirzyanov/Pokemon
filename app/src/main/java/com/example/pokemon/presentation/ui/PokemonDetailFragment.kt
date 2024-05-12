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
import coil.Coil
import coil.load
import coil.request.CachePolicy
import com.example.pokemon.PokemonApp
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentDetailBinding
import com.example.pokemon.domain.model.PokemonDetail
import com.example.pokemon.presentation.states.PokemonDetailState
import com.example.pokemon.presentation.viewmodels.PokemonDetailViewModel
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailFragment : Fragment(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PokemonDetailViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as PokemonApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonName = requireArguments().getString("name", "")
        viewModel.initWithPokemonName(pokemonName)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonDetailState.collect { state ->
                    when (state) {
                        is PokemonDetailState.Loading -> handleLoading()
                        is PokemonDetailState.Success -> handleSuccess(state.pokemonDetail)
                        is PokemonDetailState.Error -> handleError(message = state.message)
                    }
                }
            }
        }
    }

    private fun handleLoading() {
        with(binding) {
            container.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun handleSuccess(pokemonDetail: PokemonDetail) {
        with(binding) {
            container.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            ivDetailPokemon.load(
                pokemonDetail.imageUrl,
                Coil.imageLoader(ivDetailPokemon.context)
            ) {
                memoryCachePolicy(CachePolicy.ENABLED)
            }
            tvPokemonName.text = pokemonDetail.name
            tVPokemonHpCount.text = pokemonDetail.stat[0].baseStat.toString()
            tVPokemonAttackCount.text = pokemonDetail.stat[1].baseStat.toString()
            tVPokemonDefenseCount.text = pokemonDetail.stat[2].baseStat.toString()
            tVPokemonSpecialAttackCount.text = pokemonDetail.stat[3].baseStat.toString()
            tVPokemonSpecialDefenseCount.text = pokemonDetail.stat[4].baseStat.toString()
            tVPokemonBaseExperienceCount.text = pokemonDetail.baseExperience
            tVPokemonHeightCount.text = pokemonDetail.height
            tVPokemonWeightCount.text = pokemonDetail.weight
        }

    }

    private fun handleError(message: String?) {
        Toast.makeText(context, message ?: "An unknown error occurred", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(pokemonName: String): PokemonDetailFragment {
            val fragment = PokemonDetailFragment()
            val args = Bundle().apply {
                putString("name", pokemonName)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
