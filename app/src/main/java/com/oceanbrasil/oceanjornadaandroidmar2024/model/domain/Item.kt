package com.oceanbrasil.oceanjornadaandroidmar2024.model.domain

import com.google.gson.annotations.SerializedName

data class Item(
    val id: Int,
    @SerializedName("name")
    val nome: String,
    @SerializedName("image")
    val imagem: String,
    val status: String,
    val species: String
)
