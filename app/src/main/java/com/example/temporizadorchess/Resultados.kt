package com.example.temporizadorchess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Resultados : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)


        val nombrep1 = findViewById<TextView>(R.id.nombreVerde)
        val nombrep2 = findViewById<TextView>(R.id.nombreRojo)
        //llamar los nombres
        nombres(nombrep1,nombrep2)

        val tiempoP1Juego = findViewById<TextView>(R.id.tiempoP1Juego)
        val tiempoP2Juego = findViewById<TextView>(R.id.tiempoP2Juego)
        //llamar tiempo
        tiempo(tiempoP1Juego,tiempoP2Juego)

        val botonSalirApp = findViewById<Button>(R.id.SalirApp)
        //eventoClick
        botonSalirApp.setOnClickListener{
            finishAffinity()
        }

    }//fin oncreate

    fun nombres (_nombrep1:TextView, _nombrep2:TextView){

        if (DatosEnvi.nombrePlayer1.isNullOrBlank()) {
            // La cadena está vacía, es nula o contiene sólo espacios en blanco
        } else {
            // La cadena no está vacía, no es nula y no contiene sólo espacios en blanco
            _nombrep1.text = DatosEnvi.nombrePlayer1
        }
        if (DatosEnvi.nombrePlayer2.isNullOrBlank()) {
            // La cadena está vacía, es nula o contiene sólo espacios en blanco
        } else {
            // La cadena no está vacía, no es nula y no contiene sólo espacios en blanco
            _nombrep2.text = DatosEnvi.nombrePlayer2
        }
    }
    fun tiempo(_tiempoP1Juego:TextView,_tiempoP2Juego:TextView){
        _tiempoP1Juego.text = DatosEnvi.tiempoJuego
        _tiempoP2Juego.text = DatosEnvi.tiempoJuego
    }
}