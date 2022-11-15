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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.emailLoginTextInput.editText
        val password = binding.passwordLoginTextInput.editText
        firebaseAuth = Firebase.auth

        binding.ingresarButton.setOnClickListener {
            validate()
            if(validate()){
                login(email?.text.toString(), password?.text.toString())
            }
        }

        binding.forgetKeyLink.setOnClickListener {
            val intent = Intent(this, ForgetKeyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Metodo para validar los campos del usuario que va hacer login.
    private fun validate(): Boolean {
        val result = arrayOf(validateEmail(), validatePassword())

        return if (false in result){
            false
        }else return true
    }

    private fun validateEmail(): Boolean {
        val email = binding.emailLoginTextInput.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.emailLoginTextInput.error = "Este campo no puede estar vacío"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailLoginTextInput.error = "Correo electrónico no válido"
            false
        } else {
            binding.emailLoginTextInput.error = null
            true
        }
    }

    private fun validatePassword(): Boolean{
        val password = binding.passwordLoginTextInput.editText?.text.toString()
        return if (password.isEmpty()){
            binding.passwordLoginTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.passwordLoginTextInput.error = null
            true
        }
    }


/*    //Metodo para validar usuario por medio de la Database local
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
//      Una vez se hace validación de los campos user y password, se procede a hacer la comparación
//        con algun usuario registrado en la BD, si existe el usuario, se dirije a la vista homeFragment
        if ((userTextInput == userDB) && (passwordTextInput == passwordDB)){
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra(UserActivity.EXTRA_USER, userTextInput)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
        }
//        Toast.makeText(this, "Datos válidos", Toast.LENGTH_SHORT).show()
    }*/

/*     private fun validateUser(): Boolean{
        val user = binding.userTextInput.editText?.text.toString()
        return if (user.isEmpty()){
            binding.userTextInput.error = "Este campo no puede estar vacío"
            false
        }else{
            binding.userTextInput.error = null
            true
        }
    }*/

}