package com.example.mindsharpener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var textViewNum1: TextView
    private lateinit var textViewNum2: TextView
    private lateinit var textViewOperator: TextView
    private lateinit var editTextAnswer: EditText
    private lateinit var buttonCheck: Button
    private lateinit var radioGroupLevels: RadioGroup
    private lateinit var textViewPoints: TextView
    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        // Initialize views
        textViewNum1 = findViewById(R.id.textView4)
        textViewNum2 = findViewById(R.id.textView6)
        textViewOperator = findViewById(R.id.textView5)
        editTextAnswer = findViewById(R.id.editTextText)
        buttonCheck = findViewById(R.id.button)
        radioGroupLevels = findViewById(R.id.radioGroup)
        textViewPoints = findViewById(R.id.textView8)

        // Set a listener for the Check button
        buttonCheck.setOnClickListener {
            checkAnswer()
        }

        // Set a listener for the RadioGroup to handle level selection
        radioGroupLevels.setOnCheckedChangeListener { _, _ ->
            updateQuestion()
        }

        // Initial question update
        updateQuestion()
    }

    private fun updateQuestion() {
        val selectedLevelId = radioGroupLevels.checkedRadioButtonId
        val selectedLevelRadioButton = findViewById<RadioButton>(selectedLevelId)

        val maxDigits = when (selectedLevelRadioButton.text.toString()) {
            "i3" -> 1
            "i5" -> 2
            "i7" -> 3
            else -> 1
        }

        val random = Random()
        val firstNumber = random.nextInt((10.0).pow(maxDigits).toInt())
        val secondNumber = random.nextInt((10.0).pow(maxDigits).toInt())

        textViewNum1.text = firstNumber.toString()
        textViewNum2.text = secondNumber.toString()

        val operator = random.nextInt(4)
        textViewOperator.text = when (operator) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            else -> "/"
        }

        editTextAnswer.text.clear()
    }

    private fun checkAnswer() {
        val firstNumber = textViewNum1.text.toString().toInt()
        val secondNumber = textViewNum2.text.toString().toInt()
        val operator = textViewOperator.text.toString()

        val correctAnswer = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0) firstNumber / secondNumber else 0
            else -> 0
        }

        if (editTextAnswer.text.isNotEmpty()) {
            val userAnswer = editTextAnswer.text.toString().toIntOrNull() ?: 0
            if (userAnswer == correctAnswer) {
                points++
            } else {
                points--
            }
        }

        textViewPoints.text = points.toString()
        updateQuestion()
    }
}
