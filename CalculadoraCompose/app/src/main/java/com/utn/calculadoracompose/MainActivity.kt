package com.utn.calculadoracompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                CalculadoraApp()
            }
        }
    }
}

@Composable
fun CalculadoraApp() {

    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    fun obtenerNumeros(): Pair<Double?, Double?> {
        val n1 = numero1.toDoubleOrNull()
        val n2 = numero2.toDoubleOrNull()
        return Pair(n1, n2)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(onClick = {
                val (n1, n2) = obtenerNumeros()
                resultado =
                    if (n1 != null && n2 != null)
                        (n1 + n2).toString()
                    else "Error"
            }) {
                Text("+")
            }

            Button(onClick = {
                val (n1, n2) = obtenerNumeros()
                resultado =
                    if (n1 != null && n2 != null)
                        (n1 - n2).toString()
                    else "Error"
            }) {
                Text("-")
            }

            Button(onClick = {
                val (n1, n2) = obtenerNumeros()
                resultado =
                    if (n1 != null && n2 != null)
                        (n1 * n2).toString()
                    else "Error"
            }) {
                Text("×")
            }

            Button(onClick = {
                val (n1, n2) = obtenerNumeros()
                resultado =
                    if (n1 != null && n2 != null && n2 != 0.0)
                        (n1 / n2).toString()
                    else "Error"
            }) {
                Text("÷")
            }
        }

        Text(
            text = "Resultado: $resultado",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}