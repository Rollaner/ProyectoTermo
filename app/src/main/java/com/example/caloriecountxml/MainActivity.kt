package com.example.caloriecountxml

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.preference.PreferenceManager //api preferencias actual
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.work.*
import java.time.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var userTDEE : Double = 0.0
    private var calorieSum : Double = 0.0
    private var remainingCalories = userTDEE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dynamic = findViewById<View>(R.id.calorieDynamicText) as TextView
        val calorieSubmitButton: Button = findViewById(R.id.CalorieButton)
        val calorieEditText: EditText = findViewById(R.id.CalorieEditText)
        calorieSubmitButton.setOnClickListener {
            val calorieData: String = calorieEditText.text.toString()
            if (calorieData.isEmpty()) {
                Toast.makeText(applicationContext, "Please enter an amount of calories", Toast.LENGTH_SHORT)
                    .show()
            } else {
                calorieSum += calorieData.toDouble()
                remainingCalories = userTDEE - calorieSum // x -= y es equivalente a x = x-y
                if(remainingCalories > 0)
                    dynamic.text = "$remainingCalories"
                else
                    dynamic.text = "0"
                Toast.makeText(applicationContext, "Added $calorieData kilocalories", Toast.LENGTH_SHORT).show()
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
                    "CALORIES" to "$calorieSum",
                    "DATE" to "${LocalDate.now().dayOfMonth}"
                ))
                .build()
        //pone el proveso de guardado en queue, para ser realizado 1 vez por dia
        WorkManager.getInstance(this).enqueue(saveRequest)
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    } //para hacer cambios dinamicos en la app segun las settings, aunque dudo que
    // lo usemos
    /*
    @Override
    fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    }
    //complemento al anterior*/

    override fun onStart() {
        super.onStart()
        object : CountDownTimer(9900, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(LocalTime.now().hour == 0)  calorieSum = 0.0
            }
            override fun onFinish() {
                cancel()
                start()
            }
        }.start()

        val userData = UserData(this)
        val bmrManager = BMRConfig(userData)
        if(userData.getInitialLogin()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        bmrManager.calcularBMR(userData.getGender())
        userTDEE = bmrManager.calcularTDEE()
        val dynamic = findViewById<View>(R.id.calorieDynamicText) as TextView
        dynamic.text = "$userTDEE"
    }

    override fun onResume() {
        super.onResume()
        val dynamic = findViewById<View>(R.id.calorieDynamicText) as TextView
        if(userTDEE != remainingCalories) {
            userTDEE = remainingCalories //para mantener consistencia al pausar la app
        }
        if(calorieSum == 0.0){
            val userData = UserData(this)
            val bmrManager = BMRConfig(userData)
            bmrManager.calcularBMR(userData.getGender())
            userTDEE = bmrManager.calcularTDEE()
        }
        dynamic.text = "$userTDEE"
    }

    override fun onPause() {
        super.onPause()
            if(LocalTime.now().hour == 0){
                calorieSum = 0.0
            }
    }

}