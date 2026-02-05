package com.oceanbrasil.oceanjornadaandroidmar2024.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
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

        val id = intent.getIntExtra("ID", 0)

        if (id == 0) {
            finish()
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getCharacterById(id).enqueue(object : Callback<ItemDetail> {
            override fun onResponse(call: Call<ItemDetail>, response: Response<ItemDetail>) {
                response.body()?.let {
                    Log.d("API", it.toString())

                    val tvNome = findViewById<TextView>(R.id.tvNome)
                    val ivImagem = findViewById<ImageView>(R.id.ivImagem)
                    val chipStatus = findViewById<Chip>(R.id.chipStatus)
                    val chipEspecie = findViewById<Chip>(R.id.chipEspecie)
                    val chipGenero = findViewById<Chip>(R.id.chipGenero)
                    val chipOrigem = findViewById<Chip>(R.id.chipOrigem)
                    val chipLocation = findViewById<Chip>(R.id.chipLocation)
                    val chipEpisodios = findViewById<Chip>(R.id.chipEpisodios)

                    tvNome.text = it.name
                    Glide.with(ivImagem).load(it.image).into(ivImagem)

                    chipStatus.text = "Status: ${it.status}"
                    chipEspecie.text = "Espécie: ${it.species}"
                    chipGenero.text = "Gênero: ${it.gender}"
                    chipOrigem.text = "Origem: ${it.origin.name}"
                    chipLocation.text = "Localização: ${it.location.name}"
                    chipEpisodios.text = "Episódios: ${it.episode.size}"
                }
            }

            override fun onFailure(call: Call<ItemDetail>, t: Throwable) {
                Log.e("API", "Erro ao carregar dados da API.", t)
            }
        })
    }
}
