package com.example.temporizadorchess

import android.animation.Animator.AnimatorPauseListener
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.os.CountDownTimer
import android.os.Handler

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Locale


class Temporizador : AppCompatActivity() {

    //Variables para el temporizador
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textViewTimer1: TextView
    private lateinit var textViewTimer2: TextView
    private var timeRemaining: Long = 0
     var isPaused=true

    //los textos del temporizador

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)

//variables del temporizador
 textViewTimer1=findViewById(R.id.tiempo1)
textViewTimer2=findViewById(R.id.tiempo2)
        startTimer(600000, 1000)

        //nombres extraidos
        //val name1 = intent.getStringExtra("name_p1")
        //val name2 = intent.getStringExtra("name_p2")
        var sw = 0
        //Inicia la activity





//val de otra clase
        val configurar=findViewById<ImageView>(R.id.config)
        val tiempomin = ChessTimer(this,)
        //nombre
        val etiqueta1 = findViewById<TextView>(R.id.nombre1)
        etiqueta1.text = DatosEnvi.nombrePlayer1
        //etiqueta1.setText(name1)
        val etiqueta2 = findViewById<TextView>(R.id.nombre2)
        etiqueta2.text = DatosEnvi.nombrePlayer2
        //etiqueta2.setText(name2)
//variables para el boton de pausa que desactiva a los botones de los jugadores

        //color del boton click
        val boton1 = findViewById<View>(R.id.view1)
        val boton2 = findViewById<View>(R.id.view2)
        val pause=findViewById<ImageView>(R.id.pausegame)
        val play=findViewById<ImageView>(R.id.startgame)
        val stoped = findViewById<ImageView>(R.id.StopGame)
        //click desde otra clase
        val botonclick = ChessTimer(this,)

        //contador de turnos
        val tn1=findViewById<TextView>(R.id.turnos1)
        val tn2=findViewById<TextView>(R.id.turnos2)
        var cont1=0
        var cont2=0
        configurar.setOnClickListener{
            val intent=Intent(this,Configuracion::class.java)
            startActivity(intent)
        }
        stoped.setOnClickListener{
            val intent=Intent(this,Resultados::class.java)
            startActivity(intent)
        }
        play.setOnClickListener{
            resumeTimer()
        }
        pause.setOnClickListener{

                pauseTimer()

        }
        //color del boton click
        boton1.setOnClickListener {
            //cambia el color del boton
            if (sw == 1 || sw == 0) {
                /*boton1.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor2))*/  /*boton2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor))*/
                botonclick.ClickColor(boton1, boton2)
                cont1++
                tn1.setText("Turnos: "+cont1)
                sw = 2
            }
        }

        boton2.setOnClickListener {
            //cambia el color del boton
            if (sw == 2 || sw == 0) {
                /*boton2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor2))*/ /*boton1.backgroundTintList =  ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor))*/
                botonclick.ClickColor(boton2, boton1)
                cont2++
                tn2.setText("Turnos: "+cont2)
                sw = 1
            }
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
                timeRemaining = millisUntilFinished
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

    private fun pauseTimer(){
        countDownTimer?.cancel()
        isPaused=true
    }

     fun resumeTimer() {
       startTimer(timeRemaining,1000)
}}







//fin funcion onCreate

//fin ClassMain

