package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.carlosmoreno.appsychology.databinding.ActivityForgetKeyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgetKeyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetKeyBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth
        val email = binding.emailForgetKey.editText

        binding.recuperarButton.setOnClickListener {
            validateEmail()
            if (validateEmail()){
                resetPassword(email?.text.toString())
            }
        }
    }

    private fun resetPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext, "Correo de cambio de contraseña enviado", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(baseContext, "Error, correo no existe", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateEmail(): Boolean {
        val email = binding.emailForgetKey.editText?.text.toString()
        return if(email.isEmpty()){
            binding.emailForgetKey.error = "Este campo no puede estar vacío"
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailForgetKey.error = "Correo electrónico no válido"
            false
        }else{
            binding.emailForgetKey.error = null
            true
        }
    }

//    private fun validate() {
////        val result = arrayOf(validateEmail(), validatePassword())
////        if (false in result){
////            return
////        }
//
//        }
}