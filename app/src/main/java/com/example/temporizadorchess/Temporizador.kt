package com.example.temporizadorchess

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat


class Temporizador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        var swImageTempo = 0
        var swImagePlay = true
        //Inicia la activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)

        //llamar nombres
        AddNombre()

        //llamar tiempo
        Addtiempo()

        //Espacio de nombres
        val boton1 = findViewById<View>(R.id.view1)
        val boton2 = findViewById<View>(R.id.view2)
        //boton Stop
        val stoped = findViewById<ImageView>(R.id.StopGame)
        val pausePlay = findViewById<ImageView>(R.id.pausePlay)
        val configuracion = findViewById<ImageView>(R.id.configuracion)
        val reiniciarTempo = findViewById<ImageView>(R.id.reiniciarTempo)

        //click desde otra clase
        val botonclick = ChessTimer(this)


        //Evento Click

        //Click Boton P1 tiempo y color
        boton1.setOnClickListener {
            //cambia el color del boton
            if (swImageTempo == 1 || swImageTempo == 0) {

                ClickbotonTempo(boton1,boton2)
                swImageTempo = 2
            }
        }
        //Click boton P2 tiempo y color
        boton2.setOnClickListener {
            //cambia el color del boton
            if (swImageTempo == 2 || swImageTempo == 0) {

                ClickbotonTempo(boton2,boton1)
                swImageTempo = 1
            }
        }
        //Click Stop
        stoped.setOnClickListener {
            val intent = Intent(this, Resultados::class.java)
            startActivity(intent)
            finish()
        }
        //Click boton Play/Pause
        pausePlay.setOnClickListener{
            if(swImagePlay) {
                ClickPausePlay(pausePlay,swImagePlay)
                swImagePlay = false
            }
            else{
                ClickPausePlay(pausePlay,swImagePlay)
                swImagePlay = true
            }
        }
        //click config
        configuracion.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
        //click reiniciar
        reiniciarTempo.setOnClickListener {
            val intent = Intent(this, Temporizador::class.java)
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        }

    }//fin funcion onCreate

    private fun ClickbotonTempo(_boton1: View, _boton2: View) {
        _boton1.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this,
                R.color.mycolor2
            )
        )
        _boton2.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this,
                R.color.mycolor
            )
        )

    }
    private fun AddNombre(){
        //nombre
        val etiqueta1 = findViewById<TextView>(R.id.nombre1)
        val etiqueta2 = findViewById<TextView>(R.id.nombre2)
        etiqueta1.text = DatosEnvi.nombrePlayer1
        etiqueta2.text = DatosEnvi.nombrePlayer2
    }
    private fun Addtiempo(){
        val tiempomin = ChessTimer(this)
        val etiquetaTiempo1 = findViewById<TextView>(R.id.tiempo1)
        val etiquetaTiempo2 = findViewById<TextView>(R.id.tiempo2)

        tiempomin.tiempominutos(etiquetaTiempo1)
        tiempomin.tiempominutos(etiquetaTiempo2)
    }
    private fun ClickPausePlay(_playPause: ImageView, _sw: Boolean){
        if(_sw){
            _playPause.setImageResource(R.drawable.pause_icon)
        }else{
            _playPause.setImageResource(R.drawable.play_icon)
        }

    }

}//fin ClassMain