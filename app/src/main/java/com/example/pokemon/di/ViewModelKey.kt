package com.example.pokemon.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val viewModelClass: KClass<out ViewModel>)
