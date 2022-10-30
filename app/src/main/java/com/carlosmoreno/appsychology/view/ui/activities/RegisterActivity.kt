package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton2.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        val result = arrayOf(
            validateName(),
            validateEmail(),
            validateAddress(),
            validatePhone(),
            validateUser(),
            validatePassword()
        )

        if (false in result){
            return
        }
        Toast.makeText(this, "Registro válido", Toast.LENGTH_SHORT).show()

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

    private fun validateEmail(): Boolean {
        val email = binding.emailTextInput.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.emailTextInput.error = "Este campo no puede estar vacío"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailTextInput.error = "Correo electrónico no válido"
            false
        } else {
            binding.emailTextInput.error = null
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
    private fun validateAddress(): Boolean{
        val address = binding.addressTextInput.editText?.text.toString()
        return if (address.isEmpty()){
            binding.addressTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.addressTextInput.error = null
            true
        }

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
    private fun validatePassword(): Boolean{
        val password = binding.passwordTextInput.editText?.text.toString()
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
            binding.passwordTextInput.error = "Este campo no puede estar vacío"
            false
        }else if (!requerimientos.matcher(password).matches()){
            binding.passwordTextInput.error = "Contraseña muy débil"
            false
        }else{
            binding.passwordTextInput.error = null
            true
        }
    }

}