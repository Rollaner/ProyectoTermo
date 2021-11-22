package com.example.timepickerexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timepickerexample.databinding.ActivityMainBinding

class CopiaDelMA : AppCompatActivity() {

    //Seba, puse de nuevo el viewBinding para que el programa funcione, recuerda que en el discord
    //dejé una foto para colocarlo en el build.gradle.app, pero dejo a tu criterio si lo ves
    //necesario dejar el viewBinding o sacarlo al ponerlo en la aplicación

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_main)
        binding.etTime.setOnClickListener { showTimePickerDialog() }

    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected(time:String){
        binding.etTime.setText("Ha seleccionado las $time")
    }
}