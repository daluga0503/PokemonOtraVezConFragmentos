package com.turing.alan.pokemonotravezconfragmentos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListResponse
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.PokemonItemBinding

class PokemonListAdapter(private val pokemonList: PokemonListApiModel): ListAdapter<PokemonListApiModel,RecyclerView.ViewHolder>(DIFF_CALLBACK){

    object DIFF_CALLBACK: DiffUtil.ItemCallback<PokemonListResponse>() {
        override fun areItemsTheSame(oldItem: PokemonListResponse, newItem: PokemonListResponse) = oldItem == newItem

        override fun areContentsTheSame(oldItem: PokemonListResponse, newItem: PokemonListResponse) = oldItem == newItem

    }

    inner class PokemonViewHolder(private val binding: PokemonItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindPokemon(p:Pokemon){
            binding.pokemonNameText.text = p.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon =
        holder.bindPokemon(pokemon)
    }


}