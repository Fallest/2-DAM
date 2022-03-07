package com.example.android2_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class CrearMensajeActivity : AppCompatActivity() {
    // Variables para los elementos visuales
    private lateinit var textTituloMensaje: TextView
    private lateinit var textMensaje: TextView
    private lateinit var buttonGuardar: Button
    // Variables para la traducción
    private var idioma: String? = "español"
    private var diccionarioES: HashMap<String, String> = HashMap()
    private var diccionarioEN: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_mensaje)

        // Obtenemos el idioma que se está utilizando
        idioma = intent.extras?.get("IDIOMA") as String?

        // Variables para el texto y el titulo
        textTituloMensaje = findViewById(R.id.textTituloMensaje)
        textMensaje = findViewById(R.id.textMensaje)
        buttonGuardar = findViewById(R.id.buttonGuardarMensaje)

        // Inicialización de diccionarios y traducción
        initDiccionarios()
        translate()
    }

    // Función para el botón Guardar
    fun guardar(view: View) {
        // Guarda el mensaje escrito y sale de la Activity
        setResult(RESULT_OK, Intent().apply {
            putExtra("DATO", textMensaje.text.toString())
        })
        finish()
    }

    // Funciones para la traducción
    private fun initDiccionarios() {
        // Inicializar los diccionarios con los datos
        diccionarioES.put("titulo", "Escriba un mensaje para el registro")
        diccionarioES.put("boton1", "Guardar")

        diccionarioEN.put("titulo", "Write a message for the log")
        diccionarioEN.put("boton1", "Save")
    }

    private fun translate() {
        // Si el idioma escogido es español
        if (idioma.equals("español")) {
            textTituloMensaje.text = diccionarioES["titulo"]
            buttonGuardar.text = diccionarioES["boton1"]
        }
        // Si el idioma escogido es inglés
        else if (idioma.equals("english")) {
            textTituloMensaje.text = diccionarioEN["titulo"]
            buttonGuardar.text = diccionarioEN["boton1"]
        }
    }
}