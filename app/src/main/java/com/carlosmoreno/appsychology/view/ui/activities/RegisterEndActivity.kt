package com.carlosmoreno.appsychology.view.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.carlosmoreno.appsychology.databinding.ActivityRegisterEndBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegisterEndActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEndBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")

        binding.btnSaveDateUser.setOnClickListener {
            validate()
            if (validate()){
                saveDateUserFb(id?:"")
                //Para navegar a la activity Login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun saveDateUserFb(id: String){
        db.collection("usuarios").document(id).set(
            hashMapOf(
                "usuario" to binding.userTextInput.editText?.text.toString(),
                "nombre" to binding.nameTextInput.editText?.text.toString(),
                "apellido" to binding.lastNameTextInput.editText?.text.toString(),
                "telefono" to binding.phoneTextInput.editText?.text.toString()
            )
        )
        Toast.makeText(this,"Datos guardados", Toast.LENGTH_SHORT).show()
    }

    private fun validate(): Boolean {
          val result = arrayOf(
              validateName(),
              validateLastName(),
              validatePhone(),
              validateUser(),
          )

        return if (false in result){
            false
        }else return true
    }


    private fun validateUser(): Boolean{
        val user = binding.userTextInput.editText?.text.toString()
        return if (user.isEmpty()){
            binding.userTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.userTextInput.error = null
            true
        }
    }

    private fun validateName():Boolean{
        val name = binding.nameTextInput.editText?.text.toString()
        return if (name.isEmpty()){
            binding.nameTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.nameTextInput.error = null
            true
        }
    }

    private fun validateLastName():Boolean{
        val name = binding.lastNameTextInput.editText?.text.toString()
        return if (name.isEmpty()){
            binding.lastNameTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.lastNameTextInput.error = null
            true
        }
    }



    private fun validatePhone(): Boolean{
        val phone = binding.phoneTextInput.editText?.text.toString()
        return if (phone.isEmpty()){
            binding.phoneTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.phoneTextInput.error = null
            true
        }
    }


    override fun onBackPressed() {
        return
    }
}