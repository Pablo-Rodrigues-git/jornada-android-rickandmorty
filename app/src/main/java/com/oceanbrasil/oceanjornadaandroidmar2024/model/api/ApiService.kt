package com.oceanbrasil.oceanjornadaandroidmar2024.model.api

import com.oceanbrasil.oceanjornadaandroidmar2024.model.api.ApiRepository.RickAndMortyResponse
import com.oceanbrasil.oceanjornadaandroidmar2024.model.domain.ItemDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    fun carregarItens(): Call<RickAndMortyResponse>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<ItemDetail>
}
