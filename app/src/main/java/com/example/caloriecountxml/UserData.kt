package com.example.caloriecountxml

import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue

//guarda datos usuario, de momento preferencias (config y datos BMR)

class UserData(context:Context) {

    private val PREFERENCENAME = "UserPrefs"
    private val INITIALLOGIN = "InitialLogin"
    private val GENDER = "Genero"; private val AGE = "Edad"; private val HEIGHT = "Altura"
    private val MASS = "Peso"; private val EXERCISE = "NivelActividad"

    private var userPrefs = context.getSharedPreferences(PREFERENCENAME, Context.MODE_PRIVATE) //conseguir
    //contexto preferencia

    fun getInitialLogin() : Boolean{
        return userPrefs.getBoolean(INITIALLOGIN, true) /*lee preferencia InitialLogin de
        contexto UserPrefs*/
    }

    fun editInitialLogin(value: Boolean){
        val editor = userPrefs.edit()
        editor.putBoolean(INITIALLOGIN,value)
        editor.apply()
    }

    fun getExercise() : Double{
        return userPrefs.getString(EXERCISE, "1.0")!!.toDouble() //!! significa not-null, indica que la expresion nunca es NULL
    }

    fun editExercise(value : String){
        val editor = userPrefs.edit()
        editor.putString(EXERCISE,value)
        editor.apply()
    }

    fun getAge(): Double{
        return userPrefs.getInt(AGE,1).toDouble()
    }

    fun getHeight() : Double{
        return userPrefs.getInt(HEIGHT,1).toDouble()
    }

    fun getMass() : Double{
        return userPrefs.getInt(MASS,1).toDouble()
    }

    fun editAge(value : TextFieldValue){
        var prunedValue = value.toString().filter { it.isDigit() }
        val editor = userPrefs.edit()
        editor.putInt(AGE, prunedValue.toInt())
        editor.apply()
    }

    fun editHeight(value: TextFieldValue){
        var prunedValue = value.toString().filter { it.isDigit() }
        val editor = userPrefs.edit()
        editor.putInt(HEIGHT,prunedValue.toInt())
        editor.apply()
    }

    fun editWeight(value: TextFieldValue){
        var prunedValue = value.toString().filter { it.isDigit() }
        val editor = userPrefs.edit()
        editor.putInt(MASS,prunedValue.toInt())
        editor.apply()
    }

    //puede retornar cualquier valor que necesitemos, asi que parseemos aqui


}