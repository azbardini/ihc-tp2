package com.bardni.ihc1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message = intent.getStringExtra(EXTRA_MESSAGE).toString()
        overwriteMessage(message)
    }
    private fun overwriteMessage(message: String){
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = message
    }
}