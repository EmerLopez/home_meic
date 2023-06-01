package com.ugb.home_meic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class UploadProductosActivity extends AppCompatActivity {

    TextView nombreProducto, descripcion, precio, marca, disponibilidad, especificaciones;
    ImageView subirBtn, imagenPructo;
    Button enviar;
    ImageView back;
    TextView titulo;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    ProgressDialog dialog;

    Uri ImagenUri;

    RelativeLayout relativeLayout;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_productos);

        nombreProducto = findViewById(R.id.nombreProducto);
        descripcion = findViewById(R.id.descripcion);
        precio = findViewById(R.id.precio);
        marca = findViewById(R.id.marca);
        disponibilidad = findViewById(R.id.disponibilidad);
        especificaciones = findViewById(R.id.especificaciones);

        subirBtn = findViewById(R.id.subirBtn);
        imagenPructo = findViewById(R.id.imagenPructo);

        enviar = findViewById(R.id.enviar);

        relativeLayout = findViewById(R.id.relative);

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Por favor espera...");
        dialog.setCancelable(false);
        dialog.setTitle("Cargando producto");
        dialog.setCanceledOnTouchOutside(false);

        back = findViewById(R.id.btn_back);
        titulo = findViewById(R.id.tv_title_toolbar);

        titulo.setText("Agregar Producto");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadProductosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        subirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubirImagen();
                relativeLayout.setVisibility(View.VISIBLE);
                subirBtn.setVisibility(View.GONE);
            }
        });

        //Y SI COPIO ESTE CODIGO Y LO IMPLEMENTO EN UN BOTON?

       enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                final StorageReference reference = firebaseStorage.getReference().child("productos")
                        .child(System.currentTimeMillis()+ "");

                reference.putFile(ImagenUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                ProjectModel model = new ProjectModel();
                                model.setImagenPructo(uri.toString());

                                model.setNombreProducto(nombreProducto.getText().toString());
                                model.setDescripcion(descripcion.getText().toString());
                                model.setPrecio(precio.getText().toString());
                                model.setMarca(marca.getText().toString());
                                model.setDisponibilidad(disponibilidad.getText().toString());
                                model.setEspecificaciones(especificaciones.getText().toString());

                                database.getReference().child("productos").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(UploadProductosActivity.this, "Producto enviado", Toast.LENGTH_SHORT).show();

                                                dialog.dismiss();
                                                Intent intent = new Intent(UploadProductosActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                dialog.dismiss();

                                                Toast.makeText(UploadProductosActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });

            } //FINAL DEL CODIGO A COPIAR EN EL CASO DE QUE FUNCIONE EN OTRO BOTON
        });


    }

    private void SubirImagen() {

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent,101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(UploadProductosActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK){

            ImagenUri = data.getData();
            imagenPructo.setImageURI(ImagenUri);

        }
    }
}