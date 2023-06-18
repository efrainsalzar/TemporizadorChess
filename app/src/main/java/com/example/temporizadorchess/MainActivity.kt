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
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime // importar la clase LocalDateTime
import java.time.LocalDate // importar la clase LocalDate
import java.util.Random

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



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        val stringList = listOf("Chispitas", "Estrellita", "Diablito", "Cabezón", "Pato Donald", "Tomate", "Chamaco", "Dulcecita", "Pastelito") // Store strings in a list

        val random = Random()




        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonsalir = findViewById<ImageView>(R.id.botonSalir)
        val btStar = findViewById<Button>(R.id.BotonStar)
        val namep1 = findViewById<EditText>(R.id.nameP1)
        val namep2 = findViewById<EditText>(R.id.nameP2)

        val radioGroup = findViewById<RadioGroup>(R.id.MinutosVerif)

        namep1.setHint(stringList[random.nextInt(stringList.size)]) // Get random text from list  and set it in EditText
        namep2.setHint(stringList[random.nextInt(stringList.size)]) // Get random text from list  and set it in EditText

        btStar.isEnabled=false
        //Metodos Click
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // obtén la opción seleccionada
            val radioButton = findViewById<RadioButton>(checkedId)
            btStar.isEnabled=true
            //val opcionSeleccionada = radioButton.text.toString()
             val number= radioGroup.checkedRadioButtonId?.let {
                findViewById<RadioButton>(it).text.toString().replace("\\D+".toRegex(), "")
            }
            DatosEnvi.tiempoJuego = number.toString()

        }
        btStar.setOnClickListener{
            // Se ha seleccionado un botón en el RadioGroup
            guardar_nombres(namep1,namep2)
            //val n = number?.toInt()
            Toast.makeText(this, "tiempo de Juego ${DatosEnvi.tiempoJuego}", Toast.LENGTH_SHORT).show()

            siguienteActivity(Temporizador::class.java)
            //finish()
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

    private fun guardar_nombres(namep1:TextView,namep2:TextView){
        if(!namep1.text.isNullOrEmpty()){
            DatosEnvi.nombrePlayer1 = namep1.text.toString()
            DatosEnvi.nombrePlayer2 = namep2.text.toString()
        }
        else {
            DatosEnvi.nombrePlayer1 = namep1.hint.toString()
            DatosEnvi.nombrePlayer2 = namep2.hint.toString()
        }
    }
}

//fin de fun onCreate



//fin class