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

class ItemAdapter(private val itens: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImagem: ImageView = itemView.findViewById(R.id.ivImagem)
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itens[position]
        holder.tvNome.text = item.nome
        holder.tvDescription.text = "${item.status} - ${item.species}"

        // Gerar uma cor com base no nome do personagem
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
