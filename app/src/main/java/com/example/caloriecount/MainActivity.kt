package com.example.caloriecount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.caloriecount.ui.theme.CalorieCountTheme

//pega todos los otros modulos

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPrefs = UserData(this)
        val userHistory = UserHistory()
        val dailyScreen = DailyScreen()
        val bmrManager = BMRConfig()
        setContent {
            CalorieCountTheme {
                Surface(color = MaterialTheme.colors.background) {
                    if (userPrefs.getInitialLogin()) {
                        bmrManager.DatosInicio()
                    } else {
                        dailyScreen.MainApp()
                    }
                }
            }
        }
    }


    @Preview(showBackground = true, widthDp = 320)
    @Composable
    fun Preview() {
        val bmrManager = BMRConfig()
        bmrManager.DatosInicio()
    }


}    