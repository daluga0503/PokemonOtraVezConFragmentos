package com.turing.alan.pokemonotravezconfragmentos.data.api.model

import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon

data class PokemonListResponse(
    val results: List<PokemonListItemResponse>
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
