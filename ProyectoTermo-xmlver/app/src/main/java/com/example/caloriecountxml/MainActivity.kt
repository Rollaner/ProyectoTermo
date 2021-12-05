package com.example.caloriecountxml

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import android.widget.TextView
import androidx.preference.PreferenceManager

import android.content.SharedPreferences
import com.example.caloriecountxml.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var calorieCount : Double = 0.0

    private lateinit var timerAux : Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val navView: BottomNavigationView = binding.navView
        /*no se como arreglar el
        nav view, si alguien sabe, sea bienvenido.
        Va a ayudar a que la app sea mas usable*/
        setContentView(R.layout.activity_main)

        /*binding.etDate.setOnClickListener { showDatePickerDialog() }
        Para llamar al showDatePickerDialog, sale con error con y sin el binding para
        llamar la función
         */

        timerAux = Calendar.getInstance()

        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        val dynamic = findViewById<View>(R.id.calorieDinamicText) as TextView
        dynamic.text = "$calorieCount"

        /*val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/ //NAV MENU (ROTO)
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    } //para hacer cambios dinamicos en la app segun las settings, aunque dudo que
    // lo usemos

    @Override
    fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    }
    //complemento al anterior

    override fun onStart() {
        super.onStart()
        val userData = UserData(this)
        val bmrManager = BMRConfig(userData)
        if(userData.getInitialLogin()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        if(timerAux.isWeekend){
            print("yay, calendars!")
        }
        bmrManager.calcularBMR(userData.getGender())
        calorieCount = bmrManager.calcularTDEE()
    }
    //Carga userData y chequea para ver si esta usado (funciona correctamente)


    //showDatePickerDialog debe ser llamada primero para que funcione
    private fun showDatePickerDialog(){
        val datePicker = DatePickerExample { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "DatePicker")

    }

    fun onDateSelected(day: Int, month: Int, year: Int){
        //Se muestra BMR e información del día seleccionado

    }

}