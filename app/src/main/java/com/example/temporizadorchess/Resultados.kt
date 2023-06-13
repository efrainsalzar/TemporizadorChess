package com.example.temporizadorchess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Resultados : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {


        val name1 = intent.getStringExtra("namee_p1")
        val name2 = intent.getStringExtra("namee_p2")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val etiqueta1 = findViewById<TextView>(R.id.nombreRojo)
        etiqueta1.setText(name1)
        val etiqueta2 = findViewById<TextView>(R.id.nombreVerde)
        etiqueta2.setText(name2)
    }
}