package com.bardni.ihc1

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.EditText

const val EXTRA_MESSAGE = "com.bardini.ihc1.EXTRA_MESSAGE"

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensorName: String = event?.sensor!!.name;
        val accelX = event.values[0]
        val accelY = event.values[1]
        val accelZ = event.values[2]
        Log.d("Sensor X", accelX.toString())
        Log.d("Sensor Y", accelY.toString())
        Log.d("Sensor Z", accelZ.toString())
        setTextValues(accelX, accelY, accelZ)
        if(accelX >= 10 || accelY >= 10 || accelZ >= 10) {
            sendMessage()
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    fun setTextValues(X: Float, Y: Float ,Z: Float){

        val inputTextX = findViewById<EditText>(R.id.inputTextX)
        val inputTextY = findViewById<EditText>(R.id.inputTextY)
        val inputTextZ = findViewById<EditText>(R.id.inputTextZ)
        inputTextX.setText(X.toString())
        inputTextY.setText(Y.toString())
        inputTextZ.setText(Z.toString())
    }

    private fun sendMessage() {

        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, "Aí tu aceleraste em bicho")
        }
        startActivity(intent)

    }



}