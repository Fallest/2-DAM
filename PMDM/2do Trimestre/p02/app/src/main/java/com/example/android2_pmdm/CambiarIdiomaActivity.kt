package com.example.android2_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class CambiarIdiomaActivity : AppCompatActivity() {
    // Variables para los botones y el título
    private lateinit var textTituloIdioma: TextView
    private lateinit var buttonEspanol: Button
    private lateinit var buttonIngles: Button
    // Variables para la traducción
    private var idioma: String? = "español"
    private var diccionarioES: HashMap<String, String> = HashMap()
    private var diccionarioEN: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_idioma)

        // Obtenemos el idioma que se está utilizando
        idioma = intent.extras?.get("IDIOMA") as String?

        // Variables para los botones y el titulo
        textTituloIdioma = findViewById<TextView>(R.id.textTituloIdioma)
        buttonEspanol = findViewById<Button>(R.id.buttonEspañol)
        buttonIngles = findViewById<Button>(R.id.buttonIngles)
        initDiccionarios()
        translate()
    }

    // Funciones para los botones
    fun cambiarEspanol(view: View) {
        // Cambia el idioma a español
        setResult(RESULT_OK, Intent().apply {
            putExtra("DATO", "español")
        })
        finish()
    }
    fun cambiarIngles(view: View) {
        // Cambia el idioma a inglés
        var data = Intent().apply {
            putExtra("DATO", "english")
        }

        setResult(RESULT_OK, data)
        finish()
    }

    // Funciones para traducir
    private fun initDiccionarios() {
        // Inicializar los diccionarios con los datos
        diccionarioES.put("titulo", "Seleccione un idioma")
        diccionarioES.put("boton1", "Español")
        diccionarioES.put("boton2", "Inglés")

        diccionarioEN.put("titulo", "Choose a language")
        diccionarioEN.put("boton1", "Spanish")
        diccionarioEN.put("boton2", "English")
    }

    private fun translate() {
        // Si el idioma escogido es español
        if (idioma.equals("español")) {
            textTituloIdioma.text = diccionarioES["titulo"]
            buttonEspanol.text = diccionarioES["boton1"]
            buttonIngles.text = diccionarioES["boton2"]
        }
        // Si el idioma escogido es inglés
        else if (idioma.equals("english")) {
            textTituloIdioma.text = diccionarioEN["titulo"]
            buttonEspanol.text = diccionarioEN["boton1"]
            buttonIngles.text = diccionarioEN["boton2"]
        }
    }
}