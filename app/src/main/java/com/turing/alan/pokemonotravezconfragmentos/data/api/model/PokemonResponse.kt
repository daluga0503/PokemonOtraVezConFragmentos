package com.turing.alan.pokemonotravezconfragmentos.data.api.model

import com.google.gson.annotations.SerializedName

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
    val front_default:String,
    val other: OtherImg
)

data class OtherImg(
    @SerializedName("oficial-artwork")
    val official_artwork: OfficialArtwork
)

data class OfficialArtwork(
    val front_default: String
)
