package com.example.android2_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    // Atributos para guardar los datos de las Activities
    private var idioma: String? = "español"
    private var mensaje: String? = ""
    // La fecha y hora por defecto es el momento en el que se abre la app
    private var timestamp: String? = SimpleDateFormat("dd/MM/yyyy '-' HH:mm:ss").format(Date())
    // Para traducir la aplicación vamos a usar dos diccionarios (HashMaps)
    private var diccionarioES: HashMap<String, String> = HashMap()
    private var diccionarioEN: HashMap<String, String> = HashMap()
    // Variables para los botones y el titulo
    private lateinit var textTitulo: TextView
    private lateinit var buttonCambiarIdioma: Button
    private lateinit var buttonCrearMensaje: Button
    private lateinit var buttonRegHora: Button
    private lateinit var buttonVerDatos: Button
    private lateinit var buttonSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inicializadores de la actividad principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDiccionarios()

        // Inicializamos los botones y el titulo
        textTitulo = findViewById(R.id.textTitulo)
        buttonCambiarIdioma = findViewById<Button>(R.id.button_cambiarIdioma)
        buttonCrearMensaje = findViewById<Button>(R.id.button_crearMensaje)
        buttonRegHora = findViewById<Button>(R.id.button_regHora)
        buttonVerDatos = findViewById<Button>(R.id.button_verDatos)
        buttonSalir = findViewById<Button>(R.id.button_salir)

        Log.w(idioma, "Ha ocurrido un error con el idioma")
    }

    // Sobrescritura de onResume
    override fun onResume() {
        super.onResume()

        translate()
    }

    // Funciones para los botones
    fun cambiarIdioma(view: View) {
        // Inicia la actividad para cambiar el idioma
        val intent = Intent(this, CambiarIdiomaActivity::class.java).apply {
            putExtra("IDIOMA", idioma)
        }
        startActivityForResult(intent, 1)
    }
    fun crearMensaje(view: View) {
        // Inicia la actividad para crear un mensaje
        val intent = Intent(this, CrearMensajeActivity::class.java).apply {
            putExtra("IDIOMA", idioma)
        }
        startActivityForResult(intent, 2)
    }
    fun registrarHora(view: View) {
        // Inicia la actividad para registrar la hora
        val intent = Intent(this, RegHoraActivity::class.java).apply {
            putExtra("IDIOMA", idioma)
        }
        startActivityForResult(intent, 3)
    }
    fun verDatos(view: View) {
        // Inicia la actividad para ver los datos guardados
        val intent = Intent(this, VerDatosActivity::class.java).apply {
            putExtra("IDIOMA", idioma)
            putExtra("TIMESTAMP", timestamp)
            putExtra("MENSAJE", mensaje)
        }
        startActivity(intent)
    }
    fun salir(view: View) {
        // Cierra la actividad
        finish()
    }

    // Función para rescatar los datos de las actividades terminadas
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            // Si vuelve de la Activity CambiarIdioma
            if (requestCode == 1) {
                idioma = data?.extras?.get("DATO") as String?
            }
            // Si vuelve de la Activity CrearMensaje
            else if (requestCode == 2) {
                mensaje = data?.extras?.get("DATO") as String?
            }
            // Si vuelve de la Activity RegHora
            else if (requestCode == 3) {
                timestamp = data?.extras?.get("DATO") as String?
            }
        }
    }

    // Función para rellenar los diccionarios con datos
    private fun initDiccionarios() {
        // Inicializar los diccionarios con los datos
        diccionarioES.put("titulo", "Bienvenido a Logger")
        diccionarioES.put("boton1", "Cambiar Idioma")
        diccionarioES.put("boton2", "Crear Mensaje")
        diccionarioES.put("boton3", "Registrar Hora")
        diccionarioES.put("boton4", "Ver Datos")
        diccionarioES.put("boton5", "Salir")

        diccionarioEN.put("titulo", "Welcome to Logger")
        diccionarioEN.put("boton1", "Change Language")
        diccionarioEN.put("boton2", "Create Message")
        diccionarioEN.put("boton3", "Register Timestamp")
        diccionarioEN.put("boton4", "Check Data")
        diccionarioEN.put("boton5", "Exit")
    }

    // Función traductora
    private fun translate() {
        // Si el idioma escogido es español
        if (idioma == "español") {
            textTitulo.text = diccionarioES["titulo"]
            buttonCambiarIdioma.text = diccionarioES["boton1"]
            buttonCrearMensaje.text = diccionarioES["boton2"]
            buttonRegHora.text = diccionarioES["boton3"]
            buttonVerDatos.text = diccionarioES["boton4"]
            buttonSalir.text = diccionarioES["boton5"]

        }
        // Si el idioma escogido es inglés
        else if (idioma == "english") {
            textTitulo.text = diccionarioEN["titulo"]
            buttonCambiarIdioma.text = diccionarioEN["boton1"]
            buttonCrearMensaje.text = diccionarioEN["boton2"]
            buttonRegHora.text = diccionarioEN["boton3"]
            buttonVerDatos.text = diccionarioEN["boton4"]
            buttonSalir.text = diccionarioEN["boton5"]
        }
    }
}