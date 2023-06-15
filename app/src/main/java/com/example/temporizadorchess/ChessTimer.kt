package com.example.temporizadorchess

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import android.widget.TextView


class ChessTimer(private val mContext: Context) {

    val thenTime = "10:00"
    val fiveTime = "5:00"
    val threeTime = "3:00"
    val thirtyTime = "30:00"

    companion object {
        var botonSeleccionado: String? = null
    }

    fun mostrarToast() {//muestra un mensaje temporal
        Toast.makeText(
            mContext,
            "Este es un mensaje Toast desde otra clase en Kotlin!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun tiempominutos(time: TextView) {
        when (botonSeleccionado) {
            "10 min" -> {                time.setText(thenTime)            }

            "5 min" -> {                time.setText(fiveTime)            }

            "3 min" -> {                time.setText(threeTime)            }

            "30 min" -> {                time.setText(thirtyTime)            }
        }
    }
}