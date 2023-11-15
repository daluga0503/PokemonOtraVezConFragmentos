package com.turing.alan.pokemonotravezconfragmentos.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.data.model.PokemonListApiModel
import kotlinx.coroutines.launch

class PokemonListViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonList = MutableLiveData<PokemonListApiModel>()
    val pokemonList: LiveData<PokemonListApiModel>
        get() = _pokemonList
    private val observer = Observer<PokemonListApiModel> {
        _pokemonList.value = it
    }

    init {
        fetch()
    }

    private fun fetch() {
        repository.pokemon.observeForever(observer)
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