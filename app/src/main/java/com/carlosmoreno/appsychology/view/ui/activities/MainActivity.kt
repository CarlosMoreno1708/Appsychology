package com.carlosmoreno.appsychology.view.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlosmoreno.appsychology.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aboutButton.setOnClickListener{
            navegateTo(PhotoActivity())
        }

        binding.loginButton.setOnClickListener{
            navegateTo(LoginActivity())
        }

        binding.registerButton.setOnClickListener {
            navegateTo(RegisterActivity())
        }
    }

    private fun navegateTo(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}