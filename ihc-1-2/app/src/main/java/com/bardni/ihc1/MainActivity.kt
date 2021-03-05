package com.bardni.ihc1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.EditText

const val EXTRA_MESSAGE = "com.bardini.ihc1.EXTRA_MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<View>(R.id.inputButton)

        button1.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {

        val editText = findViewById<EditText>(R.id.inputText1)
        val message = editText.text.toString()

        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)


    }



}