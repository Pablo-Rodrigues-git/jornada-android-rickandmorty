package com.oceanbrasil.oceanjornadaandroidmar2024.model.domain

// Esta data class foi criada inicialmente para representar um personagem.
// No desenvolvimento com a API, ela não está sendo utilizada diretamente,
// pois as classes `Item` e `ItemDetail` foram criadas para espelhar a estrutura do JSON da API.
// Ela poderia ser usada, por exemplo, se os dados viessem de uma fonte local (um banco de dados) em vez da internet.
data class Personagem(
    // Propriedade para guardar o nome do personagem.
    val nome: String,

    // Propriedade para guardar a referência da imagem (neste caso, um `Int` que poderia ser um ID de recurso local, ex: R.drawable.personagem).
    val imagem: Int,

    // Propriedade para a descrição do personagem.
    val description: String,
)
