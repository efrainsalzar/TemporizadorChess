package com.example.temporizadorchess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //splash
        //setTheme(R.id.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //funcion desde ota clase
        /*val otra = ChessTimer(this)
        otra.mostrarToast()*/


        val namep1 = findViewById<EditText>(R.id.nameP1)
        val namep2 = findViewById<EditText>(R.id.nameP2)
        var botonselect = "deafult"

        //boton iniciar
        val btStar = findViewById<Button>(R.id.BotonStar)

        btStar.setOnClickListener{



            val intent =Intent(this,Temporizador::class.java)
            intent.putExtra("name_p1",namep1.text.toString())
            intent.putExtra("name_p2",namep2.text.toString())
            startActivity(intent)

        }

        val radioGroup = findViewById<RadioGroup>(R.id.MinutosVerif)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // obtén la opción seleccionada
            val radioButton = findViewById<RadioButton>(checkedId)
            //val opcionSeleccionada = radioButton.id

            ChessTimer.botonSeleccionado = radioButton.text.toString()






            //Toast.makeText(this, "click: $opcionSeleccionada", Toast.LENGTH_SHORT).show()
            // guarda la opción seleccionada en tu base de datos o en una variable
            // ...
        }






    }//fin de fun onCreate
//funciones
    /*fun miFuncion(){
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
    }*/
}