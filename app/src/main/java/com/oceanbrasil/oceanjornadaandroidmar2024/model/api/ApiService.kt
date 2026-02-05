package com.oceanbrasil.oceanjornadaandroidmar2024.model.api

import com.oceanbrasil.oceanjornadaandroidmar2024.model.api.ApiRepository.RickAndMortyResponse
import com.oceanbrasil.oceanjornadaandroidmar2024.model.domain.ItemDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// A interface ApiService define os "caminhos" (endpoints) da API que nosso App irá acessar.
// O Retrofit usa esta interface para criar o código que fará a comunicação de rede.
interface ApiService {
    // A anotação @GET informa ao Retrofit que esta função fará uma requisição HTTP GET.
    // O valor "character" é o caminho do endpoint que será adicionado à URL base.
    // Ex: https://rickandmortyapi.com/api/character
    @GET("character")
    // Retorna um objeto `Call` que o Retrofit usará para fazer a requisição.
    // O tipo `RickAndMortyResponse` indica ao Gson como converter a resposta JSON em objetos Kotlin.
    fun carregarItens(): Call<RickAndMortyResponse>

    // Este endpoint busca um único personagem. Note o `/{id}` no caminho.
    // Ex: https://rickandmortyapi.com/api/character/1
    @GET("character/{id}")
    // A anotação @Path("id") substitui o `/{id}` no caminho do GET pelo valor da variável `id`.
    // O tipo de retorno `ItemDetail` define a estrutura esperada para um único personagem.
    fun getCharacterById(@Path("id") id: Int): Call<ItemDetail>
}
