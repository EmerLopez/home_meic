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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ugb.home_meic.model.Persona;

public class IniciarSesion extends AppCompatActivity {


    ImageView img;
    EditText TextEmail, TextPassword;
    Button Isesion;

    private DatabaseReference databaseReference;
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
    registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesion.this, registrarse.class);
                startActivity(intent);
                finish();
            }
        });



        DB = new DBHelper(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Persona");

        Isesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correosq = TextEmail.getText().toString().trim();
                String passwordsq = TextPassword.getText().toString().trim();

                String correo = TextEmail.getText().toString();
                String password = TextPassword.getText().toString();

                // Crea una consulta a la base de datos para buscar el administrador con el correo ingresado

                if (TextUtils.isEmpty(correo)){
                    Toast.makeText(IniciarSesion.this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(IniciarSesion.this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkuserpass = DB.checkusernamepassword(correosq, passwordsq);

                Query query = databaseReference.orderByChild("correo").equalTo(correo);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean encontrado = false;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Persona persona = snapshot.getValue(Persona.class);
                            if (persona != null && persona.getPassword().equals(password)) {
                                // Inicio de sesión exitoso
                                encontrado = true;
                                Intent intent = new Intent(IniciarSesion.this, Menu_Cliente.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(IniciarSesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                                break;

                            }else {
                                if(checkuserpass==true){
                                    Toast.makeText(IniciarSesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent  = new Intent(getApplicationContext(), Menu_Cliente.class);
                                    startActivity(intent);
                                }
                            }

                        }

                        if (!encontrado) {

                            Toast.makeText(IniciarSesion.this, "Credenciale invalidas, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        //Toast.makeText(IniciarSesion.this, "Error en la consulta", Toast.LENGTH_SHORT).show();
                    }
                });



            }

        });

    }

    private void iniciarSesion(String email, String password) {


    }
}