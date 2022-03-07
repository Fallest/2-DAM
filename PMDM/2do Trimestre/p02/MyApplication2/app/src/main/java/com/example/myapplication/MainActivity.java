package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int pulsaciones = 0;
    Button boton_pulsame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton_pulsame = (Button) findViewById(R.id.btnSend);
        boton_pulsame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                /** Llamada a la hija con startActivityForResult() nos               *
                 *  obliga a sobreescribir onActivityResult(), más abajo.            *
                 *  Se podría llamar con startActivity() si la hija no devuelve nada */

                startActivityForResult(intent, 11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView texto = (TextView) findViewById(R.id.textView);

        if ((resultCode == RESULT_OK) && (requestCode == 11)) {
            texto.setText(data.getExtras().getCharSequence("DATO"));

            /** Usamos data.getExtras() si la hija usa putExtra() *
             *  o data.getData() si la hija usa setData()         */


        } else {
            if (resultCode != RESULT_OK)
                texto.setText("No es ResultOK");
            else
                texto.setText("No es 11");
        }
    }

    /* Prueba de modificación de otros métodos del ciclo de vida */

    protected void onResume() 	{
        super.onResume();

        boton_pulsame.setText(" Pulsame: "+pulsaciones);
        pulsaciones++;
    }

}