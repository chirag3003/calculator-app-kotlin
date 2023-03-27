package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var input: TextView
    private var lastNumber: Boolean = false;
    private var lastDot: Boolean = false;
    private var operator: String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById<TextView>(R.id.input)
    }

    fun onDigit(view: View) {
        lastNumber = true
        input.append((view as Button).text)
    }

    fun onClear(view: View) {
        input.text = ""
        lastNumber = false
        lastDot = false
        operator = ""
    }

    fun onDot(view: View) {
        if (!lastNumber || lastDot) {
            println("clicked")
            return;
        }
        input.append(".");
        lastNumber = false;
        lastDot = true;
    }

    fun onOperator(view: View) {
        if (lastNumber && !isOperatorAdded(input.text.toString())) {
            lastNumber = false;
            lastDot = false;
            val op = (view as Button).text.toString()
            operator = op
            input.append(op)
        }
    }

    fun onEqual(view: View) {
        val txt = input.text.toString()
        if (operator == "") return;
        val split = txt.split(operator)
        val n1 = split[0].toDouble()
        val n2 = split[1].toDouble()

        val res = when(operator){
            "/" -> n1 / n2
            "*" -> n1 * n2
            "-" -> n1 - n2
            else -> n1 + n2
        }
        lastDot = true
        lastNumber = true
        operator = ""
        input.text = res.toString()
    }

    private fun isOperatorAdded(str: String): Boolean {
        if (str.contains("*") || str.contains("/") || str.contains("+") || str.contains("-"))
            return true

        return false
    }
}