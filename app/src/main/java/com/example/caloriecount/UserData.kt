package com.example.caloriecount

import android.content.Context

//guarda datos usuario, de momento preferencias (config y datos BMR)

class UserData(context:Context) {

    private val PREFERENCE_NAME = "UserPrefs"
    private val INITIAL_LOGIN = "InitialLogin"

    private var UserPrefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE) //conseguir
    //contexto preferencia

    fun getInitialLogin() : Boolean{
        return UserPrefs.getBoolean(INITIAL_LOGIN, true) //lee preferencia InitialLogin de
        // contexto UserPrefs
    }

    fun editInitialLogin(value: Boolean){
        val editor = UserPrefs.edit()
        editor.putBoolean(INITIAL_LOGIN,value)
        editor.apply()
    }

    //puede retornar cualquier valor que necesitemos, asi que parseemos aqui


}