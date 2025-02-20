package com.frost.learndemo3

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.frost.learndemo3.ui.theme.LearnDemo3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnDemo3Theme {
                calculatorBody()
                }
            }
        }
    }

fun calcuateResult(value1: Double, value2: Double, operator: Int): Double{
    when{
        operator == 1 -> return value1 + value2
        operator == 2 -> return value1 - value2
        operator == 3 -> return value1 * value2
        operator == 4 -> return value1 / value2
        else -> return 0.0
    }
}


@Composable
fun calculatorBody(){

    val valueEntered = remember { mutableStateOf("") }
    val value1 = remember { mutableDoubleStateOf(0.0) }
    val operator = remember { mutableIntStateOf(0) }
    val result = remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculator")
        OutlinedTextField(value = "${valueEntered.value}", onValueChange = {})
        Row {
            Button(onClick = {valueEntered.value += "1"}){
                Text(text = "1")
            }
            Button( onClick = {valueEntered.value += "2"}){
                Text(text = "2")
            }
            Button(onClick = {valueEntered.value += "3"}){
                Text(text = "3")
            }
            Button(onClick = {
                value1.doubleValue = valueEntered.value.toDouble()
                valueEntered.value = ""
                operator.intValue = 1
            }){
                Text(text = "+")
            }
        }
        Row {
            Button(onClick = {valueEntered.value += "4"}){
                Text(text = "4")
            }
            Button(onClick = {valueEntered.value += "5"}){
                Text(text = "5")
            }
            Button(onClick = {valueEntered.value += "6"}){
                Text(text = "6")
            }
            Button(onClick = {
                value1.doubleValue = valueEntered.value.toDouble()
                valueEntered.value = ""
                operator.intValue = 2
            }){
                Text(text = "-")
            }
        }
        Row {
            Button(onClick = {valueEntered.value += "7"}){
                Text(text = "7")
            }
            Button(onClick = {valueEntered.value += "8"}){
                Text(text = "8")
            }
            Button(onClick = {valueEntered.value += "9"}){
                Text(text = "9")
            }
            Button(onClick = {
                value1.doubleValue = valueEntered.value.toDouble()
                valueEntered.value = ""
                operator.intValue = 3
            }){
                Text(text = "*")
            }
        }
        Row(

            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                value1.doubleValue = calcuateResult(value1.doubleValue, valueEntered.value.toDouble(), operator.value)
                result.value = value1.doubleValue.toString()
                valueEntered.value = result.value
            }){
                Text(text = "=")
            }
            Button(onClick = {valueEntered.value += "0"}){
                Text(text = "0")
            }
            Button(onClick = {if(!valueEntered.value.contains('.')) {
                valueEntered.value += "."
            }}){
                Text(text = ".")
            }
            Button(onClick = {
                value1.doubleValue = valueEntered.value.toDouble()
                valueEntered.value = ""
                operator.intValue = 4
            }){
                Text(text = "/")
            }
        }
        Row(

            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                value1.doubleValue = 0.0
                valueEntered.value = ""
                result.value = ""
            }){
                Text(text = "Clear")
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnDemo3Theme {
        calculatorBody()
    }
}