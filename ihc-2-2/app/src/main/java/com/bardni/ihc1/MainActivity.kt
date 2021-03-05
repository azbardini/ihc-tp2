package com.bardni.ihc1

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.EditText

const val EXTRA_MESSAGE = "com.bardini.ihc1.EXTRA_MESSAGE"

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor
    private lateinit var mLight: Sensor
    private lateinit var mMagnetic: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        mMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {

        val sensorType: Int = event?.sensor!!.type;

        if(sensorType == Sensor.TYPE_LIGHT) {
            setLightValues(event.values)
        }
        if(sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
            setMagneticValues(event.values)
        }
        if(sensorType == Sensor.TYPE_LINEAR_ACCELERATION) {
            setAccelerationValues(event.values)
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mMagnetic, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    private fun setAccelerationValues(values: FloatArray){
        val accelerationX = values[0]
        val accelerationY = values[1]
        val accelerationZ = values[2]
        val inputTextX = findViewById<EditText>(R.id.inputTextX)
        val inputTextY = findViewById<EditText>(R.id.inputTextY)
        val inputTextZ = findViewById<EditText>(R.id.inputTextZ)
        inputTextX.setText(accelerationX.toString())
        inputTextY.setText(accelerationY.toString())
        inputTextZ.setText(accelerationZ.toString())
        if(accelerationX >= 10 || accelerationY >= 10 || accelerationZ >= 10) {
            sendMessage()
        }
    }

    private fun setLightValues(values: FloatArray){
        val light = values[0]
        val inputTextLight = findViewById<EditText>(R.id.inputTextLight)
        inputTextLight.setText(light.toString())
    }

    private fun setMagneticValues(values: FloatArray){
        val magneticX = values[0]
        val magneticY = values[1]
        val magneticZ = values[2]
        val inputTextX = findViewById<EditText>(R.id.textMagX)
        val inputTextY = findViewById<EditText>(R.id.textMagY)
        val inputTextZ = findViewById<EditText>(R.id.textMagZ)
        inputTextX.setText(magneticX.toString())
        inputTextY.setText(magneticY.toString())
        inputTextZ.setText(magneticZ.toString())
    }

    private fun sendMessage() {
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, "Aí tu aceleraste em bicho")
        }
        startActivity(intent)
    }



}