package com.turing.alan.pokemonotravezconfragmentos.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonDetailResponse
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/{id}/")
    suspend fun fetchPokemon(@Path("id") id:String): PokemonDetailResponse

    @GET("api/v2/pokemon")
    suspend fun fetchPokemonList(): PokemonListResponse
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
        val pokemonResponseAll = api.fetchPokemonList()

        val pokemonListWithDetails = pokemonResponseAll.results.map{
            val detailResponse = api.fetchPokemon(it.name)
            PokemonApiModel(detailResponse.id,
                detailResponse.name,
                detailResponse.weight,
                detailResponse.height,
                detailResponse.sprites.front_default)
        }
        val pokemonListApiModel = PokemonListApiModel(pokemonListWithDetails)
        _pokemon.value = pokemonListApiModel

    }
}