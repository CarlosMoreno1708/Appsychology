package com.carlosmoreno.appsychology.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.R.layout
import com.carlosmoreno.appsychology.databinding.FragmentHomeBinding
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity.Companion.EXTRA_ID
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Inicio"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userID: String? = requireActivity().intent.getStringExtra(EXTRA_ID)

        db.collection("usuarios").document(userID?:"").get().addOnSuccessListener {
            binding.userText.text = it.get("usuario") as String
        }

        binding.cardPsiciologos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_psychoListFragment)
        }

        binding.cardMiPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
        }

        binding.cardAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_agendaFragment)
        }

        binding.cardUbicacion.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
        }

    }
}
