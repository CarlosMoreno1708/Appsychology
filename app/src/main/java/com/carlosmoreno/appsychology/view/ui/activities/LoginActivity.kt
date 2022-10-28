package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityForgetKeyBinding
import com.carlosmoreno.appsychology.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.forgetKeyLink.setOnClickListener {
            val intent = Intent(this, ForgetKeyActivity::class.java)
            startActivity(intent)
        }
    }

}