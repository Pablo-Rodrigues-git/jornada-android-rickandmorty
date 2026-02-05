package com.oceanbrasil.oceanjornadaandroidmar2024.model.domain

data class ItemDetail(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val episode: List<String>
)
