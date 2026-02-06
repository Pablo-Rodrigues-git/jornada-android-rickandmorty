package com.oceanbrasil.oceanjornadaandroidmar2024.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oceanbrasil.oceanjornadaandroidmar2024.R
import com.oceanbrasil.oceanjornadaandroidmar2024.model.api.ApiRepository
import com.oceanbrasil.oceanjornadaandroidmar2024.model.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configuração do RecyclerView
        val rvItens = findViewById<RecyclerView>(R.id.rvItens)

        // 2. Definir o LayoutManager
        // O LayoutManager organiza como os itens da lista serão exibidos (verticalmente, horizontalmente, em grade, etc).
        // `GridLayoutManager` exibe os itens em uma grade.
        rvItens.layoutManager = GridLayoutManager(this, 3)

        // 3. Configurar o Retrofit
        // Retrofit é uma biblioteca que simplifica a comunicação com APIs na internet (Web Services).
        val retrofit = Retrofit.Builder()
            // 3.1. Definir a URL base da API. Todas as chamadas partirão deste endereço.
            .baseUrl("https://rickandmortyapi.com/api/")
            // 3.2. Adicionar um conversor. Ele transforma o JSON (texto) da API em objetos da nossa linguagem (Kotlin).
            .addConverterFactory(GsonConverterFactory.create())
            // 3.3. Construir o objeto Retrofit.
            .build()

        // 4. Criar o serviço da API
        // Usamos o Retrofit para criar uma implementação da nossa interface `ApiService`.
        // É através deste objeto que faremos as chamadas para os endpoints da API (ex: "character").
        val apiService = retrofit.create(ApiService::class.java)

        // 5. Fazer a chamada para a API de forma assíncrona
        // `.enqueue()` executa a chamada em uma thread de fundo, para não travar a interface do usuário.
        apiService.carregarItens().enqueue(object : Callback<ApiRepository.RickAndMortyResponse> {
            // Este objeto `Callback` define o que fazer quando a API responder.

            // 6. `onResponse` é chamado quando a API retorna uma resposta (seja sucesso ou erro).
            override fun onResponse(
                call: Call<ApiRepository.RickAndMortyResponse>,
                response: Response<ApiRepository.RickAndMortyResponse>
            ) {
                // 7. Verificar se a resposta tem um corpo (body) e se não é nulo.
                response.body()?.results?.let {
                    // `it` aqui é a lista de personagens (`List<Item>`).

                    // 8. Criar e definir o Adapter para o RecyclerView
                    // O Adapter é o responsável por pegar os dados (a lista `it`) e "adaptá-los" para o formato que o RecyclerView espera.
                    rvItens.adapter = ItemAdapter(it)
                }
            }

            // 9. `onFailure` é chamado quando há um erro de rede (ex: sem internet) ou a API não pode ser alcançada.
            override fun onFailure(call: Call<ApiRepository.RickAndMortyResponse>, t: Throwable) {
                // Loga o erro no Logcat para que o desenvolvedor possa investigar.
                Log.e("API_ERROR", "Erro ao carregar itens da API", t)
            }
        })
    }
}
