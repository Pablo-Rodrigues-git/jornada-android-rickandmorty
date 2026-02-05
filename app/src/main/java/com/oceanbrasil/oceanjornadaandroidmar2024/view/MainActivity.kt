package com.oceanbrasil.oceanjornadaandroidmar2024.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oceanbrasil.oceanjornadaandroidmar2024.R
import com.oceanbrasil.oceanjornadaandroidmar2024.model.domain.Personagem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuração da RecyclerView
        val rvItens = findViewById<RecyclerView>(R.id.rvItens)
        rvItens.layoutManager = LinearLayoutManager(this)

        // 1. Criar a lista de dados locais
        // Usando ícones padrão do Android para evitar a necessidade de imagens customizadas.
        val personagens = listOf(
            Personagem(
                nome = "Rick Sanchez",
                imagem = android.R.drawable.ic_dialog_info,
                description = "Descrição do Rick Sanchez"
            ),
            Personagem(
                nome = "Morty Smith",
                imagem = android.R.drawable.ic_dialog_alert,
                description = "Descrição do Morty Smith"
            ),
            Personagem(
                nome = "Summer Smith",
                imagem = android.R.drawable.ic_dialog_dialer,
                description = "Descrição do Summer Smith 23wqoieuqoiweuioqwehjioqwehioqwheoiqwhjiwqehriuqhweriuqgweriugqwkqweyrgqwgrqkygewruyqwgfguhdgfuqwgyuqgwerukygqwerwq"
            ),
            Personagem(
                nome = "Beth Smith",
                imagem = android.R.drawable.ic_dialog_email,
                description = "Descrição do Beth Smith"
            ),
            Personagem(
                nome = "Jerry Smith",
                imagem = android.R.drawable.ic_dialog_map,
                description = "Descrição do Jerry Smith"
            )
        )

        // 2. Criar e configurar o adapter
        rvItens.adapter = ItemAdapter(personagens)
    }

    // commit message
}
