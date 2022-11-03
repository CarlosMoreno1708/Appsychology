package com.carlosmoreno.appsychology.model

import com.carlosmoreno.appsychology.R

object DataSource {
    val psycologos: MutableList<Psicologos> = mutableListOf(
        Psicologos(
            "Psicología de Pareja",
            "Carmen Perez",
            R.drawable.psicologa2
        ),
        Psicologos(
            "Psicología de Familia",
            "Jose Castillo",
            R.drawable.psicologo1
        ),
        Psicologos(
            "Psicología Clínica",
            "Maria Suarez",
            R.drawable.psicologa2
        ),
        Psicologos(
            "Psicología Militar",
            "Andres Lopez",
            R.drawable.psicologo1
        ),
        Psicologos(
            "Neuropsicología",
            "Julieta Rodriguez",
            R.drawable.psicologa2
        )
    )
}
