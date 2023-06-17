package com.example.temporizadorchess

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime // importar la clase LocalDateTime
import java.time.LocalDate // importar la clase LocalDate

object DatosEnvi {
    var nombrePlayer1: String = ""
    var nombrePlayer2: String = ""
    var tiempoJuego: String = ""
    var contadorP1: String = ""
    var contadorP2: String = ""
    var ganoPerdioP1: String = ""
    var ganoPerdioP2: String = ""
}

class MainActivity : AppCompatActivity() {



    //valor de DBFire
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonsalir = findViewById<ImageView>(R.id.botonSalir)
        val btStar = findViewById<Button>(R.id.BotonStar)
        val namep1 = findViewById<EditText>(R.id.nameP1)
        val namep2 = findViewById<EditText>(R.id.nameP2)

        val radioGroup = findViewById<RadioGroup>(R.id.MinutosVerif)

        //getData()

        //funcion desde ota clase
        /*val otra = ChessTimer(this)
        otra.mostrarToast()*/



        btStar.isEnabled=false
        //Metodos Click
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // obtén la opción seleccionada
            val radioButton = findViewById<RadioButton>(checkedId)
            btStar.isEnabled=true
            //val opcionSeleccionada = radioButton.id



            //Validar Tiempo
            btStar.setOnClickListener{
                if (checkedId == -1) {  } else {


                    // Se ha seleccionado un botón en el RadioGroup
                    DatosEnvi.nombrePlayer1 = namep1.text.toString()
                    DatosEnvi.nombrePlayer2 = namep2.text.toString()
                    DatosEnvi.tiempoJuego = radioButton.text.toString()

                    siguienteActivity(Temporizador::class.java)
                    //finish()
                }
            }
        }
        //Boton Salir
        botonsalir.setOnClickListener {
            finishAffinity()
        }


    }//fin
    fun siguienteActivity(nameActivity: Class<out Activity>){
        val intent = Intent(this,nameActivity)
        startActivity(intent)
    }
}

//fin de fun onCreate



//fin class