package com.carlosmoreno.appsychology.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosmoreno.appsychology.model.PsicologosFB
import com.carlosmoreno.appsychology.repositorio.Repo

class PsychologyViewModel: ViewModel() {
    val repo = Repo()

    fun psychoData():LiveData<MutableList<PsicologosFB>>{
        val mutableData = MutableLiveData<MutableList<PsicologosFB>>()
        repo.getPsychoData().observeForever{
            mutableData.value = it

        }
        return mutableData
    }
}