package com.example.caloriecountxml

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

//pantalla principal de la app, solo UI y recoleccion de input, no guarda nada

class DailyScreen {

    @Composable
    fun MainApp() {
        Column {
            //para el lado y hacer text - display - text
            Text(text = "Te quedan")
            //display
            Text(text = "calorias por hoy")
        }
    }
}