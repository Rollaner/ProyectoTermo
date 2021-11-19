package com.example.caloriecount

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

class BMRConfig {

    @Composable
    fun DatosInicio() {
        var edad by remember { mutableStateOf(TextFieldValue("")) }
        var genero by remember { mutableStateOf(TextFieldValue("")) }
        var altura by remember { mutableStateOf(TextFieldValue("")) }
        var peso by remember { mutableStateOf(TextFieldValue("")) }
        Column {
            Text(text = "Ingrese sus datos")
            TextField(
                value = edad,
                onValueChange = { nuevaEdad -> edad = nuevaEdad },
                placeholder = { Text(text = "Edad") },
                label = { Text(text = "Ingrese su Edad") })
            //cambiar genero por equivalente en select
            TextField(
                value = genero,
                onValueChange = { nuevagenero -> genero = nuevagenero },
                placeholder = { Text(text = "Genero") },
                label = { Text(text = "Ingrese su Genero") })
            TextField(
                value = altura,
                onValueChange = { nuevaaltura -> altura = nuevaaltura },
                placeholder = { Text(text = "Altura") },
                label = { Text(text = "Ingrese su Altura") })
            TextField(
                value = peso,
                onValueChange = { nuevapeso -> peso = nuevapeso },
                placeholder = { Text(text = "Peso") },
                label = { Text(text = "Ingrese su Peso") })
        }
        //guardar los datos y actualizar PrimeraSesion
    }

    fun calcularBMR(edad: Int, genero: String, altura: Int, peso: Int) {
        if (genero == "M") {
            println("Man")
        } else {
            println("Women")
        }
    }

}