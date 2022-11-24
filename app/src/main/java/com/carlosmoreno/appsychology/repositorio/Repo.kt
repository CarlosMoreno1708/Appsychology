package com.carlosmoreno.appsychology.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carlosmoreno.appsychology.model.PsicologosFB
import com.google.firebase.firestore.FirebaseFirestore

class Repo {

    private var db = FirebaseFirestore.getInstance()

    fun getPsychoData(): LiveData<MutableList<PsicologosFB>>{
        val mutableData = MutableLiveData<MutableList<PsicologosFB>>()
        db.collection("psicologos").document().id
        db.collection("psicologos").get().addOnSuccessListener { result ->
            val listData = mutableListOf<PsicologosFB>()
            for(document in result){
                val perfil = document.getString("perfil")
                val nombre = document.getString("nombre")
                val foto = document.getString("foto")
                val descripcion = document.getString("descripcion")
                val ciudad = document.getString("ciudad")
                val psycho = PsicologosFB(perfil!!, nombre!!, foto!!, descripcion!!, ciudad!!)
                listData.add(psycho)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}