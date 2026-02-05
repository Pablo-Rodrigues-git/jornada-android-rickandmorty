package com.oceanbrasil.oceanjornadaandroidmar2024.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        val rvItens = findViewById<RecyclerView>(R.id.rvItens)
        rvItens.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.carregarItens().enqueue(object : Callback<ApiRepository.RickAndMortyResponse> {
            override fun onResponse(
                call: Call<ApiRepository.RickAndMortyResponse>,
                response: Response<ApiRepository.RickAndMortyResponse>
            ) {
                response.body()?.results?.let {
                    rvItens.adapter = ItemAdapter(it)
                }
            }

            override fun onFailure(call: Call<ApiRepository.RickAndMortyResponse>, t: Throwable) {
                Log.e("API_ERROR", "Erro ao carregar itens da API", t)
            }
        })
    }
}
