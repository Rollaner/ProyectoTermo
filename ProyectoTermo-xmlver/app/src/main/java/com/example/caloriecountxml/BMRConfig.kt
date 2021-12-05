package com.example.caloriecountxml


class BMRConfig(private val userStuff: UserData) {

    private var BMR : Double = 0.0;

    fun calcularBMR(genero: String) {
        //extraer datos de UserData para rellenar la formula
        if (genero == "M") {
            val mass = userStuff.getMass().toDouble(); val height = userStuff.getHeight().toDouble(); val age = userStuff.getAge().toDouble()
            val constM = 5.0
            BMR = (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constM) //formula BMR Hombre
        } else {
            val mass = userStuff.getMass().toDouble(); val height = userStuff.getHeight().toDouble(); val age = userStuff.getAge().toDouble()
            val constF = -161.0
            BMR = (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constF) //formula BMR Mujer
        }
    }

    fun calcularTDEE() : Double {
        val exerciseCoef = userStuff.getExercise()
        return BMR*exerciseCoef
    }

}