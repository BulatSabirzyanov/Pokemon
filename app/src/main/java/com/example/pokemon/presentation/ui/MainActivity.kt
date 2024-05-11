package com.example.pokemon.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.pokemon.PokemonApp
import com.example.pokemon.R
import com.example.pokemon.presentation.viewmodels.MainViewModel
import com.example.pokemon.presentation.viewmodels.ViewModelFactory
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val navigator = AppNavigator(this, R.id.mainFrame)

    private val navigatorHolder by lazy {
        (application as PokemonApp).appComponent.getNavigatorHolder()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PokemonApp).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        viewModel.init()
    }
}