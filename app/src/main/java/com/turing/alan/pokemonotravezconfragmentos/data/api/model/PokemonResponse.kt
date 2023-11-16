package com.turing.alan.pokemonotravezconfragmentos.data.api.model

data class PokemonListResponse(
    val results: List<PokemonListItem>
)

data class PokemonListItem(
    val name:String
)

data class PokemonDetailResponse(
    val id:Int,
    val name:String,
    val weight:Int,
    val height:Int,
    val sprites: PokemonSpritesResponse
)

data class PokemonSpritesResponse(
    val front_default:String
)
