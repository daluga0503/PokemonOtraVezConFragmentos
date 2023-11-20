package com.turing.alan.pokemonotravezconfragmentos.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.PokemonItemBinding

class PokemonAdapter(): ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DiffCallback) {
    class PokemonViewHolder(private val binding: PokemonItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindPokemon(p: Pokemon){
            binding.namePokemon.text = p.name
            binding.imagePokemon.load(p.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bindPokemon(pokemon)
    }

    object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem==newItem
        }

    }

}