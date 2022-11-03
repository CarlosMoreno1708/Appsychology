package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.carlosmoreno.appsychology.BuildConfig
import com.carlosmoreno.appsychology.databinding.ActivityPhotoBinding
import java.io.File


class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.photoButton.setOnClickListener {
//            openCamara.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { componentName ->
                    filePhoto()
                    val photoUri: Uri = FileProvider.getUriForFile(
                        this, BuildConfig.APPLICATION_ID+".fileprovider", file)
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamara.launch(intent)
        }

    }

    private val openCamara = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
//            val data = result.data!!
//            val imageBitmap = data.extras!!.get("data") as Bitmap
            val imageBitmap = BitmapFactory.decodeFile(file.toString())
            binding.photoImage.setImageBitmap(imageBitmap)
        }
    }

    private lateinit var file: File

    private fun filePhoto(){
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

/*    private fun createContent(): ContentValues {
        val nameFile = file.name
        val typeFile = "image/jpg"
        return ContentValues().apply{
            put(MediaStore.MediaColumns.DISPLAY_NAME, nameFile)
            put(MediaStore.Files.FileColumns.MIME_TYPE, typeFile)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING,1)
        }
    }

    private fun saveContent(content: ContentValues): Uri{
        var outputStream: OutputStream?
        var uri: Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use {
            output ->
            mapeoImagen().compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        return uri!!
        }


    private fun mapeoImagen(): Bitmap{
        return BitmapFactory.decodeFile(file.toString())
    }



    private fun saveImage(){
        val save = createContent()
//        val uri = saveContent(content )
    }




//    private val requestPermissionLauncher =
//        registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
//            } else {
//                // Explain to the user that the feature is unavailable because the
//                // features requires a permission that the user has denied. At the
//                // same time, respect the user's decision. Don't link to system
//                // settings in an effort to convince the user to change their
//                // decision.
//            }
//        }
//
//    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result: ActivityResult ->
//        if (result.resultCode == Activity.RESULT_OK){
//            val intent = result.data
//            val imageBitmap = intent?.extras?.get("data") as Bitmap
//            binding.userPhotoImage.setImageBitmap(imageBitmap)
//        }
//    }
//    private fun openCamera(){
//        startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (intent.resolveActivity(packageManager) != null) {
//            ActivityResultContracts.StartActivityForResult()
//        }
//    }

 */
}