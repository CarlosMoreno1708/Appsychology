package com.carlosmoreno.appsychology.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
         super.onBackPressed()
    }
}