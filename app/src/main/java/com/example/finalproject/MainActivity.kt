package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var mtextmain: TextView
    lateinit var btnget: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mtextmain = findViewById(R.id.textmain)
        btnget = findViewById(R.id.btnGetstarted)
        btnget.setOnClickListener {
           val intent = Intent(this@MainActivity, PlasticsActivity::class.java)
            startActivity(intent)

        }
    }
}