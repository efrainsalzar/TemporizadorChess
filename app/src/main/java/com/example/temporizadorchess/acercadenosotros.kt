package com.example.temporizadorchess

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class acercadenosotros : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acercadenosotros)

        val retorno=findViewById<ImageView>(R.id.retorno1)

        retorno.setOnClickListener{
            finish()
        }
    }
}