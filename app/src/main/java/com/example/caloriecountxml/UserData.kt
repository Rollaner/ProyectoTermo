package com.example.caloriecountxml

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

//guarda datos usuario, de momento preferencias (config y datos BMR)

class UserData(context:Context) {

    private val PREFERENCENAME = "UserPrefs"
    private val INITIALLOGIN = "InitialLogin"
    private val GENDER = "Genero"; private val AGE = "Edad"; private val HEIGHT = "Altura"
    private val MASS = "Peso"; private val EXERCISE = "NivelActividad"

    private var userPrefs : SharedPreferences = context.getSharedPreferences(PREFERENCENAME, MODE_PRIVATE) //conseguir
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

    fun getAge(): String?{
        return userPrefs.getString(AGE,"1")
    }

    fun getGender():String{
        return userPrefs.getString(GENDER, "M").toString()
    }

    fun getHeight() : String?{
        return userPrefs.getString(HEIGHT,"1")
    }


    fun getMass() : String? {
        return userPrefs.getString(MASS,"1")
    }

    fun editAge(value : String ){
        val prunedValue = value.filter { it.isDigit() }
        val editor = userPrefs.edit()
        editor.putString(AGE, prunedValue)
        editor.apply()
    }

    fun editHeight(value : String){
        val prunedValue = value.filter { it.isDigit() }
        val editor = userPrefs.edit()
        editor.putString(HEIGHT,prunedValue)
        editor.apply()
    }

    fun editWeight(value : String){
        val prunedValue = value.filter { it.isDigit() }
        val editor = userPrefs.edit()
        editor.putString(MASS,prunedValue)
        editor.apply()
    }

    fun editGender(value : String){
        val pruneValue = value.filter { it.isUpperCase() }
        val editor = userPrefs.edit()
        editor.putString(GENDER, pruneValue)
        editor.apply()
    }

    //puede retornar cualquier valor que necesitemos, asi que parseemos aqui


}