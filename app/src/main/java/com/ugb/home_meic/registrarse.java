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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrarse extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button signup;
    TextView signIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        editTextEmail = findViewById(R.id.re_correo);
        editTextPassword  = findViewById(R.id.re_contrasena);
        signIn = findViewById(R.id.re_IniciarS);
        signup = findViewById(R.id.re_registrarse);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registrarse.this, IniciarSesion.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(registrarse.this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(registrarse.this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(registrarse.this, "Registro realizado con exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registrarse.this, IniciarSesion.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(registrarse.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}