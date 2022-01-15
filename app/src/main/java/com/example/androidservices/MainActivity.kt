package com.example.androidservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvService = findViewById<TextView>(R.id.tvService)
        val etData = findViewById<EditText>(R.id.etData)

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            Intent(this, MyService::class.java).also {
                // Starting the service
                startService(it)
                tvService.text = "Service running"
            }
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            Intent(this, MyService::class.java).also {
                // Stopping the service
                stopService(it)
                tvService.text = "Service stopped"
            }
        }

        findViewById<Button>(R.id.btnSend).setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = etData.text.toString()
                it.putExtra("EXTRA_DATA", dataString)
                // If service is not running, it starts a new one with the data string.
                startService(it)
            }
        }
    }
}