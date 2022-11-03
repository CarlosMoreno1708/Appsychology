package com.carlosmoreno.appsychology.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.carlosmoreno.appsychology.databinding.ViewPsychoItemBinding
import com.carlosmoreno.appsychology.model.DataSource
import com.carlosmoreno.appsychology.model.Psicologos

class PsycoAdapter(
    private val context: Context
): RecyclerView.Adapter<PsycoAdapter.ViewHolder>(){

    val listPsico: MutableList<Psicologos> = DataSource.psycologos

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ViewPsychoItemBinding.bind(view)

        fun bin(psicologos: Psicologos, context: Context){
            with(binding){
                psycoPhotoImage.setImageResource(psicologos.photo)
                psycoPerfilText.text = psicologos.perfil
                binding.psycoNameText.text = psicologos.name
            }
            itemView.setOnClickListener{
                Toast.makeText(context, psicologos.perfil, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewPsychoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = listPsico.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listPsico[position]
        holder.bin(data, context)
//        holder.binding.psycoPhotoImage.setImageResource(data.photo)
//        holder.binding.psycoPerfilText.text = data.perfil
//        holder.binding.psycoNameText.text = data.name
    }

}