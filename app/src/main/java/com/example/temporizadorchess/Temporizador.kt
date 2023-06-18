package com.example.temporizadorchess

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class Temporizador : AppCompatActivity() {
    //Si tu sabes me dices porque yo no
    private lateinit var countdownTimerP1: CountDownTimer
    private lateinit var countdownTimerP2: CountDownTimer
    private lateinit var etiquetaTiempo1: TextView
    private lateinit var etiquetaTiempo2: TextView
    private var isTimerRunningP1 = false
    private var isTimerRunningP2 = false
    private var timeRemainingP1: Long = 0
    private var timeRemainingP2: Long = 0
    private var PausarPrimeraP1: Boolean = true
    private var PausarPrimeraP2: Boolean = true

    var condicionWin = true
    var contarP1 = 0
    var contarP2 = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        var swImageTempo = 0
        var swPlay = true


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)


        val boton1 = findViewById<View>(R.id.view1)
        val boton2 = findViewById<View>(R.id.view2)
        val stoped = findViewById<ImageView>(R.id.StopGame)
        val pausePlay = findViewById<ImageView>(R.id.pausePlay)
        val configuracion = findViewById<ImageView>(R.id.configuracion)
        val reiniciarTempo = findViewById<ImageView>(R.id.reiniciarTempo)
        etiquetaTiempo1 = findViewById(R.id.tiempo1)
        etiquetaTiempo2 = findViewById(R.id.tiempo2)

        //desactivar el boton Play/Pause por primera vez
        pausePlay.isEnabled = false
        // Llamar nombres
        AddNombre()
        // Llamar tiempo
        val timenew = DatosEnvi.tiempoJuego.toLong() * 1000 * 60 // Combierte el tiempo a milisegundos
        startTimerP1(timenew)
        startTimerP2(timenew)

        // Evento Click P1
        boton1.setOnClickListener {
            if (swImageTempo == 1 || swImageTempo == 0) {
                contarP1++
                condicionWin = true
                ClickbotonTempo(boton1, boton2)
                swImageTempo = 2
                pauseTimerP1()
                continueTimerP2()
                pausePlay.isEnabled = true
            }
        }
        // Evento Click P2
        boton2.setOnClickListener {
            if (swImageTempo == 2 || swImageTempo == 0) {
                contarP2++
                condicionWin = false
                ClickbotonTempo(boton2, boton1)
                swImageTempo = 1
                pauseTimerP2()
                continueTimerP1()
                pausePlay.isEnabled = true
            }
        }
        // click Stop
        stoped.setOnClickListener {
            siguienteActivity(Resultados::class.java)
            finish()
        }
        // Click pausePlay
        pausePlay.setOnClickListener {
            if (swPlay) {
                ClickPausePlay(pausePlay, swPlay)
                swPlay = false
                pauseTimerP1()
                pauseTimerP2()
            } else {
                ClickPausePlay(pausePlay, swPlay)
                when (swImageTempo) {
                    1 -> continueTimerP1()
                    2 -> continueTimerP2()
                }

                swPlay = true

            }
        }
        // Click configuracion
        configuracion.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
        //Click Reiniciar
        reiniciarTempo.setOnClickListener {
            val intent = Intent(this, Temporizador::class.java)
            startActivity(intent)
            finish()
        }
    } // fin de la funcion OnCreate


    private fun startTimerP1(timeInMillis: Long) {
        if (!isTimerRunningP1) {
            timeRemainingP1 = timeInMillis
            countdownTimerP1 = object : CountDownTimer(timeRemainingP1, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeRemainingP1 = millisUntilFinished
                    updateTimerText1(etiquetaTiempo1)
                }

                override fun onFinish() {
                    stopTimerP1()
                }
            }

            countdownTimerP1.start()
            isTimerRunningP1 = true
            updateTimerText1(etiquetaTiempo1)
        }
    }

    private fun startTimerP2(timeInMillis: Long) {
        if (!isTimerRunningP2) {
            timeRemainingP2 = timeInMillis
            countdownTimerP2 = object : CountDownTimer(timeRemainingP2, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeRemainingP2 = millisUntilFinished
                    updateTimerText2(etiquetaTiempo2)
                }

                override fun onFinish() {
                    stopTimerP2()
                }
            }

            countdownTimerP2.start()
            isTimerRunningP2 = true
            updateTimerText2(etiquetaTiempo2)
        }
    }

    private fun pauseTimerP1() {
        if (isTimerRunningP1) {
            countdownTimerP1.cancel()
            isTimerRunningP1 = false
            updateTimerText1(etiquetaTiempo1)
        }
    }

    private fun pauseTimerP2() {
        if (isTimerRunningP2) {
            countdownTimerP2.cancel()
            isTimerRunningP2 = false
            updateTimerText2(etiquetaTiempo2)
        }
    }

    private fun continueTimerP1() {
        if (!isTimerRunningP1 && timeRemainingP1 > 0) {
            startTimerP1(timeRemainingP1)
        }
    }

    private fun continueTimerP2() {
        if (!isTimerRunningP2 && timeRemainingP2 > 0) {
            startTimerP2(timeRemainingP2)
        }
    }

    private fun updateTimerText1(etiquetaTiempo: TextView) {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeRemainingP1)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeRemainingP1) % 60
        etiquetaTiempo.text = String.format("%02d:%02d", minutes, seconds)
        if (PausarPrimeraP1) {
            pauseTimerP1(); PausarPrimeraP1 = false
        }
    }

    private fun updateTimerText2(etiquetaTiempo: TextView) {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeRemainingP2)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeRemainingP2) % 60
        etiquetaTiempo.text = String.format("%02d:%02d", minutes, seconds)
        if (PausarPrimeraP2) {
            pauseTimerP2(); PausarPrimeraP2 = false
        }
    }

    private  fun stopTimerP1() {
        countdownTimerP1.cancel()
        isTimerRunningP1 = false
        timeRemainingP1 = 0
        updateTimerText1(etiquetaTiempo1)
        //
        siguienteActivity(Resultados::class.java)
        finish()
    }

    private  fun stopTimerP2() {
        countdownTimerP2.cancel()
        isTimerRunningP2 = false
        timeRemainingP2 = 0
        updateTimerText2(etiquetaTiempo2)
        //
        siguienteActivity(Resultados::class.java)
        finish()
    }

    private fun ClickbotonTempo(boton1: View, boton2: View) {
        boton1.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor2))
        boton2.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor))
    }

    private fun AddNombre() {
        val etiqueta1 = findViewById<TextView>(R.id.nombre1)
        val etiqueta2 = findViewById<TextView>(R.id.nombre2)
        etiqueta1.text = DatosEnvi.nombrePlayer1
        etiqueta2.text = DatosEnvi.nombrePlayer2
    }

    private fun ClickPausePlay(playPause: ImageView, sw: Boolean) {
        if (sw) {
            playPause.setImageResource(R.drawable.pause_icon)
        } else {
            playPause.setImageResource(R.drawable.play_icon)
        }
    }

    private fun tuTurno(condicion: Boolean) {
        if (condicion) {
            DatosEnvi.ganoPerdioP1 = "Victoria"
            DatosEnvi.ganoPerdioP2 = "Derrota"
        } else {
            DatosEnvi.ganoPerdioP2 = "Victoria"
            DatosEnvi.ganoPerdioP1 = "Derrota"
        }
    }
    private fun siguienteActivity(nameActivity: Class<out Activity>){
        DatosEnvi.contadorP1 = contarP1.toString()
        DatosEnvi.contadorP2 = contarP2.toString()
        tuTurno(condicionWin)
        val intent = Intent(this,nameActivity)
        startActivity(intent)
    }
}
