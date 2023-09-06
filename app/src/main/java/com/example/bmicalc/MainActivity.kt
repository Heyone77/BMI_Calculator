package com.example.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)


        calcButton.setOnClickListener() {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if(validateInput(weight,height)){
                val bmi = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))

                displayResult(bmi)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{

        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {return true}
        }
    }


    private fun calculateBmiResult(bmi: Double): Pair<String, Int> {

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Normal Weight"
                color = R.color.normal
            }
            bmi in 25.0..29.99 -> {
                resultText = "Overweight"
                color = R.color.overweight
            }
            bmi >= 30 -> {
                resultText = "Obese"
                color = R.color.obese
            }

        }

        return Pair(resultText, color)
    }

    private fun displayResult(bmi:Double){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescrition = findViewById<TextView>(R.id.tvResult)
        val additionalInfo = findViewById<TextView>(R.id.tvInfo)

        val bmi2Digit = "%.2f".format(bmi)
        resultIndex.text = bmi2Digit.toString()



        additionalInfo.text = "Normal range is 18.5 - 24.9"

        var result = calculateBmiResult(bmi)

        resultDescrition.text = result.first
        resultDescrition.setTextColor(ContextCompat.getColor(this, result.second))
    }
}