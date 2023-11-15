package com.turing.alan.pokemonotravezconfragmentos.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.turing.alan.pokemonotravezconfragmentos.data.model.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.model.PokemonListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/{id}/")
    suspend fun fetchPokemon(@Path("id") id:String):PokemonApiModel

    @GET("api/v2/pokemon/")
    suspend fun fetchPokemonList(): PokemonListApiModel
}


class PokemonRepository private constructor(private val api:PokemonApi) {

    private val _pokemon = MutableLiveData<PokemonListApiModel>()
    val pokemon: LiveData<PokemonListApiModel>
        get() = _pokemon

    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!

        }
    }

    suspend fun fetch() {
        val pokemonResponse = api.fetchPokemonList()
        _pokemon.value = pokemonResponse
    }
}