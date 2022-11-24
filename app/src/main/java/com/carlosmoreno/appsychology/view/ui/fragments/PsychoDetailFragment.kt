package com.carlosmoreno.appsychology.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.FragmentPsychoDetailBinding
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity
import com.google.firebase.firestore.FirebaseFirestore

class PsychoDetailFragment : Fragment() {

    private lateinit var binding: FragmentPsychoDetailBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPsychoDetailBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Detalle"

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getStringExtra(UserActivity.EXTRA_ID)

        parentFragmentManager.setFragmentResultListener("key", this, FragmentResultListener {
            requestKey, bundle ->
                with(binding){
                    titleDetail.text = bundle.getString("perfil").toString()
                    nameDetail.text = bundle.getString("nombre").toString()
                    cityDetail.text = "Ciudad: ${bundle.getString("ciudad").toString()}"
                    descriptionDetail.text = bundle.getString("descripcion").toString()
                }
                Glide.with(requireContext()).load(bundle.getString("foto")).into(binding.photoDetail)
        })

        binding.btnAgendar.setOnClickListener {
            agendaFb(id?:"")
            findNavController().navigate(R.id.action_psychoDetailFragment_to_homeFragment)
        }
    }

    private fun agendaFb(id: String){
        db.collection("agenda").document(id).set(
            hashMapOf(
                "perfil" to binding.titleDetail.text.toString(),
                "nombre" to binding.nameDetail.text.toString(),
                "ciudad" to binding.cityDetail.text.toString(),
                "fecha" to "23/11/2022",
                "hora" to "08:00 AM"
            )
        )
        Toast.makeText(requireContext(),"Cita programada", Toast.LENGTH_SHORT).show()
    }

}