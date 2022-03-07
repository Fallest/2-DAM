package com.example.android2_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class RegHoraActivity : AppCompatActivity() {
    // Variables para los elementos visuales
    private lateinit var textTituloTimestamp: TextView
    private lateinit var textFecha: TextView
    private lateinit var textHora: TextView
    private lateinit var buttonGuardar: Button
    // Variables para la traducción
    private var idioma: String? = "español"
    private var diccionarioES: HashMap<String, String> = HashMap()
    private var diccionarioEN: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_hora)

        // Obtenemos el idioma que se está utilizando
        idioma = intent.extras?.get("IDIOMA") as String?

        // Variables para los elementos visuales
        textTituloTimestamp = findViewById(R.id.textTituloTimestamp)
        textFecha = findViewById(R.id.textDate)
        textHora = findViewById(R.id.textTime)
        buttonGuardar = findViewById(R.id.buttonGuardarTimestamp)

        // Inicialización de diccionarios y traducción
        initDiccionarios()
        translate()
    }

    // Función para el botón Guardar
    fun guardar(view: View) {
        // Guarda el mensaje escrito y sale de la Activity
        setResult(RESULT_OK, Intent().apply {
            putExtra("DATO", textFecha.text.toString() + " - " + textHora.text.toString())
        })
        finish()
    }

    // Funciones para la traducción
    private fun initDiccionarios() {
        // Inicializar los diccionarios con los datos
        diccionarioES.put("titulo", "Date el registro")
        diccionarioES.put("boton1", "Guardar")

        diccionarioEN.put("titulo", "Date the log")
        diccionarioEN.put("boton1", "Save")
    }

    private fun translate() {
        // Si el idioma escogido es español
        if (idioma.equals("español")) {
            textTituloTimestamp.text = diccionarioES["titulo"]
            buttonGuardar.text = diccionarioES["boton1"]
        }
        // Si el idioma escogido es inglés
        else if (idioma.equals("english")) {
            textTituloTimestamp.text = diccionarioEN["titulo"]
            buttonGuardar.text = diccionarioEN["boton1"]
        }
    }
}