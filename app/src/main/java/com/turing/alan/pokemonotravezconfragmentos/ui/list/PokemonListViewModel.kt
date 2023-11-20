package com.turing.alan.pokemonotravezconfragmentos.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon


class PokemonListViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonUi = MutableLiveData<List<Pokemon>>()
    val pokemonUi: LiveData<List<Pokemon>>
        get() = _pokemonUi

    //Pasamos una lista
    private val observer = Observer<PokemonListApiModel> {
        //_pokemonUi.value = Pokemon(it.id, it.name)
        //Por cada pokemon de la lista el behaviur subject lo mapea a tipo Pokemon
            respuesta -> _pokemonUi.value = respuesta.list.map {
            pokemonApiModel ->  Pokemon(pokemonApiModel.id, pokemonApiModel.name, pokemonApiModel.front)
        }
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