package com.oceanbrasil.oceanjornadaandroidmar2024.view

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oceanbrasil.oceanjornadaandroidmar2024.R
import com.oceanbrasil.oceanjornadaandroidmar2024.model.domain.Item

// O Adapter é a peça que conecta os dados (a lista de `itens`) ao RecyclerView.
// Ele é responsável por criar as views para cada item e preenchê-las com os dados corretos.
class ItemAdapter(private val itens: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // O ViewHolder descreve a view de um item e armazena as referências para os seus componentes (TextViews, ImageView, etc).
    // Fazer isso evita que o sistema precise chamar `findViewById` toda vez que for reciclar uma view, otimizando a performance.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImagem: ImageView = itemView.findViewById(R.id.ivImagem)
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    // `onCreateViewHolder` é chamado pelo RecyclerView quando ele precisa criar um novo ViewHolder.
    // Isso acontece quando a lista é exibida pela primeira vez e quando rolamos para novos itens que ainda não têm uma view criada.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 1. Inflar o layout XML (`item_layout.xml`) que define a aparência de um item da lista.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    // `onBindViewHolder` é chamado pelo RecyclerView para associar um ViewHolder a um dado (um item da lista).
    // Esta função preenche os componentes da view (que estão no `holder`) com os dados do `item` na `position` especificada.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 1. Pega o item da lista na posição correspondente.
        val item = itens[position]
        // 2. Define o texto do TextView `tvNome` com o nome do personagem.
        holder.tvNome.text = item.nome
        // 3 define o status do personagem
        holder.tvStatus.text = item.status
        // 4. define a especie do personagem
        holder.tvDescription.text = item.species

        // Lógica para gerar uma cor única para cada nome de personagem.
        val color = Color.HSVToColor(floatArrayOf((item.nome.hashCode() % 360).toFloat(), 0.8f, 0.9f))
        holder.tvNome.setTextColor(color)

        Glide.with(holder.itemView.context)
            .load(item.imagem)
            .into(holder.ivImagem)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ItemDetailActivity::class.java)
            intent.putExtra("ID", item.id)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}
