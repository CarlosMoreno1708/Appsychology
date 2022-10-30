package com.carlosmoreno.appsychology.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityForgetKeyBinding
import java.util.regex.Pattern

class ForgetKeyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetKeyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recuperarButton.setOnClickListener { validate() }
    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validatePassword())
        if (false in result){
            return
        }
        Toast.makeText(this, "Recuperación de contraseña exitoso", Toast.LENGTH_SHORT).show()
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

    private fun validatePassword(): Boolean {
        val password = binding.passwordForgetKey.editText?.text.toString()
        val requerimientos = Pattern.compile(
            "^"+
                    "(?=.*[0-9])" +         //Al menos 1 número
                    "(?=.*[a-z])" +         //Al menos 1 letra minúscula
                    "(?=.*[A-Z])" +         //Al menos 1 letra mayúscula
                    "(?=.*[@#\$%^&+=])" +   //Al menos 1 caracter especial
                    "(?=\\S+$)" +           //sin espacios en blanco
                    ".{6,}" +               //Al menos 6 caracteres
                    "$"
        )

        return if(password.isEmpty()){
            binding.passwordForgetKey.error = "Este campo no puede estar vacío"
            false
        }else if (!requerimientos.matcher(password).matches()){
            binding.passwordForgetKey.error = "Contraseña muy débil"
            false
        }else{
            binding.passwordForgetKey.error = null
            true
        }
    }
}