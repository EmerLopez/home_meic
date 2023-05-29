package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleProductActivity extends AppCompatActivity {

    TextView solNombreProducto, solPrecio, solMarca, solDescripcion, solEspecificaciones, solDisponibilidad;

    ImageView solImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        solNombreProducto = findViewById(R.id.solNombreProducto);
        solPrecio = findViewById(R.id.solPrecio);
        solMarca = findViewById(R.id.solMarca);
        solDescripcion = findViewById(R.id.solDescripcion);
        solEspecificaciones = findViewById(R.id.solEspecificaciones);
        solDisponibilidad = findViewById(R.id.solDisponibilidad);

        solImagen = findViewById(R.id.solImagen);

        Picasso.get().load(getIntent().getStringExtra("solImagen")).placeholder(R.drawable.laptop).into(solImagen);

        solNombreProducto.setText(getIntent().getStringExtra("solNombreProducto"));
        solPrecio.setText(getIntent().getStringExtra("solPrecio"));
        solMarca.setText(getIntent().getStringExtra("solMarca"));
        solDescripcion.setText(getIntent().getStringExtra("solDescripcion"));
        solEspecificaciones.setText(getIntent().getStringExtra("solEspecificaciones"));
        solDisponibilidad.setText(getIntent().getStringExtra("solDisponibilidad"));


    }
}