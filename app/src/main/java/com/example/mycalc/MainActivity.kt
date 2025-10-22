package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firsNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialazation of the lateinit variables :
        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previouscalculationtextview)


        // Let's first set all of the buttons from 0 to 9 :
        val btn0: Button = findViewById<Button>(R.id.btn0)
        val btn1: Button = findViewById<Button>(R.id.btn1)
        val btn2: Button = findViewById<Button>(R.id.btn2)
        val btn3: Button = findViewById<Button>(R.id.btn3)
        val btn4: Button = findViewById<Button>(R.id.btn4)
        val btn5: Button = findViewById<Button>(R.id.btn5)
        val btn6: Button = findViewById<Button>(R.id.btn6)
        val btn7: Button = findViewById<Button>(R.id.btn7)
        val btn8: Button = findViewById<Button>(R.id.btn8)
        val btn9: Button = findViewById<Button>(R.id.btn9)

        // Initalization of the Operators :
        val btnAdd: Button = findViewById<Button>(R.id.btnAddtion)
        val btnMinus: Button = findViewById<Button>(R.id.btnSubtract)
        val btnMultiply: Button = findViewById<Button>(R.id.btnMultiply)
        val btnDivide: Button = findViewById<Button>(R.id.btnDivide)
        val btnPercent: Button = findViewById<Button>(R.id.btnPercent)

        // Initalization of the Others remaining buttons :

        val btnClear: Button = findViewById<Button>(R.id.btnClear)
        val btnEqual: Button = findViewById<Button>(R.id.btnEqual)
        val btnDot: Button = findViewById<Button>(R.id.btnDot)
        val btnBackSpace: Button = findViewById<Button>(R.id.btnBackspace)

        // Let's set what is enter on screen when the user click on the button from 0 to 9 :
        btn0.setOnClickListener { appendNumber("0") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }
        btnDot.setOnClickListener { appendNumber(".") }

        btnAdd.setOnClickListener { setOperation("+") }
        btnMinus.setOnClickListener { setOperation("-") }
        btnMultiply.setOnClickListener { setOperation("*") }
        btnDivide.setOnClickListener { setOperation("/") }

        btnEqual.setOnClickListener { calculateResult() }
        btnClear.setOnClickListener { clearCalculator() }

        btnBackSpace.setOnClickListener { deleteNum() }
        }

    // setOpeation function :
    fun setOperation(operator: String) {
        firsNumber = resultTextView.text.toString().toDouble()
        operation = operator
        isNewOperation = true
        previousCalculationTextView.text = "$firsNumber $operation"
        resultTextView.text = "0.0"
    }

        // Here is the append Function :
        private fun appendNumber(number: String) {
            if (isNewOperation) {
                resultTextView.text = number
                isNewOperation = false
            } else {
                resultTextView.text = "${resultTextView.text}$number"
            }
        }

    // a Function of the calculation for the Calculator :
    private fun calculateResult() {
        try {
            val secondNumber :Double = resultTextView.text.toString().toDouble()
            val result: Double = when(operation) {
                "+" -> firsNumber+secondNumber
                "-" -> firsNumber-secondNumber
                "/"-> firsNumber/secondNumber
                "*" -> firsNumber*secondNumber
                else -> secondNumber
            }
            previousCalculationTextView.text = "$firsNumber $operation $secondNumber"
            resultTextView.text = result.toString()
            isNewOperation = true
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }

    // Clear Function :
    private fun clearCalculator() {
        resultTextView.text = "0"
        previousCalculationTextView.text = ""
        operation = ""
        firsNumber = 0.0
        isNewOperation = true;
    }

    // Delete Function :
    private fun deleteNum() {
        if(resultTextView.text.isNotEmpty() && resultTextView.text != "0.0" && resultTextView.text != "Error") {
            resultTextView.text = resultTextView.text.drop(1)
        } else {
            Toast.makeText(this, "Invalid Operator", Toast.LENGTH_SHORT).show()
        }
    }
}