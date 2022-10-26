package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.setAnimation(R.raw.animation)
        binding.animationView.playAnimation()

        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(
            {
                val intent = Intent( this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000
        )
    }
}