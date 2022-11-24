package com.carlosmoreno.appsychology.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.ActivityUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var firebaseAuth: FirebaseAuth

    companion object{
        const val EXTRA_EMAIL = "UserActivity:email"
        const val EXTRA_ID = "UserActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        val email = intent.getStringExtra(EXTRA_EMAIL)

        binding.bottomNavegation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->  findNavController(R.id.nav_graph_fragment).navigate(R.id.homeFragment)
                R.id.perfil->  findNavController(R.id.nav_graph_fragment).navigate(R.id.perfilFragment)
                R.id.psicologos->  findNavController(R.id.nav_graph_fragment).navigate(R.id.psychoListFragment)
                R.id.agenda->  findNavController(R.id.nav_graph_fragment).navigate(R.id.agendaFragment)
                R.id.mapa->  findNavController(R.id.nav_graph_fragment).navigate(R.id.mapFragment)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_signOut -> signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut(){
        firebaseAuth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        return
    }
}