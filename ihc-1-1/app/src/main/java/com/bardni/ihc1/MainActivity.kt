package com.bardni.ihc1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<View>(R.id.inputButton)

        button1.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {

        val inputText1 = findViewById<EditText>(R.id.inputText1)
        val inputText2 = findViewById<EditText>(R.id.inputText2)

        val number1 = inputText1.text.toString().toIntOrNull()
        val number2 = inputText2.text.toString().toIntOrNull()

        val outputText = findViewById<TextView>(R.id.outputText)
        
        if (number1 != null && number2!= null ){
            outputText.text = (number1+number2).toString()
        }

    }



}