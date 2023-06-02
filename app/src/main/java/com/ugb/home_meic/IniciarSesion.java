package com.ugb.home_meic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IniciarSesion extends AppCompatActivity {


    ImageView img;
    EditText TextEmail, TextPassword;
    Button Isesion;


    TextView registrarse;

    DBHelper DB;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_iniciar_sesion);

        TextEmail = (EditText) findViewById(R.id.lo_correo);
        TextPassword  = (EditText) findViewById(R.id.lo_contrasena);
        Isesion = findViewById(R.id.lo_continuar);
        registrarse = findViewById(R.id.lo_registrarse);
        img = findViewById(R.id.ini_logo_imagen);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(IniciarSesion.this, acceso_admins.class);
                startActivity(intentImg);
                finish();
            }
        });


        DB = new DBHelper(this);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesion.this, registrarse.class);
                startActivity(intent);
                finish();
            }
        });

        Isesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = TextEmail.getText().toString();
                String password = TextPassword.getText().toString();



                if (TextUtils.isEmpty(email)){
                    Toast.makeText(IniciarSesion.this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(IniciarSesion.this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    Boolean checkuserpass = DB.checkusernamepassword(email, password);
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()){ //&& checkuserpass==true){
                            Toast.makeText(IniciarSesion.this, "Inicio de sesion con exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(IniciarSesion.this, Menu_Cliente.class);
                            startActivity(intent);
                            finish();
                        }else {
                            if(checkuserpass==true){
                                Toast.makeText(IniciarSesion.this, "Inicio de sesion sin conexion", Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(getApplicationContext(), Menu_Cliente.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(IniciarSesion.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
                            }
                        }


                        }
                });
            }
        });

    }
}