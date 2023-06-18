package com.example.temporizadorchess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.RadioGroup

class Configuracion : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)



        val acercadeJuego = findViewById<Button>(R.id.acercadeJuego)
        val btnnewTiempo = findViewById<Button>(R.id.nuevoTiempo)

        val idMinuto = findViewById<EditText>(R.id.editMinutos)
        val idSegundo = findViewById<EditText>(R.id.editSegundos)

        val retorno = findViewById<ImageView>(R.id.atras)

        val radioGroup = findViewById<RadioGroup>(R.id.MinutosVerif)


        btnnewTiempo.isEnabled = false



        //Validar Seleccion
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // obtén la opción seleccionada
            //val radioButton = findViewById<RadioButton>(checkedId)

            idMinuto.text.clear(); idSegundo.text.clear();
            newSelectionText(btnnewTiempo, true)
            //val opcionSeleccionada = radioButton.text.toString()
            val number= radioGroup.checkedRadioButtonId?.let {
                findViewById<RadioButton>(it).text.toString().replace("\\D+".toRegex(), "")
            }
            DatosEnvi.tiempoJuego = number.toString()
        }

        //click acerca del juego
        acercadeJuego.setOnClickListener{
            val intent = Intent(this, AcercadeJuego::class.java)
            startActivity(intent)
        }
        //click reiniciar tiempo nuevo
        btnnewTiempo.setOnClickListener {
            if(!idMinuto.text.isNullOrEmpty()){DatosEnvi.tiempoJuego = idMinuto.text.toString()}

            val intent = Intent(this, Temporizador::class.java)
            startActivity(intent)
            finish()
        }
        //Click minutos

        retorno.setOnClickListener { finish() }


        //mandar directo al textoSegundo

        FocusTextSegundo(idMinuto, idSegundo)
        //validar que si se escribio datos
        ValidarMinutos(btnnewTiempo, idMinuto)
        //validad segundos
        ValidarSegundos(idSegundo)

    }//-------------------------------------------------------------------------------

    private fun FocusTextSegundo(_idMinuto: EditText, _idSegundo: EditText) {
        //_idMinuto.setOnEditorActionListener { _, _, _ ->
        //poner en focus
        /*_idSegundo.requestFocus()*/
        //poner ceros predeterminados
        _idSegundo.setText("00")
            //newSelectionText(_radioGroup, false)


        //dar bandera  de "si vete alli"
        //true
        //}
    }

    private fun ValidarMinutos(_btnnewTiempo: Button, _idMinuto: EditText) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty() || s.toString().toInt() < 3 || s.toString().toInt() > 90) {
                    newSelectionText(_btnnewTiempo, false)
                } else {
                    newSelectionText(_btnnewTiempo, true)

                }
            }
        }
        _idMinuto.addTextChangedListener(textWatcher)
    }

    private fun ValidarSegundos(_idSegundo:EditText){
        _idSegundo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val valueStr = s.toString()
                if (!valueStr.isNullOrEmpty()) {
                    val value = valueStr.toInt()
                    if (value > 59  ) {
                        _idSegundo.setText("59")
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun newSelectionBoton(_radioValidar:RadioButton, bool : Boolean){
        if(bool)
        {_radioValidar.isChecked = true }
        else
        {_radioValidar.isChecked = false }
    }

    private fun newSelectionText (_btnnewTiempo: Button, bool: Boolean){
        if(bool)
        {_btnnewTiempo.isEnabled = true}
        else
        {_btnnewTiempo.isEnabled = false}
    }

}
