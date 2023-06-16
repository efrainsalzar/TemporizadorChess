package com.example.temporizadorchess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker

class Configuracion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)


        val acercade = findViewById<Button>(R.id.acercade)
        val btnnewTiempo = findViewById<Button>(R.id.nuevoTiempo)

        val idMinuto = findViewById<EditText>(R.id.editMinutos)
        val idSegundo = findViewById<EditText>(R.id.editSegundos)



        btnnewTiempo.isEnabled = false


        //mandar directo al textoSegundo
        FocusTextSegundo(idMinuto,idSegundo)
        //validar que si se escribio datos
        ValidarDatos(btnnewTiempo, idMinuto)


        //click acercade
        acercade.setOnClickListener {
            val intent = Intent(this, acercadenosotros::class.java)
            startActivity(intent)
        }
        //click reiniciar tiempo nuevo
        btnnewTiempo.setOnClickListener {
            val intent = Intent(this, Temporizador::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun FocusTextSegundo(_idMinuto: EditText,_idSegundo: EditText){
        //_idMinuto.setOnEditorActionListener { _, _, _ ->
            //poner en focus
            //_idSegundo.requestFocus()
            //poner ceros predeterminados
            _idSegundo.setText("00")
            //dar bandera  de "si vete alli"
            //true
        //}
    }

    private fun ValidarDatos(_btnnewTime: Button, _idMinuto: EditText) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty() || s.toString().toInt() < 3 || s.toString().toInt() > 90) {
                    _btnnewTime.isEnabled = false
                } else {
                    _btnnewTime.isEnabled = true
                }
            }

        }
        _idMinuto.addTextChangedListener(textWatcher)
    }


}
