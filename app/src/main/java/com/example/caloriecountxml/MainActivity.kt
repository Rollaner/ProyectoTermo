package com.example.caloriecountxml

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.preference.PreferenceManager //api preferencias actual
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import java.time.*
import com.example.caloriecountxml.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userTDEE : Double = 0.0
    private var totalCalories : Double = 0.0
    private var timerAux = LocalDate.now()
    private var calorieCounter = userTDEE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = binding.navView
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dynamic = findViewById<View>(R.id.calorieDinamicText) as TextView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        dynamic.text = "$userTDEE"
        val calorieSubmitButton: Button = findViewById(R.id.CalorieButton)
        val calorieEditText: EditText = findViewById(R.id.CalorieEditText)
        calorieSubmitButton.setOnClickListener {
            val calorieData: String = calorieEditText.text.toString()
            if (calorieData.isEmpty()) {
                Toast.makeText(applicationContext, "Please enter an amount of calories", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if(calorieCounter > 0)
                    dynamic.text = "$calorieCounter"
                else
                    dynamic.text = "0"
                totalCalories += calorieData.toDouble()
                calorieCounter = userTDEE - totalCalories // x -= y es equivalente a x = x-y
                Toast.makeText(applicationContext, "$calorieData calories", Toast.LENGTH_SHORT).show()
            }
        }
        //trabajo cada 24 horas si y solo si el dispositivo tiene espacio disponible.
        val constraints = Constraints.Builder()
            .setRequiresStorageNotLow(true)
            .build()
        val saveRequest =
            PeriodicWorkRequestBuilder<HistoryWorker>(1, TimeUnit.DAYS)
                // Additional configuration
                .setConstraints(constraints)
                .setInputData(workDataOf(
                    "TDEE" to "$userTDEE",
                    "CALORIES" to "$totalCalories",
                    "DATE" to "$timerAux"
                ))
                .build()
        //pone el proveso de guardado en queue, para ser realizado 1 vez por dia
        WorkManager.getInstance(this).enqueue(saveRequest)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController) //NAV MENU (ROTO)
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
        bmrManager.calcularBMR(userData.getGender())
        userTDEE = bmrManager.calcularTDEE()
    }

}