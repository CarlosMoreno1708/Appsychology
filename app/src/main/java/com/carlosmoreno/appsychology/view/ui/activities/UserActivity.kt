package com.carlosmoreno.appsychology.view.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    companion object{
        const val EXTRA_USER = "UserActivity:user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra(EXTRA_USER)

        binding.bottomNavegation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->  findNavController(R.id.nav_graph_fragment).navigate(R.id.homeFragment)
            }
            true
        }
//        binding.bottomNavegation.setOnNavigationItemReselectedListener {
//            when(it.itemId){
//                R.id.home->  findNavController(R.id.nav_graph_fragment).navigate(R.id.homeFragment)
//            }
//        }
    }

    override fun onBackPressed() {
        return
    }
}