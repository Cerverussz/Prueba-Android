package co.com.ceiba.mobile.pruebadeingreso.core

import android.view.View

internal infix fun View.onClick(function: () -> Unit) {
    setOnClickListener { function() }
}