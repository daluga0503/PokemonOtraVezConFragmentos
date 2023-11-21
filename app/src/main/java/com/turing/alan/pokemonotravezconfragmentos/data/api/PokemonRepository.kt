package com.turing.alan.pokemonotravezconfragmentos.data.api


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonDetailResponse
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListItem
import com.turing.alan.pokemonotravezconfragmentos.data.api.model.PokemonListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/{name}/")
    suspend fun fetchPokemon(@Path("name") name:String): PokemonDetailResponse
    // TODO Añadir nuevo metodo para leer una lista de pokemon
    @GET("api/v2/pokemon")
    suspend fun fetchAllPokemon(): PokemonListResponse
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
            // que voy hacer en esta sesion de retrofit. Le doy los permisos de la interfaz
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!

        }
    }

    suspend fun fetch() {
        //Contiene la lista de pokemon
        val pokemonList = api.fetchAllPokemon()
        // lista de detalles
        val detailPokemonList = mutableListOf<PokemonApiModel>()
        pokemonList.results.forEach{
            //accede  la lista de detalles de cada pokemon
                pokemon -> val detalleDelPokemon = api.fetchPokemon(pokemon.name)
            // de todas los campo del pokemon nos quedamos con las propiedades de abajo
            val listandoCadaPokemon = PokemonApiModel(
                id = detalleDelPokemon.id,
                name = detalleDelPokemon.name,
                weight = detalleDelPokemon.weight,
                height = detalleDelPokemon.height,
                front = detalleDelPokemon.sprites.front_default,
                detailImg = detalleDelPokemon.sprites.other.toString()

            )
            detailPokemonList.add(listandoCadaPokemon)
        }
        //Actualizar el MutableliveData
        val listaDefinitivaPokemons = PokemonListApiModel(detailPokemonList)
        _pokemon.postValue(listaDefinitivaPokemons)
    }

    suspend fun getPokemonById(name: String):PokemonApiModel{
        val detailResponse = api.fetchPokemon(name)
        return PokemonApiModel(
            detailResponse.id,
            detailResponse.name,
            detailResponse.weight,
            detailResponse.height,
            detailResponse.sprites.front_default,
            detailResponse.sprites.other.official_artwork.toString()
        )
    }

}