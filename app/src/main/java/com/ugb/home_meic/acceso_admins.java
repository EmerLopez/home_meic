package com.ugb.home_meic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ugb.home_meic.model.Persona;

public class acceso_admins extends AppCompatActivity {

    private EditText correoEditText, passwordEditText;
    private Button iniciarSesionButton;
    private TextView mensajeTextView;
    private DatabaseReference databaseReference;
    ImageView back;
    TextView titulo;

    FirebaseDatabase firebaseDatabase;

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

        correoEditText = findViewById(R.id.lo_correo);
        passwordEditText = findViewById(R.id.lo_contrasena);
        iniciarSesionButton = findViewById(R.id.lo_continuar);
        //mensajeTextView = findViewById(R.id.mensajeTextView);

       


// Obtiene una referencia a la base de datos de Firebase
        
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Administrador");
        //firebaseDatabase.getReference().child("Administrador");

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = correoEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                iniciarSesion(correo, password);
            }
        });

    }

    private void iniciarSesion(String correo, String password) {
        // Crea una consulta a la base de datos para buscar el administrador con el correo ingresado

        if (TextUtils.isEmpty(correo)){
            Toast.makeText(acceso_admins.this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(acceso_admins.this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
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
                        Intent intent = new Intent(acceso_admins.this, Menu_principal.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(acceso_admins.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        break;

                    }
                }

                if (!encontrado) {
                    // El administrador no fue encontrado o la contraseña es incorrecta
                    //mensajeTextView.setText("Correo o contraseña incorrectos");
                    Toast.makeText(acceso_admins.this, "El administrador no fue encontrado o la contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error en la consulta
                //mensajeTextView.setText("Error en la consulta");
                Toast.makeText(acceso_admins.this, "Error en la consulta", Toast.LENGTH_SHORT).show();
            }
        });
    }

}