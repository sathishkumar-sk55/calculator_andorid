package com.frost.learndemo3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

fun calcuateResult(value1: Double, value2: Double, operator: Int): Double {
    when {
        operator == 1 -> return value1 + value2
        operator == 2 -> return value1 - value2
        operator == 3 -> return value1 * value2
        operator == 4 -> return value1 / value2
        else -> return 0.0
    }
}


@Composable
fun calculatorBody() {


    val valueEntered = remember { mutableStateOf("") }
    val value1 = remember { mutableDoubleStateOf(0.0) }
    val operator = remember { mutableIntStateOf(0) }
    val result = remember { mutableStateOf("") }

    @Composable
    fun numberButton(onClick: () -> Unit, text: String) {
        Button(
            onClick,
            shape = RectangleShape,
            modifier = Modifier
                .padding(1.dp)
                .height(100.dp)
                .width(125.dp)
                .border(1.dp, androidx.compose.ui.graphics.Color.Black)
        ) {
            Text(
                text = text,
                fontSize = 30.sp
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = "${valueEntered.value}", onValueChange = {},
            readOnly = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 30.sp,
                color = Black
            ),
            modifier = Modifier
                .heightIn(150.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .absolutePadding(bottom = 20.dp)
        )
        Row {
            numberButton(onClick = { valueEntered.value += "1" }, text = "1")
            numberButton(onClick = { valueEntered.value += "2" }, text = "2")
            numberButton(onClick = { valueEntered.value += "3" }, text = "3")
            numberButton(onClick = {
                if (!valueEntered.value.isBlank()) {

                    Log.d("MainActivity", "ValueEntereted not empty")
                    value1.doubleValue = valueEntered.value.toDouble()
                    valueEntered.value = ""
                    operator.intValue = 1
                }
            }, text = "+")
        }
        Row {
            numberButton(onClick = { valueEntered.value += "4" }, text = "4")
            numberButton(onClick = { valueEntered.value += "5" }, text = "5")
            numberButton(onClick = { valueEntered.value += "6" }, text = "6")
            numberButton(onClick = {
                if (!valueEntered.value.isBlank()) {

                    Log.d("MainActivity", "ValueEntereted not empty")
                    value1.doubleValue = valueEntered.value.toDouble()
                    valueEntered.value = ""
                    operator.intValue = 2
                }
            }, text = "-")
        }
        Row {
            numberButton(onClick = { valueEntered.value += "7" }, text = "7")
            numberButton(onClick = { valueEntered.value += "8" }, text = "8")
            numberButton(onClick = { valueEntered.value += "9" }, text = "9")
            numberButton(onClick = {
                if (!valueEntered.value.isBlank()) {

                    Log.d("MainActivity", "ValueEntereted not empty")
                    value1.doubleValue = valueEntered.value.toDouble()
                    valueEntered.value = ""
                    operator.intValue = 3
                }
            }, text = "*")

        }
        Row(horizontalArrangement = Arrangement.End) {


            numberButton(onClick = { valueEntered.value += "0" }, text = "0")

            numberButton(onClick = {
                if (!valueEntered.value.contains('.')) {
                    if (!valueEntered.value.isBlank()) {

                        Log.d("MainActivity", "ValueEntereted not empty")
                        valueEntered.value += "."
                    }
                }
            }, text = ".")
            numberButton(
                onClick = {
                    value1.doubleValue = 0.0
                    valueEntered.value = ""
                    result.value = ""
                },
                text = "Clear"
            )

            numberButton(onClick = {
                if (!valueEntered.value.isBlank()) {

                    Log.d("MainActivity", "ValueEntereted not empty")
                    value1.doubleValue = valueEntered.value.toDouble()
                    valueEntered.value = ""
                    operator.intValue = 4
                }

            }, text = "/")

        }
        Row(horizontalArrangement = Arrangement.End) {
            numberButton(onClick = {
                value1.doubleValue = calcuateResult(
                    value1.doubleValue,
                    valueEntered.value.toDouble(),
                    operator.value
                )
                result.value = value1.doubleValue.toString()
                valueEntered.value = result.value
            }, text = "=")

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