package com.example.rollingballs

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener , SurfaceHolder.Callback {
    private var surfaceWidth: Int = 0
    private var surfaceHeight: Int = 0

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            Log.d("MainActivity",
                "x = ${event.values[0].toString()}" +
                ",y = ${event.values[1].toString()}" +
                ",z  ${event.values[2].toString()}"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val holder = surfaceView.holder
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE)
            as SensorManager
        val accSensor = sensorManager.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER
        )
        sensorManager.registerListener(
            this, accSensor,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        surfaceWidth = width
        surfaceHeight = height
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager
        sensorManager.unregisterListener(this)
    }
}
