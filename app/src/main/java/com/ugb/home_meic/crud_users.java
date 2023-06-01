package com.ugb.home_meic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ugb.home_meic.model.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class crud_users extends AppCompatActivity {
    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;
    TextView titulo;

    ImageView back;
    EditText nomP, appP,correoP,passwordP;
    ListView listV_personas;

    Button agregar, guardar, eliminar;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    boolean validar = false;

    Persona personaSelected;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_users);

        nomP = findViewById(R.id.txt_nombrePersona);
        appP = findViewById(R.id.txt_appPersona);
        correoP = findViewById(R.id.txt_correoPersona);
        passwordP = findViewById(R.id.txt_passwordPersona);
        agregar = findViewById(R.id.add);
        guardar = findViewById(R.id.save);
        eliminar = findViewById(R.id.delete);
        back = findViewById(R.id.btn_back);
        titulo = findViewById(R.id.tv_title_toolbar);

        titulo.setText("Administracion de Usuarios");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(crud_users.this, Menu_Cliente.class);
                startActivity(intent);
                finish();
            }
        });

        listV_personas = findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listarDatos();



        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


               validar = true;

                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                appP.setText(personaSelected.getApellido());
                correoP.setText(personaSelected.getCorreo());
                passwordP.setText(personaSelected.getPassword());
            }
        });


        agregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombre = nomP.getText().toString();
                String correo = correoP.getText().toString();
                String password = passwordP.getText().toString();
                String app = appP.getText().toString();


                if (nombre.equals("")||correo.equals("")||password.equals("")||app.equals("")){
                    validacion();
                }
                else {
                    Persona p = new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(app);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    databaseReference.child("Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(crud_users.this, "Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                validar = false;

            }

        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nomP.getText().toString();
                String correo = correoP.getText().toString();
                String password = passwordP.getText().toString();
                String app = appP.getText().toString();

                if (validar==true){
                    Persona p = new Persona();
                    p.setUid(personaSelected.getUid());
                    p.setNombre(nomP.getText().toString().trim());
                    p.setApellido(appP.getText().toString().trim());
                    p.setCorreo(correoP.getText().toString().trim());
                    p.setPassword(passwordP.getText().toString().trim());
                    databaseReference.child("Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(crud_users.this,"Actualizado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }else {
                    Toast.makeText(crud_users.this,"Error", Toast.LENGTH_LONG).show();
                }


                validar = false;
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nomP.getText().toString();
                String correo = correoP.getText().toString();
                String password = passwordP.getText().toString();
                String app = appP.getText().toString();

                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Persona").child(p.getUid()).removeValue();
                Toast.makeText(crud_users.this,"Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();

                validar = false;
            }
        });

 }



    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(crud_users.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


    private void limpiarCajas() {
        nomP.setText("");
        correoP.setText("");
        passwordP.setText("");
        appP.setText("");
    }

    private void validacion() {
        String nombre = nomP.getText().toString();
        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();
        String app = appP.getText().toString();
        if (nombre.equals("")){
            nomP.setError("Required");
        }
        else if (app.equals("")){
            appP.setError("Required");
        }
        else if (correo.equals("")){
            correoP.setError("Required");
        }
        else if (password.equals("")){
            passwordP.setError("Required");
        }
    }
}