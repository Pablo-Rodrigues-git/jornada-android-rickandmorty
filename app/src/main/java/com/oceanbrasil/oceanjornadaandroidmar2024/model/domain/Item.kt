package com.oceanbrasil.oceanjornadaandroidmar2024.model.domain

import com.google.gson.annotations.SerializedName

// `data class` é um tipo de classe em Kotlin otimizada para armazenar dados.
// Esta classe representa a estrutura de dados de um único personagem na lista da API.
// O Gson usará esta classe como um molde para converter o JSON em objetos Kotlin.
data class Item(
    // O nome da propriedade `id` é o mesmo que o nome no JSON, então não precisa de anotação.
    val id: Int,

    // A anotação @SerializedName é usada quando o nome da propriedade no Kotlin
    // é diferente do nome do campo no JSON. Aqui, o campo "name" do JSON
    // será colocado na propriedade `nome` do nosso objeto.
    @SerializedName("name")
    val nome: String,

    // O mesmo acontece para "image", que será mapeado para a propriedade `imagem`.
    @SerializedName("image")
    val imagem: String,

    // Estes campos (`status` e `species`) têm o mesmo nome no JSON e na classe,
    // então o Gson os mapeia automaticamente.
    val status: String,
    val species: String
)
