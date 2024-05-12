package com.example.pokemon.presentation.adapter

import com.example.pokemon.domain.model.Pokemon

fun interface OnItemClickListener {
    fun onItemClick(item: Pokemon)
}
