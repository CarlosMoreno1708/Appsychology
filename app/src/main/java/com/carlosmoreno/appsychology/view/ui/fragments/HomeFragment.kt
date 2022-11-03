package com.carlosmoreno.appsychology.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.view.ui.activities.LoginActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val cerrarSesionButton = view.findViewById<Button>(R.id.cerrarSesionButton)

        cerrarSesionButton.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardPsico = view.findViewById<ImageView>(R.id.cardPsiciologos)

        cardPsico.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
        }

    }

}