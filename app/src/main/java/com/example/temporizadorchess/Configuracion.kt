package com.example.temporizadorchess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Configuracion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)


        val acercade = findViewById<Button>(R.id.acerca)
        acercade.setOnClickListener {
            val intent = Intent(this, acercadenosotros::class.java)
            startActivity(intent)
        }
    }
}