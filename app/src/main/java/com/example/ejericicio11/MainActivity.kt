package com.example.ejericicio11

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.himnoespana)
        mediaPlayer.start()
        mediaPlayer.isLooping=true

        title = "Comienzo Aventura"
        var registrar = findViewById<Button>(R.id.registrar)
        var acceder = findViewById<Button>(R.id.acceder)
        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)
        var cambio: Intent
        registrar.setOnClickListener(){
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful)
                        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this, "No se ha podido registrar el usuario", Toast.LENGTH_LONG).show()
                }
            }
        }

        acceder.setOnClickListener(){
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        cambio = Intent(this, clase::class.java)
                        startActivity(cambio)
                    }
                    else
                        Toast.makeText(this, "Para acceder con este correo primero debes registrarte", Toast.LENGTH_LONG).show()
                }
            }
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