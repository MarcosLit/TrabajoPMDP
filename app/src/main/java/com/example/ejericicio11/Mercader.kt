package com.example.ejericicio11

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Mercader : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)

        var cambio : Intent

        var continuar = findViewById<Button>(R.id.continuarComercio)
        var comerciar = findViewById<Button>(R.id.comerciar)
        var linearOpcion = findViewById<LinearLayout>(R.id.linearOpcion)
        var linearComercio = findViewById<LinearLayout>(R.id.linearComercio)
        var cancelarComercio = findViewById<Button>(R.id.cancelarComercio)
        var imagenMercader = findViewById<ImageView>(R.id.imagenMercader)
        var layoutComprar = findViewById<LinearLayout>(R.id.layoutComprar)
        var layoutVenta = findViewById<LinearLayout>(R.id.layoutVenta)
        var comprar = findViewById<Button>(R.id.comprar)
        var vender = findViewById<Button>(R.id.vender)
        var aceptarTrueque = findViewById<Button>(R.id.aceptarTrueque)
        var compraMas  = findViewById<Button>(R.id.compraMas)
        var compraMenos = findViewById<Button>(R.id.compraMenos)
        var vendeMas = findViewById<Button>(R.id.vendeMas)
        var vendeMenos = findViewById<Button>(R.id.vendeMenos)
        var compraCantidad = findViewById<TextView>(R.id.compraCantidad)
        var vendeCantidad = findViewById<TextView>(R.id.vendeCantidad)
        var musica = findViewById<ImageView>(R.id.musica)
        musica.contentDescription=intent.getStringExtra("estado")
        if (musica.contentDescription.equals("musica")){
            musica.setImageResource(R.drawable.baseline_music_note_24)
        }else if (musica.contentDescription.equals("nomusica")){
            musica.setImageResource(R.drawable.baseline_music_off_24)
        }


        comerciar.setOnClickListener(){
            linearOpcion.visibility = (View.INVISIBLE)
            linearComercio.visibility = (View.VISIBLE)
        }

        cancelarComercio.setOnClickListener(){
            imagenMercader.setImageResource(R.drawable.mercader)
            linearComercio.visibility = (View.INVISIBLE)
            linearOpcion.visibility = (View.VISIBLE)
            layoutVenta.visibility = (View.INVISIBLE)
            layoutComprar.visibility = (View.INVISIBLE)
            aceptarTrueque.visibility = (View.INVISIBLE)
        }

        comprar.setOnClickListener(){
            imagenMercader.setImageResource(R.drawable.obj)
            layoutComprar.visibility = (View.VISIBLE)
            layoutVenta.visibility = (View.INVISIBLE)
            vendeCantidad.text = "0"
            aceptarTrueque.visibility = (View.VISIBLE)
        }

        vender.setOnClickListener(){
            imagenMercader.setImageResource(R.drawable.mochila)
            layoutComprar.visibility = (View.INVISIBLE)
            layoutVenta.visibility = (View.VISIBLE)
            compraCantidad.text = "0"
            aceptarTrueque.visibility = (View.VISIBLE)
        }

        compraMas.setOnClickListener(){
            compraCantidad.text = (compraCantidad.text.toString().toInt()+1).toString()
        }
        compraMenos.setOnClickListener(){
            if (compraCantidad.text.toString().toInt()>0)
                compraCantidad.text = (compraCantidad.text.toString().toInt()-1).toString()
        }


        vendeMas.setOnClickListener(){
            if (vendeCantidad.text.toString().toInt()+1 <= personaje1.mochila.interior.size)
                vendeCantidad.text = (vendeCantidad.text.toString().toInt()+1).toString()
            else
                Toast.makeText(this, "No tienes esa cantidad de objetos en la mochila", Toast.LENGTH_LONG).show()
        }

        vendeMenos.setOnClickListener(){
            if (vendeCantidad.text.toString().toInt()>0){
                vendeCantidad.text = (vendeCantidad.text.toString().toInt()-1).toString()
            }
        }


        continuar.setOnClickListener(){
            cambio = Intent(this, InicioAventura::class.java)
            cambio.putExtra("estado", musica.contentDescription.toString())
            startActivity(cambio)
        }

        aceptarTrueque.setOnClickListener(){
            //compra vende Cantidad
            if(layoutComprar.visibility == View.VISIBLE){
                var cont : Int = compraCantidad.text.toString().toInt()
                do {
                    if ((personaje1.mochila.getPesoMochila() - articulo.getPeso()) >= 0){
                        personaje1.mochila.setPesoMochila(personaje1.mochila.getPesoMochila() - articulo.getPeso())
                        personaje1.mochila.interior.add(articulo)
                        print("Peso de la mochila cambiado -> ")
                        println(personaje1.mochila.getPesoMochila().toString())
                        cont--
                    }
                }while(cont > 0)
            }else if (layoutVenta.visibility == View.VISIBLE){
                var cont2 : Int = vendeCantidad.text.toString().toInt()
                    do{
                        if ((personaje1.mochila.getPesoMochila() + articulo.getPeso()) <= 100){
                            personaje1.mochila.setPesoMochila(personaje1.mochila.getPesoMochila() + articulo.getPeso())
                            personaje1.mochila.interior.removeAt(0)
                            print("Peso de la mochila cambiado -> ")
                            println(personaje1.mochila.getPesoMochila().toString())
                            cont2--
                        }
                }while (cont2 > 0)
            }

            compraCantidad.text = "0"
            vendeCantidad.text = "0"

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