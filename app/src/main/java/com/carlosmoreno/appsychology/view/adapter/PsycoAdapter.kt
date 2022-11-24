package com.carlosmoreno.appsychology.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlosmoreno.appsychology.databinding.CardViewPsychoBinding
import com.carlosmoreno.appsychology.model.ClickListener
import com.carlosmoreno.appsychology.model.PsicologosFB

class PsycoAdapter(
    private val context: Context,
    var clickListener: ClickListener
): RecyclerView.Adapter<PsycoAdapter.ViewHolder>(){

//    val listPsico: MutableList<Psicologos> = DataSource.psycologos
    var listPsico = mutableListOf<PsicologosFB>()

    fun setListData(data: MutableList<PsicologosFB>){
        listPsico = data
    }

    class ViewHolder(view: View, listener: ClickListener): RecyclerView.ViewHolder(view), View.OnClickListener {
        val binding = CardViewPsychoBinding.bind(view)
        var listener: ClickListener? = null

        init {
            this.listener = listener
            itemView.setOnClickListener(this)
        }

        fun bin(psicologos: PsicologosFB){
            with(binding){
//                psycoCardImage.setImageResource(psicologos.foto)
                psycoPerfilCard.text = psicologos.perfil
                psycoNameCard.text = psicologos.nombre
            }
            Glide.with(binding.root.context).load(psicologos.foto).into(binding.psycoCardImage)
        }

        override fun onClick(view: View?) {
            this.listener?.onClic(view!!, bindingAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewPsychoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, clickListener)
    }

    override fun getItemCount() = listPsico.size

//    Instancia los objetos de la lista, uno a uno.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listPsico[position]
        holder.bin(data)

//        holder.binding.psycoCardImage.setImageResource(data.photo)
//        holder.binding.psycoPerfilCard.text = data.perfil
//        holder.binding.psycoNameCard.text = data.name
    }

}