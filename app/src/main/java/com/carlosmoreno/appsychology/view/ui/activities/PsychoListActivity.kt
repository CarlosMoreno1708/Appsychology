package com.carlosmoreno.appsychology.view.ui.activities

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosmoreno.appsychology.databinding.ActivityPsychoListBinding
import com.carlosmoreno.appsychology.view.adapter.PsycoAdapter

class PsychoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPsychoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsychoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PsycoAdapter(this)
    }



//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        return super.onKeyDown(keyCode, event)
//    }
}