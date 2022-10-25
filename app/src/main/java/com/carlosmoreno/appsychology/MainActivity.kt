package com.carlosmoreno.appsychology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlosmoreno.appsychology.databinding.ActivityAboutBinding
import com.carlosmoreno.appsychology.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSomos.setOnClickListener{
            startActivity(Intent(this, ActivityAboutBinding::class.java) )
        }
    }
}