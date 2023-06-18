package com.example.temporizadorchess

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AcercadeJuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acercade_juego)



        val retorno=findViewById<ImageView>(R.id.atras)


        retorno.setOnClickListener{
            finish()
        }






    }
}