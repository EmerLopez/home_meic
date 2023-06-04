package com.ugb.home_meic;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ugb.home_meic.model.Persona;

import java.util.UUID;

public class registrarse extends AppCompatActivity {
    EditText TextEmail, TextPassword, TextRPassword, TextNombre, TextApellido;
    Button registrarseR;
    TextView IsesionR;

    DBHelper DB;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    boolean validar = false;

    Persona personaSelected;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        TextEmail = (EditText) findViewById(R.id.re_correo);
        TextPassword  = (EditText) findViewById(R.id.re_contrasena);
        TextRPassword  = (EditText) findViewById(R.id.re_vcontrasena);
        TextNombre = (EditText) findViewById(R.id.nombre);
        TextApellido = (EditText) findViewById(R.id.re_apellido);
        IsesionR = findViewById(R.id.re_IniciarS);
        registrarseR = findViewById(R.id.re_registrarse);
        DB = new DBHelper(this);

        IsesionR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registrarse.this, IniciarSesion.class);
                startActivity(intent);
                finish();
            }
        });

        inicializarFirebase();

        registrarseR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = TextNombre.getText().toString();
                String correo = TextEmail.getText().toString();
                String password = TextPassword.getText().toString();
                String app = TextApellido.getText().toString();
                String repassword = TextRPassword.getText().toString();


                if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(app)) {
                    Toast.makeText(registrarse.this, "Campos vacios, llena todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(repassword)){
                        Persona p = new Persona();
                        p.setUid(UUID.randomUUID().toString());
                        p.setNombre(nombre);
                        p.setApellido(app);
                        p.setCorreo(correo);
                        p.setPassword(password);
                        databaseReference.child("Persona").child(p.getUid()).setValue(p);


                        Boolean checkuser = DB.checkusername(correo);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(correo, password);
                            if (insert == true) {
                                Toast.makeText(registrarse.this, "Registro con exito ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), IniciarSesion.class);
                                startActivity(intent);
                            }
                        }
                    }else {
                        Toast.makeText(registrarse.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }


            }


        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

}
