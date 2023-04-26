package com.ugb.home_meic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.ugb.home_meic.HomeAdapter.CategoriesAdapter;
import com.ugb.home_meic.HomeAdapter.CategoriesHelperClass;
import com.ugb.home_meic.HomeAdapter.FeaturedAdapter;
import com.ugb.home_meic.HomeAdapter.FeaturedHelperClass;

import java.util.ArrayList;

public class PanelDeInicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //VARIABLES
    RecyclerView featuredRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcono;

    Button categoriasBtn, eletroBtn, mueblesBtn, audioBtn, tecnologiaBtn, btnCarrito;
    TextView holaTexto;


    //MENU
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pane_inicio);

//DESPLAZAMIENTO
        featuredRecycler = findViewById(R.id.featured_recycler);
        categoriesRecycler = findViewById(R.id.categorias_recycler);
        menuIcono = findViewById(R.id.menu_icono);


//BOTONES PARA IR A SUS PRODUCTOS
        categoriasBtn = findViewById(R.id.btn_categorias);
        eletroBtn = findViewById(R.id.btn_electrodomesticos);
        mueblesBtn = findViewById(R.id.btn_mubles);
        audioBtn = findViewById(R.id.btn_audio);
        tecnologiaBtn = findViewById(R.id.btn_tecnologia);
        btnCarrito = findViewById(R.id.btn_carrito);


//TRASLADA EL NOMBRE A OTRA PANTALLA
        holaTexto = findViewById(R.id.app_nombre);


//MENU
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.vista_navegacion);


//ANIMACIÓN
        cajonDeNavegacion();


//VISUALIZAR FUNCIONES
        featuredRecycler();
        categoriesRecycler();

        categoriasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanelDeInicio.this, MainActivity.class);
                Pair[] pairs = new Pair[1];


                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PanelDeInicio.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

        eletroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanelDeInicio.this, MainActivity.class);
                Pair[] pairs = new Pair[1];


                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PanelDeInicio.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

        mueblesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanelDeInicio.this, MainActivity.class);
                Pair[] pairs = new Pair[1];


                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PanelDeInicio.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

        audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanelDeInicio.this, MainActivity.class);
                Pair[] pairs = new Pair[1];


                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PanelDeInicio.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

        tecnologiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanelDeInicio.this, MainActivity.class);
                Pair[] pairs = new Pair[1];


                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PanelDeInicio.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanelDeInicio.this, MainActivity.class);
                Pair[] pairs = new Pair[1];


                pairs[0] = new Pair<View, String>(holaTexto, "logo_texto2");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PanelDeInicio.this, pairs);
                    startActivity(intent, options.toBundle());
                }



            }
        });

    }


    private void cajonDeNavegacion() {

        //Cajon de navegación
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_inicio);

        menuIcono.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        cajonDeNavegacionAnimado();

    }

    private void cajonDeNavegacionAnimado() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return true;
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.producto1, "Electrodomesticos", "Estos son los articulos electrodomesticos más vendidos."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.producto2, "Muebles", "Este es el mueble más vendidos."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.producto3, "Video", "Este es el Smart Tv más vendido."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.producto4, "Audio", "Esta es la bosina sonic más vendidos."));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }

    private void categoriesRecycler() {

        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new CategoriesHelperClass(R.drawable.fiesta, "."));
        mostViewedLocations.add(new CategoriesHelperClass(R.drawable.venta1, "."));
        mostViewedLocations.add(new CategoriesHelperClass(R.drawable.venta2, "."));
        mostViewedLocations.add(new CategoriesHelperClass(R.drawable.venta3, "."));

        adapter = new CategoriesAdapter(mostViewedLocations);
        categoriesRecycler.setAdapter(adapter);


    }




}
