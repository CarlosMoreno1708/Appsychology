package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityForgetKeyBinding
import com.carlosmoreno.appsychology.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ingresarButton.setOnClickListener {
            validate()
        }

        binding.forgetKeyLink.setOnClickListener {
            val intent = Intent(this, ForgetKeyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validate() {
        val userTextInput = binding.userTextInput.editText?.text.toString()
        val passwordTextInput = binding.passwordTextInput.editText?.text.toString()
        val date = getSharedPreferences(userTextInput, Context.MODE_PRIVATE)

        val userDB = date.getString("user","")
        val passwordDB = date.getString("password","")

        val result = arrayOf(validateUser(), validatePassword())

        if (false in result){
            return
        }

        if ((userTextInput == userDB) && (passwordTextInput == passwordDB)){
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra(UserActivity.EXTRA_USER, userTextInput)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
        }
//        Toast.makeText(this, "Datos válidos", Toast.LENGTH_SHORT).show()
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
        return if (password.isEmpty()){
            binding.passwordTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.passwordTextInput.error = null
            true
        }
    }
}