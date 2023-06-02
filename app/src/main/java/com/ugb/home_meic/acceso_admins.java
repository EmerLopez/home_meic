package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class acceso_admins extends AppCompatActivity {
    ImageView back;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_admins);

        titulo = findViewById(R.id.tv_title_toolbar);
        titulo.setText("");

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(acceso_admins.this, IniciarSesion.class);
                startActivity(intent);
                finish();
            }
        });
    }
}