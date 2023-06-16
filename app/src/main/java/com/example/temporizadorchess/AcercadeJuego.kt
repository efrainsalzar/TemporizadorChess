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


        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.image_tablero)

        // Escala la imagen (en este caso, al 50% del tamaño original)
        val ancho = bitmap.width / 2
        val altura = bitmap.height / 2
        val nuevaImagen = Bitmap.createScaledBitmap(bitmap, ancho, altura, false)

        // Actualiza la imagen en la vista ImageView
        val imageTablero = findViewById<ImageView>(R.id.tablaImage)
        imageTablero.setImageBitmap(nuevaImagen)








    }
}