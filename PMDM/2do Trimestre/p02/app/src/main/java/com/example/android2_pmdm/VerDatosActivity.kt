package com.example.android2_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerDatosActivity : AppCompatActivity() {
    // Variables para los elementos visuales
    private lateinit var textTituloDatos: TextView
    private lateinit var textIdioma: TextView
    private lateinit var textTimestamp: TextView
    private lateinit var textMensaje: TextView
    private lateinit var labelIdioma: TextView
    private lateinit var labelFecha: TextView
    private lateinit var labelMensaje: TextView
    // Variables para la traducción
    private var idioma: String? = "español"
    private var diccionarioES: HashMap<String, String> = HashMap()
    private var diccionarioEN: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_datos)

        // Obtenemos el idioma que se está utilizando
        idioma = intent.extras?.get("IDIOMA") as String?

        // Variables para los elementos visuales
        textTituloDatos = findViewById<TextView>(R.id.textTituloDatos)
        textIdioma = findViewById<TextView>(R.id.textIdioma)
        textTimestamp = findViewById<TextView>(R.id.textFecha)
        textMensaje = findViewById<TextView>(R.id.textMensaje)
        labelIdioma = findViewById<TextView>(R.id.labelIdioma)
        labelFecha = findViewById<TextView>(R.id.labelFecha)
        labelMensaje = findViewById<TextView>(R.id.labelMensaje)

        // Extraemos los datos que vienen en el Intent
        textIdioma.text = intent.extras?.get("IDIOMA") as String?
        textTimestamp.text = intent.extras?.get("TIMESTAMP") as String?
        textMensaje.text = intent.extras?.get("MENSAJE") as String?

        // Traducimos
        initDiccionarios()
        translate()
    }

    // Funciones para traducir
    private fun initDiccionarios() {
        // Inicializar los diccionarios con los datos
        diccionarioES.put("titulo", "Datos a registrar")
        diccionarioES.put("texto1", "Idioma:")
        diccionarioES.put("texto2", "Fecha y Hora:")
        diccionarioES.put("texto3", "Mensaje:")

        diccionarioEN.put("titulo", "Data to log")
        diccionarioEN.put("texto1", "Language:")
        diccionarioEN.put("texto2", "Date and Time:")
        diccionarioEN.put("texto3", "Message:")
    }

    private fun translate() {
        // Si el idioma escogido es español
        if (idioma.equals("español")) {
            textTituloDatos.text = diccionarioES["titulo"]
            labelIdioma.text = diccionarioES["texto1"]
            labelFecha.text = diccionarioES["texto2"]
            labelMensaje.text = diccionarioES["texto3"]
        }
        // Si el idioma escogido es inglés
        else if (idioma.equals("english")) {
            textTituloDatos.text = diccionarioEN["titulo"]
            labelIdioma.text = diccionarioEN["texto1"]
            labelFecha.text = diccionarioEN["texto2"]
            labelMensaje.text = diccionarioEN["texto3"]
        }
    }
}