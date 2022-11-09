package com.carlosmoreno.appsychology.view.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.carlosmoreno.appsychology.BuildConfig
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity
import com.carlosmoreno.appsychology.view.ui.activities.UserActivity.Companion.EXTRA_USER
import java.io.File

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_perfil, container, false)

        val btnCamara = view.findViewById<ImageButton>(R.id.cameraButton)
        val btnGaleria = view.findViewById<ImageButton>(R.id.galeryButton)

        (activity as AppCompatActivity).supportActionBar?.title = "Mi perfil"

        //Metodo para obtener los datos del usuario que inicio sesión.
        getDateUser()

        btnCamara.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(requireActivity().packageManager).also { componentName ->
                    filePhoto()
                    val photoUri: Uri = FileProvider.getUriForFile(
                        requireContext(), BuildConfig.APPLICATION_ID+".fileprovider", file)
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamara.launch(intent)
        }

        btnGaleria.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            galeryCamara.launch(intent)
        }

        return view
    }

    private fun getDateUser() {
        val perfilUserText = view?.findViewById<TextView>(R.id.perfilUserText)
        val perfilNameText = view?.findViewById<TextView>(R.id.perfilNameText)
        val perfilEmailText = view?.findViewById<TextView>(R.id.perfilEmailText)
        val perfilPhoneText = view?.findViewById<TextView>(R.id.perfilPhoneText)
        val perfilAddressText = view?.findViewById<TextView>(R.id.perfilAddressText)

        val dateUser = requireActivity().intent.getStringExtra(EXTRA_USER)

        val date = requireActivity().getSharedPreferences(dateUser, Context.MODE_PRIVATE)

        val userDB = date.getString("user","")
        val nameDB = date.getString("name","")
        val emailDB = date.getString("email","")
        val phoneDB = date.getString("phone","")
        val addressDB = date.getString("address","")

        perfilUserText?.text = userDB
        perfilNameText?.text = nameDB
        perfilEmailText?.text = emailDB
        perfilPhoneText?.text = phoneDB
        perfilAddressText?.text = addressDB

    }

    private lateinit var file: File

    private fun filePhoto(){
        val dir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

    private val openCamara = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            val imageView = view?.findViewById<ImageView>(R.id.imagenFoto)
            val imageBitmap = BitmapFactory.decodeFile(file.toString())
            imageView?.setImageBitmap(imageBitmap)
        }
    }

    private val galeryCamara = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            val imageView = view?.findViewById<ImageView>(R.id.imagenFoto)
            val data = result.data!!
            imageView?.setImageURI(data.data)
        }

    }
}

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        @Suppress("DEPRECATION")
//        super.onActivityResult(requestCode, resultCode, data)
//        val imageView = view?.findViewById<ImageView>(R.id.imagenFoto)
//        if(requestCode == 123){
//            val imageBitmap = BitmapFactory.decodeFile(file.toString())
//            imageView?.setImageBitmap(imageBitmap)
////            val bitmap = data?.extras?.get("data") as Bitmap
////            imageView?.setImageBitmap(bitmap)
//        }else if(requestCode == 456){
//            imageView?.setImageURI(data?.data)
//        }
//    }