package com.example.pokemon.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.databinding.PokemonListItemBinding


class PokemonAdapter(private val listener: OnItemClickListener) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonListItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PokemonViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener.onItemClick(item)
                }
            }
        }

        fun bind(item: Pokemon) {
            with(binding) {
                textView.text = item.name
                imageView.load(item.url, Coil.imageLoader(itemView.context))
            }
        }
    }
    private class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.url == newItem.url
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ): Boolean {
            return oldItem == newItem
        }
    }

}
