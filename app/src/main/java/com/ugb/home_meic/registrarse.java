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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrarse extends AppCompatActivity {
    EditText TextEmail, TextPassword, TextRPassword;
    Button registrarseR;
    TextView IsesionR;

    DBHelper DB;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        TextEmail = (EditText) findViewById(R.id.re_correo);
        TextPassword  = (EditText) findViewById(R.id.re_contrasena);
        TextRPassword  = (EditText) findViewById(R.id.re_vcontrasena);
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

        registrarseR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = TextEmail.getText().toString();
                String password = TextPassword.getText().toString();
                String repassword = TextRPassword.getText().toString();
                /*
                String email, password, repassword;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                repassword = String.valueOf(editTextRPassword.getText());

                 */

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
                        if (task.isSuccessful() && password.equals(repassword)){
                            Boolean checkuser = DB.checkusername(email);
                            if(checkuser==false) {
                                Boolean insert = DB.insertData(email, password);
                                if (insert == true) {
                                    Toast.makeText(registrarse.this, "Registro con exito", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), IniciarSesion.class);
                                    startActivity(intent);

                                }
                            }

                            /*
                            Toast.makeText(registrarse.this, "Registro realizado con exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registrarse.this, IniciarSesion.class);
                            startActivity(intent);
                            finish();

                             */
                        }else {
                            if(password.equals(repassword)){
                                Boolean checkuser = DB.checkusername(email);
                                if(checkuser==false){
                                    Boolean insert = DB.insertData(email, password);
                                    if(insert==true){
                                        Toast.makeText(registrarse.this, "Registro con exito sin conexion", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),IniciarSesion.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(registrarse.this, "Fallo al registrar", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }

                        }
                    }
                });

            }
        });
    }
}