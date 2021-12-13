package com.example.caloriecountxml


class BMRConfig(private val userStuff: UserData) {

    private var bmr : Double = 0.0

    fun calcularBMR(genero: String) {
        //extraer datos de UserData para rellenar la formula
        bmr = if (genero == "M") { //es algo que se puede hacer
            val mass = userStuff.getMass().toString().toDouble(); val height = userStuff.getHeight().toString().toDouble(); val age = userStuff.getAge().toString().toDouble()
            val constM = 5.0
            (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constM) //formula BMR Hombre
        } else {
            val mass = userStuff.getMass().toString().toDouble(); val height = userStuff.getHeight().toString().toDouble(); val age = userStuff.getAge().toString().toDouble()
            val constF = -161.0
            (10.0*mass/1 + 6.25*height/1 - 5.0*age/1 + constF) //formula BMR Mujer
        }
    }

    fun calcularTDEE() : Double {
        val exerciseCoef = userStuff.getExercise()
        return bmr*exerciseCoef
    }

}