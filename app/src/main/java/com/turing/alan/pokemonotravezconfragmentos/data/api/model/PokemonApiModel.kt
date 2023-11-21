package com.turing.alan.pokemonotravezconfragmentos.data.api.model

data class PokemonApiModel(
    val id:Int,
    val name:String,
    val weight:Int,
    val height:Int,
    val front:String,
    val imgDetail: String,
)

data class PokemonDetail(
    val id:Int,
    val name:String,
    val weight:Int,
    val height:Int,
    val imgDetail: String,
)

data class PokemonListApiModel(
    val list:List<PokemonApiModel>
)


