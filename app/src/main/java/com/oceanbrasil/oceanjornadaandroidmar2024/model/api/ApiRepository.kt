package com.oceanbrasil.oceanjornadaandroidmar2024.model.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.oceanbrasil.oceanjornadaandroidmar2024.model.domain.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {
    // A API do Rick and Morty alinha os resultados em um objeto "results".
    // Esta classe de dados ajuda o Gson a parsear essa estrutura.
    data class RickAndMortyResponse(val results: List<Item>)

    val itens = MutableLiveData<List<Item>>()

    private val apiService: ApiService

    // Inicializa o Retrofit e o serviço da API
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            // Aqui o Gson converte nosso JSON em objetos Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        // Carrega os itens na inicialização
        fetchItems()
    }

    // Função para carregar os itens da API
    private fun fetchItems() {
        apiService.carregarItens().enqueue(object : Callback<RickAndMortyResponse> {

            // Aqui fazemos o tratamento de dados da resposta, se deu certo ou não.
            override fun onResponse(
                call: Call<RickAndMortyResponse>,
                response: Response<RickAndMortyResponse>
            ) {
                response.body()?.let {
                    Log.d("API", "Itens carregados com sucesso\n${it.results}")
                    itens.postValue(it.results)
                }
            }

            override fun onFailure(call: Call<RickAndMortyResponse>, t: Throwable) {
                Log.e("API", "Erro ao carregar dados da API.", t)
            }
        })
    }
}
