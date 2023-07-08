package com.converter.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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

        // Set item selection listeners for the Spinners
        convertFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convertValue()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        convertTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convertValue()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
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
                    "Miles" -> inputValue * 0.62
                    "Centimeters" -> inputValue * 100000
                    "Inches" -> inputValue * 39370
                    "Kilometers" -> inputValue
                    else -> null
                }
                // Add more cases for other unit conversions
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
