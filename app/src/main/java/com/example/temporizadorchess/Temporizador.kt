package com.example.temporizadorchess

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class Temporizador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {




        //nombres extraidos
        val name1 = intent.getStringExtra("name_p1")
        val name2 = intent.getStringExtra("name_p2")
        var sw = 0
        //Inicia la activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)


//val de otra clase
        val tiempomin = ChessTimer(this,)

        //nombre
        val etiqueta1 = findViewById<TextView>(R.id.nombre1)
        etiqueta1.setText(name1)
        val etiqueta2 = findViewById<TextView>(R.id.nombre2)
        etiqueta2.setText(name2)

        //tiempo text
        val etiquetaTiempo1 = findViewById<TextView>(R.id.tiempo1)
        val etiquetaTiempo2 = findViewById<TextView>(R.id.tiempo2)

        tiempomin.tiempominutos(etiquetaTiempo1)
        tiempomin.tiempominutos(etiquetaTiempo2)
        //etiquetaTiempo2.setText(tiempo)

        //color del boton click
        val boton1 = findViewById<View>(R.id.view1)
        val boton2 = findViewById<View>(R.id.view2)
        //val centroboton = findViewById<LinearLayout>(R.id.centro)
        val stoped = findViewById<ImageView>(R.id.StopGame)


        //click desde otra clase
        val botonclick = ChessTimer(this,)


        //color del boton click
        boton1.setOnClickListener {
            //cambia el color del boton
            if (sw == 1 || sw == 0) {
                /*boton1.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor2))*/  /*boton2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor))*/
                botonclick.ClickColor(boton1, boton2)


                sw = 2
            }
        }

        boton2.setOnClickListener {
            //cambia el color del boton
            if (sw == 2 || sw == 0) {
                /*boton2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor2))*/ /*boton1.backgroundTintList =  ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mycolor))*/
                botonclick.ClickColor(boton2, boton1)
                sw = 1
            }
        }

        stoped.setOnClickListener{
            val intent = Intent(this, InformeResult::class.java)
            startActivity(intent)
        }

        /* val textView = findViewById<TextView>(R.id.contadortiempo)
         val countDownTimer = object : CountDownTimer(10000, 1000) {
             override fun onTick(millisUntilFinished: Long) {
                 textView.text =  "${millisUntilFinished / 1000}"
             }
             override fun onFinish() {
                 textView.text = "Tiempo finalizado"
             }
         }
         countDownTimer.start()*/
        /*val timer = ChessTimer(object: ChessTimer.TimerListener {
            override fun onTimeTick(player1Time: Long, player2Time: Long) {
                // update UI with current times
            }
            override fun onTimerFinish(winner: Int) {
                // handle end of game
            }
            override fun onPlayerSwitch() {
                // handle player switching turns
            }
        })
        timer.start(120, 120) // start with 2 minutes for each player
*/






    }//fin funcion onCreate

}//fin ClassMain