package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class muebles extends AppCompatActivity {
    TextView detailDesc, detailProducto, detailPrecio, detailMarca, detailDisp, detailEspecs;
    ImageView detailImage, back;
    Button deleteButton, editButton;
    TextView titulo;
    String key = "";
    String imageUrl = "";
    @SuppressLint("MissingInflatedId")

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muebles);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailProducto = findViewById(R.id.detailProducto);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
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
                Intent intent = new Intent(muebles.this, mainMuebles.class);
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
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Categoria Muebles");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(muebles.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), mainMuebles.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(muebles.this, updateMuebles.class)
                        .putExtra("Producto", detailProducto.getText().toString())
                        .putExtra("Precio", detailPrecio.getText().toString())
                        .putExtra("Marca", detailMarca.getText().toString())
                        .putExtra("Descripcion", detailDesc.getText().toString())
                        .putExtra("Especificaciones", detailEspecs.getText().toString())
                        .putExtra("Disponibilidad", detailDisp.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}