package com.oceanbrasil.oceanjornadaandroidmar2024.model.domain

// Esta data class representa a estrutura de dados esperada quando buscamos
// os detalhes de um único personagem pela API.
// É uma versão potencialmente mais detalhada (ou diferente) do que o `Item` da lista.
data class ItemDetail(
    // O nome da propriedade `name` é o mesmo no JSON, então o Gson faz o mapeamento automático.
    val name: String,

    // O mesmo para `image`.
    val image: String,

    // O mesmo para origem
    val origin: Origin
    // Poderíamos adicionar mais campos aqui (como status, species, gender, origin, etc.)
    // se quiséssemos exibir mais informações na tela de detalhes.
)
