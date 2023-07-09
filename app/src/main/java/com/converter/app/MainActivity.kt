package com.converter.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var inputValueLayout: TextInputLayout
    private lateinit var output: TextView
    private lateinit var convertFrom: Spinner
    private lateinit var convertTo: Spinner
    private lateinit var buttonConvert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        inputValueLayout = findViewById(R.id.inputValue)
        output = findViewById(R.id.output)
        convertFrom = findViewById(R.id.convertFrom)
        convertTo = findViewById(R.id.convertTo)
        buttonConvert = findViewById(R.id.buttonConvert)

        // Set click listener for the Convert button
        buttonConvert.setOnClickListener {
            convertValue()
        }
    }

    // Function to perform the conversion
    private fun convertValue() {
        // Get the value from the user
        val inputValueString = inputValueLayout.editText?.text.toString()
        val inputValue = inputValueString.toFloatOrNull()

        if (inputValue != null) {
            // Perform the conversion based on the selected units
            val result = when (convertFrom.selectedItem.toString()) {
                "Kilometers" -> when (convertTo.selectedItem.toString()) {
                    "Miles" -> inputValue * 0.621371
                    "Centimeters" -> inputValue * 100000
                    "Inches" -> inputValue * 39370.1
                    else -> null
                }
                "Miles" -> when (convertTo.selectedItem.toString()) {
                    "Kilometers" -> inputValue * 1.60934
                    "Centimeters" -> inputValue * 160934
                    "Inches" -> inputValue * 63360
                    else -> null
                }
                "Centimeters" -> when (convertTo.selectedItem.toString()) {
                    "Kilometers" -> inputValue / 100000
                    "Miles" -> inputValue / 160934
                    "Inches" -> inputValue / 2.54
                    else -> null
                }
                "Inches" -> when (convertTo.selectedItem.toString()) {
                    "Kilometers" -> inputValue / 39370.1
                    "Miles" -> inputValue / 63360
                    "Centimeters" -> inputValue * 2.54
                    else -> null
                }
                "Celsius" -> when (convertTo.selectedItem.toString()) {
                    "Kelvin" -> inputValue + 273.15
                    "Fahrenheit" -> inputValue * 9 / 5 + 32
                    else -> null
                }
                "Kelvin" -> when (convertTo.selectedItem.toString()) {
                    "Celsius" -> inputValue - 273.15
                    "Fahrenheit" -> inputValue * 9 / 5 - 459.67
                    else -> null
                }
                "Fahrenheit" -> when (convertTo.selectedItem.toString()) {
                    "Celsius" -> (inputValue - 32) * 5 / 9
                    "Kelvin" -> (inputValue + 459.67) * 5 / 9
                    else -> null
                }
                else -> null
            }

            if (result != null) {
                output.text = result.toString()
            } else {
                // If the result is null, display an error message
                output.text = "Not able to convert"
            }
        } else {
            // If the input value is null, display an error message
            output.text = "Enter a valid value"
        }
    }

}