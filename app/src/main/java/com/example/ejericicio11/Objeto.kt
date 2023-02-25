package com.example.ejericicio11

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class Objeto : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        var cambio : Intent

        var recoger = findViewById<Button>(R.id.recoger)
        var continuarRecoger = findViewById<Button>(R.id.continuarRecoger)
        var musica = findViewById<ImageView>(R.id.musica)
        musica.contentDescription=intent.getStringExtra("estado")
        if (musica.contentDescription.equals("musica")){
            musica.setImageResource(R.drawable.baseline_music_note_24)
        }else if (musica.contentDescription.equals("nomusica")){
            musica.setImageResource(R.drawable.baseline_music_off_24)
        }


        recoger.setOnClickListener(){
            if ((personaje1.mochila.getPesoMochila() - articulo.getPeso()) >= 0){
                personaje1.mochila.setPesoMochila(personaje1.mochila.getPesoMochila() - articulo.getPeso())
                personaje1.mochila.interior.add(articulo)
                println("Objeto introducido")
                print("Peso de la mochila cambiado ->")
                println(personaje1.mochila.getPesoMochila().toString())
                println(personaje1.mochila.interior.size.toString())
            }else{
                Toast.makeText(this, "La mochila esta llena, por favor contacta con el mercader", Toast.LENGTH_LONG).show()
            }
        }

        continuarRecoger.setOnClickListener(){
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