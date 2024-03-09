package com.example.calculadoramark

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputMethodManager: InputMethodManager
    var hsInputErros = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        defineButtonOperations()
    }

    private fun defineButtonOperations() {
        defineSumOperation()
        defineSubtractOperation()
        defineMultiplyOperation()
        defineDivideOperation()
    }

    private fun defineDivideOperation() {
        val divideButton = findViewById<Button>(R.id.bDivide)
        divideButton.setOnClickListener {
            closeKeyboard(it)
            divide()
        }
    }

    private fun defineMultiplyOperation() {
        val multiplyButton = findViewById<Button>(R.id.bMultiply)
        multiplyButton.setOnClickListener {
            closeKeyboard(it)
            multiply()
        }
    }

    private fun defineSubtractOperation() {
        val subtractButton = findViewById<Button>(R.id.bSubtract)
        subtractButton.setOnClickListener {
            closeKeyboard(it)
            subtract()
        }
    }

    private fun defineSumOperation() {
        val sumButton = findViewById<Button>(R.id.bSum)
        sumButton.setOnClickListener {
            closeKeyboard(it)
            sum()
        }
    }

    private fun closeKeyboard(view: View) {
        this.inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun sum() {
        clearResultValue()

        val n1 = getFirstNumber()
        val n2 = getSecondNumber()

        if (n1 == null || n2 == null) {
            return
        }

        val result = n1 + n2

        setResultValue(result)
    }

    private fun subtract() {
        clearResultValue()

        val n1 = getFirstNumber()
        val n2 = getSecondNumber()

        if (n1 == null || n2 == null) {
            return
        }

        val result = n1 - n2

        setResultValue(result)
    }

    private fun multiply() {
        clearResultValue()

        val n1 = getFirstNumber()
        val n2 = getSecondNumber()

        if (n1 == null || n2 == null) {
            return
        }

        val result = n1 * n2

        setResultValue(result)
    }

    private fun divide() {
        clearResultValue()

        val n1 = getFirstNumber()
        val n2 = getSecondNumber()

        if (n1 == null || n2 == null) {
            return
        }

        val result = n1 / n2

        setResultValue(result)
    }

    private fun setResultValue(result: Double) {
        val resultString = String.format("%.2f", result)
        findViewById<TextView>(R.id.tvResultValue).text = resultString
    }

    private fun clearResultValue() {
        findViewById<TextView>(R.id.tvResultValue).text = ""
    }

    private fun getFirstNumber(): Double? {
        val etNumber1 = findViewById<EditText>(R.id.etNumberOne)
        val value = etNumber1.text.toString()

        if (value.isEmpty()) {
            setErrorBackgroundColor(etNumber1)
            return null
        }

        setDefaultBackgroundColor(etNumber1)
        return value.toDouble()
    }

    private fun getSecondNumber(): Double? {
        val etNumber2 = findViewById<EditText>(R.id.etNumberTwo)
        val value = etNumber2.text.toString()

        if (value.isEmpty()) {
            setErrorBackgroundColor(etNumber2)
            return null
        }

        setDefaultBackgroundColor(etNumber2)
        return value.toDouble()
    }

    private fun setErrorBackgroundColor(et: EditText) {
        if (this.hsInputErros.contains(et.id.toString())) {
            return
        }

        et.setBackgroundColor(getColor(R.color.red))

        this.hsInputErros.add(et.id.toString())
    }

    private fun setDefaultBackgroundColor(et: EditText) {
        if (!this.hsInputErros.contains(et.id.toString())) {
            return
        }

        et.setBackgroundColor(getColor(R.color.white))

        this.hsInputErros.remove(et.id.toString())
    }
}