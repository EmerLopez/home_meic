package com.ugb.home_meic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.ugb.home_meic.databinding.ActivityMenuClienteBinding;

public class Menu_Cliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.abrir_navegador,
                R.string.cerrar_navegador);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Inicio()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_inicio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Inicio()).commit();
                break;

            case R.id.nav_compartir:
                Intent intent1 = new Intent(Menu_Cliente.this, principalChats.class);
                startActivity(intent1);
                finish();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Compartir()).commit();
                break;

            case R.id.nav_todas_las_categorias:
                Intent intent = new Intent(Menu_Cliente.this, MainActivity.class);
                startActivity(intent);
                finish();

                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Compartir()).commit();
                break;

            case R.id.nav_sobre_nosotros:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Nosotros()).commit();
                break;

            case R.id.nav_cerrar_sesion:
                Intent intent2 = new Intent(Menu_Cliente.this, IniciarSesion.class);
                startActivity(intent2);
                finish();
                Toast.makeText(this, "Cerrar Sesion", Toast.LENGTH_SHORT).show();

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}