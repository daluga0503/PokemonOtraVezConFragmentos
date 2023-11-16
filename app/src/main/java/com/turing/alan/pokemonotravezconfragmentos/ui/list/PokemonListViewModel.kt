package com.turing.alan.pokemonotravezconfragmentos.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListResponse
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import kotlinx.coroutines.launch

class PokemonListViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList


    private val observer = Observer<PokemonListApiModel> {

        _pokemonList.value = it.pokemonList.map {
            pokemonApiModel ->  Pokemon(pokemonApiModel.id , pokemonApiModel.name)
        }
    }

    init {
        fetch()
    }

    private fun fetch() {
        repository.pokemon.removeObserver(observer)
        viewModelScope.launch {
            repository.fetch()
        }


    }

    override fun onCleared() {
        super.onCleared()
        repository
            .pokemon
            .removeObserver(observer)
    }
}