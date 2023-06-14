package com.example.temporizadorchess

import android.annotation.SuppressLint
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    //valor de DBFire
    val db = Firebase.firestore
    val TAG = "Datos"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        //splash
        //setTheme(R.id.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val namep1 = findViewById<EditText>(R.id.nameP1)
        val namep2 = findViewById<EditText>(R.id.nameP2)
        val botonSalir = findViewById<ImageView>(R.id.botonSalir)

        getData()

        //funcion desde ota clase
        /*val otra = ChessTimer(this)
        otra.mostrarToast()*/



val salida=findViewById<Button>(R.id.salida)

        salida.setOnClickListener{
            finish()
        }
        //boton iniciar
        val btStar = findViewById<Button>(R.id.BotonStar)

        btStar.setOnClickListener{
            val intent =Intent(this,Temporizador::class.java)
            intent.putExtra("name_p1",namep1.text.toString())
            intent.putExtra("name_p2",namep2.text.toString())
            startActivity(intent)
            //finish()
        }
        val ace=findViewById<Button>(R.id.acerca)

        ace.setOnClickListener {
            val intent=Intent(this,acercadenosotros::class.java)
            startActivity(intent)
        }

      //  val radioGroup = findViewById<RadioGroup>(R.id.MinutosVerif)

        //radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // obtén la opción seleccionada
          //  val radioButton = findViewById<RadioButton>(checkedId)
            //val opcionSeleccionada = radioButton.id
            //ChessTimer.botonSeleccionado = radioButton.text.toString()

        //}


        botonSalir.setOnClickListener() {
            finish()
        }
    }//fin de fun onCreate

    private fun getData(){
        db.collection("Infome")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}