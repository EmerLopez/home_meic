package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailElectroActivity extends AppCompatActivity {

    TextView detailDesc, detailProducto, detailPrecio, detailMarca, detailDisp, detailEspecs;
    ImageView detailImage, back;

    TextView titulo;
    String key = "";
    String imageUrl = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_electro);


        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailProducto = findViewById(R.id.detailProducto);
        detailPrecio = findViewById(R.id.detailPrecio);
        detailMarca = findViewById(R.id.detailMarca);
        detailDisp = findViewById(R.id.detailDisp);
        detailEspecs = findViewById(R.id.detailEspecs);


        titulo = findViewById(R.id.tv_title_toolbar);
        titulo.setText("");

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailElectroActivity.this, catalogoElectro_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailProducto.setText(bundle.getString("Producto"));
            detailPrecio.setText(bundle.getString("Precio"));
            detailMarca.setText(bundle.getString("Marca"));
            detailDesc.setText(bundle.getString("Descripcion"));
            detailEspecs.setText(bundle.getString("Especificaciones"));
            detailDisp.setText(bundle.getString("Disponibilidad"));
            key = bundle.getString("Key");

            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}