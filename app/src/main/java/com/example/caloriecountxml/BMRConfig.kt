package com.example.caloriecountxml

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview


class BMRConfig(val userStuff: UserData) {

    fun calcularBMR(genero: String): Double {
        var result = 0.0
        //extraer datos de UserData para rellenar la formula
        if (genero == "M") {
            val mass = userStuff.getMass(); val height = userStuff.getHeight(); val age = userStuff.getAge(); val constM = 5.0
            result = (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constM) //formula BMR Hombre
        } else {
            val mass = userStuff.getMass(); val height = userStuff.getHeight(); val age = userStuff.getAge(); val constF = -161.0
            result = (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constF) //formula BMR Mujer
        }
        return result
    }

    fun calcualrTDEE(bmr: Double) : Double {
        val exerciseCoef = userStuff.getExercise()
        return bmr*exerciseCoef
    }

}