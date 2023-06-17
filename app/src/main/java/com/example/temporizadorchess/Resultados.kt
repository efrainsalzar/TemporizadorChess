package com.example.temporizadorchess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import java.time.LocalDateTime

class Resultados : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)



        //Datos P1
        val nombrep1 = findViewById<TextView>(R.id.nombrePlayer1)
        val tiempoP1Juego = findViewById<TextView>(R.id.tiempoP1Juego)
        val cont1 = findViewById<TextView>(R.id.contP1)
        val GP1 = findViewById<TextView>(R.id.ganoPerdio1)

        //Datos P2
        val nombrep2 = findViewById<TextView>(R.id.nombrePlayer2)
        val tiempoP2Juego = findViewById<TextView>(R.id.tiempoP2Juego)
        val cont2 = findViewById<TextView>(R.id.contP2)
        val GP2 = findViewById<TextView>(R.id.ganoPerdio2)

        //llamar los nombres
        nombres(nombrep1, nombrep2)
        //llamar tiempo
        tiempo(tiempoP1Juego, tiempoP2Juego)
        //llamar Movimientos
        contadorTurnos(cont1, cont2)
        //llamar Ganador/Perdedor
        ganarPerder(GP1, GP2)


        val btnReiniciar = findViewById<ImageView>(R.id.reiniciar)
        val btnHome = findViewById<ImageView>(R.id.newHome)
        val btnSalirApp = findViewById<Button>(R.id.SalirApp)
        val btnCloudAdd = findViewById<ImageView>(R.id.cloudAdd)

        //eventoClick


        btnSalirApp.setOnClickListener {
            finishAffinity()
        }
        btnReiniciar.setOnClickListener {
            val intent = Intent(this, Temporizador::class.java)
            startActivity(intent)
            finish()
        }
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //cierra todas las pantallas que estan detras
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        btnCloudAdd.setOnClickListener {
            val nombreCollFecha = nombreColeccion()
            AddDatosFirebase(
                nombreCollFecha,
                nombrep1.text.toString(),
                tiempoP1Juego.text.toString(),
                cont1.text.toString(),
                GP1.text.toString()
            )
            AddDatosFirebase(
                nombreCollFecha,
                nombrep2.text.toString(),
                tiempoP2Juego.text.toString(),
                cont2.text.toString(),
                GP2.text.toString()
            )
            Toast.makeText(this, "Datos guardados en FireBase", Toast.LENGTH_SHORT).show()
        }

    }//fin oncreate

    fun nombres(_nombrep1: TextView, _nombrep2: TextView) {

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

    fun tiempo(_tiempoP1Juego: TextView, _tiempoP2Juego: TextView) {
        _tiempoP1Juego.text = DatosEnvi.tiempoJuego
        _tiempoP2Juego.text = DatosEnvi.tiempoJuego
    }

    fun contadorTurnos(_contP1Juego: TextView, _contP2Juego: TextView) {
        _contP1Juego.text = DatosEnvi.contadorP1.toString()
        _contP2Juego.text = DatosEnvi.contadorP2.toString()
    }

    fun ganarPerder(_GP1: TextView, _GP2: TextView) {
        _GP1.text = DatosEnvi.ganoPerdioP1
        _GP2.text = DatosEnvi.ganoPerdioP2
    }

    private fun AddDatosFirebase(
        _nombreColeccionFecha: String,
        _nombre: String,
        _tiempo: String,
        _movimientos: String,
        _resultado: String
    ) {
        // Obtiene una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()
        val TAG = "Nuevo"

// Añade una nueva colección llamada "nuevaColeccion" y agrega un documento con los datos que desees
        db.collection("${_nombreColeccionFecha}")
            .add(
                mapOf(
                    "nombre" to _nombre,
                    "tiempo" to _tiempo,
                    "movimientos" to _movimientos,
                    "resultado" to _resultado
                )
            )
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar documento", e)
            }

    }

    fun nombreColeccion(): String {
        // obtener la fecha actual
        val fechaActual = LocalDateTime.now()

        return fechaActual.toString()
    }

}