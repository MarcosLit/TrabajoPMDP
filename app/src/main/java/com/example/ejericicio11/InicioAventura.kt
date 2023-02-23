package com.example.ejericicio11

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class InicioAventura : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_aventura)

        var dado = findViewById<ImageButton>(R.id.dado)
        var cambio = Intent(this, InicioAventura::class.java)



        dado.setOnClickListener(){
            val numeros = 1..4
            val numero = numeros.random().toString()
            when(numero){
                "1"->cambio= Intent(this, Ciudad::class.java)
                "2"->cambio= Intent(this, Enemigo::class.java)
                "3"->cambio= Intent(this, Mercader::class.java)
                "4"->cambio= Intent(this, Objeto::class.java)
            }
            startActivity(cambio)
        }

        var musica = findViewById<ImageView>(R.id.musica)
        mediaPlayer.isLooping=true
        musica.setOnClickListener(){
            if (musica.contentDescription.equals("musica")){
                mediaPlayer.pause()
                musica.setImageResource(R.drawable.baseline_music_off_24)
                musica.contentDescription="nomusica"
            }else if (musica.contentDescription.equals("nomusica")){
                mediaPlayer.start()
                musica.setImageResource(R.drawable.baseline_music_note_24)
                musica.contentDescription="musica"
            }
        }



    }
}