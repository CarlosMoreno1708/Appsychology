package com.carlosmoreno.appsychology.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityForgetKeyBinding

class ForgetKeyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetKeyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}