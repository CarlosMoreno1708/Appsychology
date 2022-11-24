package com.carlosmoreno.appsychology.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.FragmentAgendaBinding
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class AgendaFragment : Fragment() {

    private lateinit var binding: FragmentAgendaBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgendaBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Agenda"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getStringExtra(UserActivity.EXTRA_ID)

        db.collection("agenda").document(id?:"").get().addOnSuccessListener {
            binding.perfilAgenda.text = it.get("perfil") as String
            binding.nameAgenda.text = it.get("nombre") as String
            binding.cityAgenda.text = it.get("ciudad") as String
            binding.fechaAgenda.text = it.get("fecha") as String
            binding.horaAgenda.text = it.get("hora") as String
        }

    }

}