package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class pantalla_bienvenida extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    //VARIABLES
    ImageView imagen;
    TextView logo;

    //ANIMACIONES
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);
        //PRIMERA PANTALLA
        imagen = findViewById(R.id.fondo_imagen);
        logo = findViewById(R.id.logo);

        //ANIMACIONES
        topAnim = AnimationUtils.loadAnimation(this, R.anim.lateral_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.inferior_anim);

        //ESTABLECER ANIMACIONES EN ELEMENTOS
        imagen.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(pantalla_bienvenida.this, Menu_Cliente.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(imagen, "logo_imagen");
                pairs[1] = new Pair<View, String>(logo, "logo_texto");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(pantalla_bienvenida.this, pairs);
                    startActivity(intent, options.toBundle());
                }


            }
        },SPLASH_SCREEN);
    }
}