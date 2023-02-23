package com.example.ejericicio11

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class Ciudad : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)

        var cambio:Intent
        var entrar = findViewById<Button>(R.id.entrar)
        var continar = findViewById<Button>(R.id.continuarCiudad)
        var musica = findViewById<ImageView>(R.id.musica)
        musica.contentDescription=intent.getStringExtra("estado")
        if (musica.contentDescription.equals("musica")){
            musica.setImageResource(R.drawable.baseline_music_note_24)
        }else if (musica.contentDescription.equals("nomusica")){
            musica.setImageResource(R.drawable.baseline_music_off_24)
        }


        entrar.setOnClickListener(){
            Toast.makeText(this, "Entrar", Toast.LENGTH_SHORT).show()
        }

        continar.setOnClickListener(){
            cambio = Intent(this, InicioAventura::class.java)
            cambio.putExtra("estado", musica.contentDescription.toString())
            startActivity(cambio)
        }


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