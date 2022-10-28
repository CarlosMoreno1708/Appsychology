package com.carlosmoreno.appsychology.view.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.carlosmoreno.appsychology.databinding.ActivityPhotoBinding


class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.captureButton.setOnClickListener {
            requestPermissionLauncher.launch(
                Manifest.permission.CAMERA)
        }

    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            binding.psychoPhotoImage.setImageBitmap(imageBitmap)
        }
    }
//    private val abrir = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result ->
//        if (result.resultCode == RESULT_OK){
//            val data = result.data!!
//            val imageBitmap = data.extras!!.get("data") as Bitmap
//            binding.photoImage.setImageBitmap(imageBitmap)
//        }
//    }

    private fun openCamera(){
        startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            ActivityResultContracts.StartActivityForResult()
        }
    }

}