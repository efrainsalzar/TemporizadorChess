package com.example.temporizadorchess

import android.animation.Animator.AnimatorPauseListener
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.os.CountDownTimer
import android.os.Handler

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.Locale


class Temporizador : AppCompatActivity() {

    //Variables para el temporizador
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textViewTimer1: TextView
    private lateinit var textViewTimer2: TextView
    //los textos del temporizador


    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {

        var swImageTempo = 0
        var swImagePlay = true
        var condicionWin = true
        var contarP1 = 0
        var contarP2 = 0

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)

//variables del temporizador
        textViewTimer1 = findViewById(R.id.tiempo1)
        textViewTimer2 = findViewById(R.id.tiempo2)
        startTimer(601000, 1000)

        //nombres extraidos
        //val name1 = intent.getStringExtra("name_p1")
        //val name2 = intent.getStringExtra("name_p2")
        var sw = 0

        //Inicia la activity

        //llamar nombres
        AddNombre()

        //llamar tiempo
        Addtiempo()


//val de otra clase
        val boton1 = findViewById<View>(R.id.view1)
        val boton2 = findViewById<View>(R.id.view2)
        val stoped = findViewById<ImageView>(R.id.StopGame)
        val pausePlay = findViewById<ImageView>(R.id.pausePlay)
        val configuracion = findViewById<ImageView>(R.id.configuracion)
        val reiniciarTempo = findViewById<ImageView>(R.id.reiniciarTempo)

//agregado988
        //Evento Click

        //Click Boton P1 tiempo y color
        boton1.setOnClickListener {
            //cambia el color del boton
            if (swImageTempo == 1 || swImageTempo == 0) {
                contarP1++
                condicionWin = true
                ClickbotonTempo(boton1, boton2)
                swImageTempo = 2
            }
        }
        //Click boton P2 tiempo y color
        boton2.setOnClickListener {
            //cambia el color del boton
            if (swImageTempo == 2 || swImageTempo == 0) {
                contarP2++
                condicionWin = false
                ClickbotonTempo(boton2, boton1)
                swImageTempo = 1
            }
        }
        //Click Stop
        stoped.setOnClickListener {
            DatosEnvi.contadorP1 = contarP1.toString()
            DatosEnvi.contadorP2 = contarP2.toString()
            tuTurno(condicionWin)
            val intent = Intent(this, Resultados::class.java)
            startActivity(intent)
            finish()
        }
        //Click boton Play/Pause
        pausePlay.setOnClickListener {
            if (swImagePlay) {
                ClickPausePlay(pausePlay, swImagePlay)
                swImagePlay = false
            } else {
                ClickPausePlay(pausePlay, swImagePlay)
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


    } //fin del oncreate

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


    private fun AddNombre() {
        //nombre
        val etiqueta1 = findViewById<TextView>(R.id.nombre1)
        val etiqueta2 = findViewById<TextView>(R.id.nombre2)
        etiqueta1.text = DatosEnvi.nombrePlayer1
        etiqueta2.text = DatosEnvi.nombrePlayer2
    }

    private fun Addtiempo() {
        val tiempomin = ChessTimer(this)
        val etiquetaTiempo1 = findViewById<TextView>(R.id.tiempo1)
        val etiquetaTiempo2 = findViewById<TextView>(R.id.tiempo2)

        tiempomin.tiempominutos(etiquetaTiempo1)
        tiempomin.tiempominutos(etiquetaTiempo2)
    }

    private fun ClickPausePlay(_playPause: ImageView, _sw: Boolean) {
        if (_sw) {
            _playPause.setImageResource(R.drawable.pause_icon)
        } else {
            _playPause.setImageResource(R.drawable.play_icon)
        }

    }

    fun tuTurno(condicion: Boolean) {
        if (condicion) {
            DatosEnvi.ganoPerdioP1 = "Victoria"
            DatosEnvi.ganoPerdioP2 = "Derrota"
        } else {
            DatosEnvi.ganoPerdioP2 = "Victoria"
            DatosEnvi.ganoPerdioP1 = "Derrota"
        }
    }


    private fun startTimer(durationMillis: Long, intervalMillis: Long) {
        countDownTimer = object : CountDownTimer(durationMillis, intervalMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                val timeFormatted = String.format("%02d:%02d", minutes, seconds)
                textViewTimer1.text = timeFormatted
                textViewTimer2.text = timeFormatted
            }

            override fun onFinish() {
                textViewTimer1.text = "00:00"
                textViewTimer2.text = "00:00"
            }
        }

        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel() // Cancela el temporizador al cerrar la actividad
    }
}


//fin funcion onCreate

//fin ClassMain

