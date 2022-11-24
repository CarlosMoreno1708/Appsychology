package com.carlosmoreno.appsychology.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.FragmentEditUserBinding
import com.carlosmoreno.appsychology.databinding.FragmentHomeBinding
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class EditUserFragment : Fragment() {

    private lateinit var binding: FragmentEditUserBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditUserBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Mi perfil"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userEmail: String? = requireActivity().intent.getStringExtra(UserActivity.EXTRA_EMAIL)
        val id: String? = requireActivity().intent.getStringExtra(UserActivity.EXTRA_ID).toString()

        binding.emailEditText.editText?.setText(userEmail)

        getDateUserFb(id?:"")

        binding.btnCancelEditUser.setOnClickListener {
            findNavController().navigate(R.id.action_editUserFragment_to_perfilFragment)
        }
        binding.btnSaveEditUser.setOnClickListener {
            saveDateUserFb(id?:"")
            Toast.makeText(context,"Datos guardados", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editUserFragment_to_perfilFragment)
        }


    }

    private fun getDateUserFb(id: String) {

        db.collection("usuarios").document(id).get().addOnSuccessListener {
            binding.userEditText.editText?.setText(it.get("usuario") as String)
            binding.nameEditText.editText?.setText(it.get("nombre") as String)
            binding.lastNameEditText.editText?.setText(it.get("apellido") as String)
            binding.phoneEditText.editText?.setText(it.get("telefono") as String)
        }
    }

    //  Se almacena los datos del usuario en Firebase.
    private fun saveDateUserFb(id: String){
        db.collection("usuarios").document(id).set(
            hashMapOf(
                "usuario" to binding.userEditText.editText?.text.toString(),
                "nombre" to binding.nameEditText.editText?.text.toString(),
                "apellido" to binding.lastNameEditText.editText?.text.toString(),
                "correo" to binding.emailEditText.editText?.text.toString(),
                "telefono" to binding.phoneEditText.editText?.text.toString()
            )
        )
    }

}