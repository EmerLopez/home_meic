package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class IniciarSesion extends AppCompatActivity {

    // VARIABLES
    Button continuar, registrar;
    ImageView imagen;
    TextView holaTexto, iniciarTexto;
    TextInputLayout nombreUsuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_iniciar_sesion);


        imagen = findViewById(R.id.ini_logo_imagen);
        holaTexto = findViewById(R.id.ini_hola);
        iniciarTexto = findViewById(R.id.ini_iniciar);
        nombreUsuario = findViewById(R.id.ini_nombre_usuario);
        contrasena = findViewById(R.id.ini_contrasena);
        continuar = findViewById(R.id.ini_continuar);
        registrar = findViewById(R.id.ini_registrarse);

        //IR A PANTALLA REGISTRARSE

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IniciarSesion.this, MainActivity.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(imagen, "logo_imagen1");
                pairs[1] = new Pair<View, String>(holaTexto, "logo_texto2");
                pairs[2] = new Pair<View, String>(iniciarTexto, "iniciar_logo3");
                pairs[3] = new Pair<View, String>(nombreUsuario, "nombre_logo4");
                pairs[4] = new Pair<View, String>(contrasena, "contrasena_logo5");
                pairs[5] = new Pair<View, String>(continuar, "continuar_logo6");
                pairs[6] = new Pair<View, String>(registrar, "registrarse_logo7");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(IniciarSesion.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

        //IR A PANTALLA PERFIL USUARIO

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();

                Pair[] pairs = new Pair[5];

                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");
                pairs[1] = new Pair<View, String>(iniciarTexto, "iniciar_logo3");
                pairs[2] = new Pair<View, String>(nombreUsuario, "nombre_logo4");
                pairs[3] = new Pair<View, String>(contrasena, "contrasena_logo5");
                pairs[4] = new Pair<View, String>(continuar, "continuar_logo6");


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(IniciarSesion.this, pairs);
                    startActivity(intent1, options.toBundle());
                }

            }
        });

        //IR A PANEL DE INICIO

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(IniciarSesion.this, PanelDeInicio.class);

                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(IniciarSesion.this, pairs);
                    startActivity(intent1, options.toBundle());
                }

            }
        });

    }
}