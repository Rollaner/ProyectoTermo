package com.example.caloriecount

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

class BMRConfig(userStuff: UserData) {


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

    fun calcularBMR(edad: Int, genero: String, altura: Int, peso: Int): Double {
        var result = 0.0
        //extraer datos de UserData para rellenar la formula
        if (genero == "M") {
            val mass = 1; val height = 1; val age = 1; val constM = 5.0
            result = (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constM) //formula BMR Hombre
        } else {
            val mass = 1; val height = 1; val age = 1; val constF = -161.0
            result = (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constF) //formula BMR Mujer
        }
        return result
    }

}