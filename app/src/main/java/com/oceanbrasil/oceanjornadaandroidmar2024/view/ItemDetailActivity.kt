package com.oceanbrasil.oceanjornadaandroidmar2024.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.oceanbrasil.oceanjornadaandroidmar2024.R
import com.oceanbrasil.oceanjornadaandroidmar2024.model.api.ApiService
import com.oceanbrasil.oceanjornadaandroidmar2024.model.domain.ItemDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        // 1. Obter o ID do personagem passado da tela anterior (MainActivity)
        // O ID foi enviado através de um Intent com a chave "ID".
        // Se a chave "ID" não for encontrada, o valor padrão será 0.
        val id = intent.getIntExtra("ID", 0)

        // 2. Validar o ID recebido
        // Se o ID for 0, significa que não recebemos um ID válido.
        // `finish()` encerra a tela atual e `return` impede a execução do restante do código.
        if (id == 0) {
            finish()
            return
        }

        // 3. Configurar o Retrofit para comunicação com a API (semelhante à MainActivity)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 4. Criar o serviço da API a partir da interface ApiService
        val apiService = retrofit.create(ApiService::class.java)

        // 5. Fazer a chamada para buscar um personagem específico pelo seu ID
        // A chamada é assíncrona com `.enqueue()` para não travar a interface.
        apiService.getCharacterById(id).enqueue(object : Callback<ItemDetail> {
            // O Callback define o que fazer com a resposta da API.

            // 6. `onResponse` é executado quando a API retorna uma resposta.
            override fun onResponse(call: Call<ItemDetail>, response: Response<ItemDetail>) {
                // 7. Verifica se a resposta contém dados (corpo).
                response.body()?.let {
                    // `it` aqui é o objeto `ItemDetail` com os dados do personagem.
                    Log.d("API", it.toString()) // Log para fins de depuração.

                    // 8. Encontrar os componentes (Views) no layout XML.
                    val tvNome = findViewById<TextView>(R.id.tvNome)
                    val ivImagem = findViewById<ImageView>(R.id.ivImagem)

                    // 9. Atualizar a interface do usuário com os dados recebidos.
                    // Define o texto do TextView com o nome do personagem.
                    tvNome.text = it.name
                    // Usa a biblioteca Glide para carregar a imagem da URL no ImageView.
                    Glide.with(ivImagem).load(it.image).into(ivImagem)
                }
            }

            // 10. `onFailure` é executado se ocorrer um erro de rede.
            override fun onFailure(call: Call<ItemDetail>, t: Throwable) {
                // Registra o erro no Logcat para análise do desenvolvedor.
                Log.e("API", "Erro ao carregar dados da API.", t)
            }
        })
    }
}
