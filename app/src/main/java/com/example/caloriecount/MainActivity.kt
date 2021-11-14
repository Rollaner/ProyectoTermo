package com.example.caloriecount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caloriecount.ui.theme.CalorieCountTheme

class MainActivity : ComponentActivity() {
    private var primeraSesion: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Cargar Datos Aqui
        //if(Cierta Cond, -> PrimeraSesion = False)
        setContent {
            CalorieCountTheme {
                    datosInicio(primeraSesion)
                    mainApp()
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)) {
        Text(text = "Hello $name!")
    }
}

@Composable
fun mainApp() {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Column{
            //para el lado y hacer text - display - text
            Text(text = "Te quedan")
            //display
            Text(text = "calorias por hoy")
        }
    }
}

@Preview (showBackground = true, widthDp = 320)
@Composable
fun preview(){
    val verdadero = true
    datosInicio(verdadero)
}

@Composable
fun datosInicio(primeraSesion: Boolean){
    if(primeraSesion) {
        var edad  by remember { mutableStateOf(TextFieldValue("")) }
        var genero by remember { mutableStateOf(TextFieldValue("")) }
        var altura by remember { mutableStateOf(TextFieldValue("")) }
        var peso by remember { mutableStateOf(TextFieldValue("")) }
        Column{
            Text(text = "Ingrese sus datos")
            TextField(value = edad, onValueChange = {nuevaEdad -> edad = nuevaEdad}, placeholder = {Text (text="Edad")}, label = {Text (text="Ingrese su Edad")})
            //cambiar genero por equivalente en select
            TextField(value = genero, onValueChange = {nuevagenero -> genero = nuevagenero},placeholder = {Text (text="Genero")},label = {Text (text="Ingrese su Genero")})
            TextField(value = altura, onValueChange = {nuevaaltura -> altura = nuevaaltura},placeholder = {Text (text="Altura")},label = {Text (text="Ingrese su Altura")})
            TextField(value = peso, onValueChange = {nuevapeso -> peso = nuevapeso},placeholder = {Text (text="Peso")},label = {Text (text="Ingrese su Peso")})
        }
        //guardar los datos y actualizar PrimeraSesion
    }
}

fun calcularBMR(edad: Int, genero: String, altura: Int, peso: Int){
    if(genero == "M"){
        println("Man")
    }else{
        println("Women")
    }

}