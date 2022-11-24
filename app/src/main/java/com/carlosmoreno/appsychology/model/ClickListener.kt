package com.carlosmoreno.appsychology.model

import android.view.View

interface ClickListener {
    fun onClic(view: View, position: Int)
}