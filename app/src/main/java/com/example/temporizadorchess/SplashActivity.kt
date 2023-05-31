package com.example.temporizadorchess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val SPLASH_TIME_OUT = 1000 // 3 segundos

        Handler().postDelayed({
            // Aquí se inicia la actividad principal después del tiempo de espera
            startActivity(Intent(this, MainActivity::class.java))
            // Cierra esta actividad
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }


}