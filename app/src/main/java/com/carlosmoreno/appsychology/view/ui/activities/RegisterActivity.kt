package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.carlosmoreno.appsychology.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.emailTextInput.editText
        val password = binding.passwordTextInput.editText

        firebaseAuth= Firebase.auth
        binding.registerButton2.setOnClickListener {
            validate()
            if(validate()){
                createUser(email?.text.toString(), password?.text.toString())
            }
        }
    }

//  Con el sigte metodo se crea un usuario en la BD de firebase.
    private fun createUser(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {Task ->
                if(Task.isSuccessful){
                    Toast.makeText(baseContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    //Para navegar a la activity LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(baseContext, "Error de registro", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validate(): Boolean {
      /*  val result = arrayOf(
            validateName(),
            validateEmail(),
            validateAddress(),
            validatePhone(),
            validateUser(),
            validatePassword()
        )*/
        val result = arrayOf(
            validateEmail(),
            validatePassword()
        )

        return if (false in result){
            false
        }else return true

        //registerUser()
    }

    //Con el sigte metodo se crea un usuario y se almacena en la BD local del teléfono con
    //shared preferences
    private fun registerUser(){
        val user = binding.userTextInput.editText?.text.toString()
        val password = binding.passwordTextInput.editText?.text.toString()
        val name = binding.nameTextInput.editText?.text.toString()
        val email = binding.emailTextInput.editText?.text.toString()
        val phone = binding.phoneTextInput.editText?.text.toString()
        val address = binding.addressTextInput.editText?.text.toString()

        val date = getSharedPreferences(user, Context.MODE_PRIVATE)
        val edit = date.edit()

        edit.apply{
            putString("user", user)
            putString("password", password)
            putString("name", name)
            putString("email", email)
            putString("phone", phone)
            putString("address", address)
            commit()
        }

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        //Para navegar a la activity LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
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
        }else if(password.length < 6){
            binding.passwordTextInput.error = "Debe tener al menos 6 caracteres"
            false
        } else if (!requerimientos.matcher(password).matches()){
            binding.passwordTextInput.error = "Contraseña muy débil"
            false
        }else{
            binding.passwordTextInput.error = null
            true
        }
    }

}