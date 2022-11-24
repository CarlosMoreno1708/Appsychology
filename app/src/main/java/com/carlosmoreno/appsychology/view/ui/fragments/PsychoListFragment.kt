package com.carlosmoreno.appsychology.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosmoreno.appsychology.R
import com.carlosmoreno.appsychology.databinding.FragmentPsychoListBinding
import com.carlosmoreno.appsychology.model.ClickListener
import com.carlosmoreno.appsychology.view.adapter.PsycoAdapter
import com.carlosmoreno.appsychology.viewmodel.PsychologyViewModel


class PsychoListFragment : Fragment() {

    private lateinit var binding: FragmentPsychoListBinding
    private lateinit var adapter: PsycoAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(PsychologyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPsychoListBinding.inflate(inflater, container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Nuestros psicólogos"

        observeData()

        adapter = PsycoAdapter(requireContext(), object : ClickListener {
            override fun onClic(view: View, position: Int) {

                val foto = adapter.listPsico[position].foto
                val perfil = adapter.listPsico[position].perfil
                val nombre = adapter.listPsico[position].nombre
                val descripcion = adapter.listPsico[position].descripcion
                val ciudad = adapter.listPsico[position].ciudad

                val datosAEnviar = Bundle()

                datosAEnviar.putString("foto", foto)
                datosAEnviar.putString("perfil", perfil)
                datosAEnviar.putString("nombre", nombre)
                datosAEnviar.putString("descripcion", descripcion)
                datosAEnviar.putString("ciudad", ciudad)
                parentFragmentManager.setFragmentResult("key", datosAEnviar)

//                Toast.makeText(requireContext(), adapter.listPsico[position].perfil, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_psychoListFragment_to_psychoDetailFragment)
            }

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

//    Por medio de esta función se llama al viewModel para traer la informacion que se ejecuta la
//    informacion atraves del viewModel,  que hace referencia a la que esta en Firebase.
    private fun observeData() {
        viewModel.psychoData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}