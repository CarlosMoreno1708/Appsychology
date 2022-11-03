package com.carlosmoreno.appsychology.model

import androidx.annotation.DrawableRes

data class Psicologos(
    val perfil:String,
    val name: String,
    @DrawableRes var photo: Int)
