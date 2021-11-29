package com.example.caloriecountxml

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.caloriecountxml.databinding.ActivityLoginBinding
import android.view.View

import android.widget.EditText
import android.widget.RadioButton


class LoginActivity : AppCompatActivity() {
    private lateinit var userData : UserData
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private lateinit  var age : String ; private lateinit  var height : String ; private lateinit  var weight :String
    private lateinit var gender : String; private lateinit var exercise : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val ageEditText = findViewById<View>(R.id.editTextNumber) as EditText
        age = ageEditText.text.toString()
        val weightEditText = findViewById<View>(R.id.editTextNumber2) as EditText
        //los datos estan pasando como strings vacias, el error esta ^ocurriendo alli^
        // probablemente. Puesto que todos tienen valor por defecto
        // (justamente para evitar este tipo de problemas)
        //si alguien se atreve, lo invito a intentr arreglarlo antes que yo lo haga
        //investigar "Como capturar user imput en android"
        weight = weightEditText.text.toString()
        val heightEditText = findViewById<View>(R.id.editTextNumber3) as EditText
        height = heightEditText.text.toString()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //UserData tiene que inciarse una vez la actividad fue creada (contexto no existe antes de eso)
    override fun onStart(){
        super.onStart()
        userData = UserData(this)
    }

    fun radioButtonG(view: View?) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioM ->
                    if (checked) {
                        gender = "M"
                    }
                R.id.radioF ->
                    if (checked) {
                        gender = "F"
                    }
            }
        }
    }

    fun radioButtonE(view: View?) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioSedentary ->
                    if (checked) {
                        exercise = "1.375"
                    }
                R.id.radioAverage ->
                    if (checked) {
                        exercise = "1.55"
                    }
                R.id.radioFitness ->
                    if (checked){
                        exercise = "1.725"
                    }
            }
        }

    }

    fun submitbuttonHandler(view: View?) {
        userData.editAge(age); userData.editHeight(height); userData.editWeight(weight)
        userData.editGender(gender); userData.editExercise(exercise)
        userData.editInitialLogin(false)
        finish()
    }

}