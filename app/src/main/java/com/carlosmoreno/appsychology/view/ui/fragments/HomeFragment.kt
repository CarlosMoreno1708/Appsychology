package com.carlosmoreno.appsychology.view.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.view.ui.activities.LoginActivity
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity.Companion.EXTRA_USER

class HomeFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Inicio"

        val cardPsico = view.findViewById<ImageView>(R.id.cardPsiciologos)
        val cardAgenda = view.findViewById<ImageView>(R.id.cardAgenda)
        val cardMiperfil = view.findViewById<ImageView>(R.id.cardMiPerfil)
        val cardUbicacion = view.findViewById<ImageView>(R.id.cardUbicacion)

        val userText = view.findViewById<TextView>(R.id.userText)

        val dateUser = requireActivity().intent.getStringExtra(EXTRA_USER)

        userText.text ="Usuario: $dateUser"

        cardPsico.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_psychoListFragment)
        }

        cardMiperfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
        }

        cardAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_agendaFragment)
        }

        cardUbicacion.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
        }

        return view
    }
}
