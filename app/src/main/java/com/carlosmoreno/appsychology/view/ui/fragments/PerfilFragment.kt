package com.carlosmoreno.appsychology.view.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.BuildConfig
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.FragmentPerfilBinding
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity.Companion.EXTRA_EMAIL
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity.Companion.EXTRA_ID
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
//        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Mi perfil"
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userEmail = requireActivity().intent.getStringExtra(EXTRA_EMAIL)
        val userID: String? = requireActivity().intent.getStringExtra(EXTRA_ID)
        binding.perfilEmailText.text = userEmail

        //Metodo para obtener los datos del usuario que inicio sesión.
        getDateUserFb(userID?:"")

        binding.cameraButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(requireActivity().packageManager).also { componentName ->
                    filePhoto()
                    val photoUri: Uri = FileProvider.getUriForFile(
                        requireContext(), BuildConfig.APPLICATION_ID + ".fileprovider", file
                    )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamara.launch(intent)
        }

        binding.galeryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            galeryCamara.launch(intent)
        }

        binding.btnEditUser.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_editUserFragment)
        }

    }

    //    Metodo para obtener los datos de usuario desde Firebase
    private fun getDateUserFb(id: String) {

        db.collection("usuarios").document(id).get().addOnSuccessListener {
            binding.perfilUserText.text = it.get("usuario") as String
            binding.perfilNameText.text = it.get("nombre") as String
            binding.perfilLastNameText.text = it.get("apellido") as String
            binding.perfilPhoneText.text = it.get("telefono") as String
        }
    }

    private lateinit var file: File

    private fun filePhoto() {
        val dir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

    private val openCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageView = view?.findViewById<ImageView>(R.id.imagenFoto)
                val imageBitmap = BitmapFactory.decodeFile(file.toString())
                imageView?.setImageBitmap(imageBitmap)
            }
        }

    private val galeryCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
//                val imageView = view?.findViewById<ImageView>(R.id.imagenFoto)
                val data = result.data!!
                binding.imagenFoto.setImageURI(data.data)
            }
        }
}

//    private fun getDateUser() {
//        val perfilUserText = view?.findViewById<TextView>(R.id.perfilUserText)
//        val perfilNameText = view?.findViewById<TextView>(R.id.perfilNameText)
//        val perfilEmailText = view?.findViewById<TextView>(R.id.perfilEmailText)
//        val perfilPhoneText = view?.findViewById<TextView>(R.id.perfilPhoneText)
//
//        val dateUser = requireActivity().intent.getStringExtra(EXTRA_EMAIL)
//
//        val date = requireActivity().getSharedPreferences(dateUser, Context.MODE_PRIVATE)
//
//        val userDB = date.getString("user","")
//        val nameDB = date.getString("name","")
//        val emailDB = date.getString("email","")
//        val phoneDB = date.getString("phone","")
//
//        perfilUserText?.text = userDB
//        perfilNameText?.text = nameDB
//        perfilEmailText?.text = emailDB
//        perfilPhoneText?.text = phoneDB
//
//    }
