package com.ugb.home_meic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    BD db_agenda;
    String accion="nuevo";
    String id="";
    String rev="";
    Button btn;
    TextView temp;
    FloatingActionButton fab;
    ImageView img;
    String urlCompletaImg="";
    Intent tomarFotoIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnGuardar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar_agenda();
            }
        });
        fab = findViewById(R.id.fabRegresarListaAmigos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarListaAmigos();
            }
        });
        img = findViewById(R.id.imgAmigo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFotoAmigo();
            }
        });
        mostrar_datos_amigos();
    }
    void mostrar_datos_amigos(){
        try {
            Bundle parametros = getIntent().getExtras();
            accion = parametros.getString("accion");
            if (accion.equals("modificar")) {
                String amigos[] = parametros.getStringArray("amigos");
                id = amigos[0];
                rev = amigos[1];

                temp = findViewById(R.id.txtnombre);
                temp.setText(amigos[2]);

                temp = findViewById(R.id.txtdireccion);
                temp.setText(amigos[3]);

                temp = findViewById(R.id.txtTelefono);
                temp.setText(amigos[4]);

                temp = findViewById(R.id.txtemail);
                temp.setText(amigos[5]);

                urlCompletaImg = amigos[6];
                Bitmap bitmap = BitmapFactory.decodeFile(urlCompletaImg);
                img.setImageBitmap(bitmap);
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error al mostrar los datos: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    void guardar_agenda(){
        try {
            temp = (TextView) findViewById(R.id.txtnombre);
            String nombre = temp.getText().toString();

            temp = (TextView) findViewById(R.id.txtdireccion);
            String direccion = temp.getText().toString();

            temp = (TextView) findViewById(R.id.txtTelefono);
            String telefono = temp.getText().toString();

            temp = (TextView) findViewById(R.id.txtemail);
            String email = temp.getText().toString();

            //guadar datos en server
            JSONObject datosAmigos = new JSONObject();
            if (accion.equals("modificar")){
                datosAmigos.put("_id",id);
                datosAmigos.put("_rev",id);
            }
            datosAmigos.put("nombre",nombre);
            datosAmigos.put("direccion",direccion);
            datosAmigos.put("telefono",telefono);
            datosAmigos.put("email",email);
            datosAmigos.put("urlFoto",urlCompletaImg);

            enviarDatosServidor objGuardarDatosServidor= new enviarDatosServidor(getApplicationContext());
            String msg= objGuardarDatosServidor.execute(datosAmigos.toString()).get();

            db_agenda = new BD(MainActivity.this, "",null,1);
            String result = db_agenda.administrar_agenda(id, nombre, direccion, telefono, email, urlCompletaImg, accion);
            msg = result;
            if( result.equals("ok") ){
                msg = "Registro guardado con exito";
                regresarListaAmigos();
            }


            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, "Error en guardar agenda: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    void regresarListaAmigos(){
        Intent iListaAmigos = new Intent(MainActivity.this, lista_amigos.class);
        startActivity(iListaAmigos);
    }
    private void tomarFotoAmigo(){
        tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if( tomarFotoIntent.resolveActivity(getPackageManager())!=null ){
            File fotoAmigo = null;
            try{
                fotoAmigo = crearImagenAmigo();
                if( fotoAmigo!=null ){
                    Uri uriFotoAmigo = FileProvider.getUriForFile(MainActivity.this,
                            "com.ugb.miapp.fileprovider", fotoAmigo);
                    tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriFotoAmigo);
                    startActivityForResult(tomarFotoIntent, 1);
                }
            }catch (Exception ex){
                Toast.makeText(this, "Error al tomar la foto: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "NO se selecciono una foto... ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if( requestCode==1 && resultCode==RESULT_OK ){
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaImg);
                img.setImageBitmap(imagenBitmap);
            }else{
                Toast.makeText(this, "Se cancelo la seleccion de la foto", Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error al mostrar la camara: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private File crearImagenAmigo() throws Exception{
        String fechaHoraMs = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "imagen_"+ fechaHoraMs +"_";
        File dirAlmacenamiento = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if(dirAlmacenamiento.exists()==false ){
            dirAlmacenamiento.mkdirs();
        }
        File image = File.createTempFile(fileName, ".jpg", dirAlmacenamiento);
        urlCompletaImg = image.getAbsolutePath();
        return image;
    }
}