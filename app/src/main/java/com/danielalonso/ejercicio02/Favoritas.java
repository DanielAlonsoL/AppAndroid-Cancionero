package com.danielalonso.ejercicio02;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danielalonso.ejercicio02.adapter.CancionAdaptador;
import com.danielalonso.ejercicio02.dbCancion.ConstructorCancion;
import com.danielalonso.ejercicio02.pojo.Cancion;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private ArrayList<Cancion> canciones;
    private RecyclerView listaCanciones;

    private ConstructorCancion constructorCancion;
    private CancionAdaptador cancionAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        constructorCancion = new ConstructorCancion(this);
        canciones = constructorCancion.obtenerCanciones();

        listaCanciones = findViewById(R.id.rvCanciones);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        cancionAdaptador = new CancionAdaptador(canciones, this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaCanciones.setLayoutManager(llm);

        //inicializarMascotas();
        inicializarAdaptador();

        //Agregando toolbar a la pantalla principal
        toolbar = findViewById(R.id.toolbar_fav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritas, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.aboutMe:
                // User chose the "Settings" item, show the app settings UI...
                Intent about = new Intent(this, AcercaDe.class);
                startActivity(about);
                break;

            case R.id.contact:
                Intent contacto = new Intent(this, Contacto.class);
                startActivity(contacto);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inicializarAdaptador() {
        CancionAdaptador adaptador = new CancionAdaptador(canciones, this);
        listaCanciones.setAdapter(adaptador);
    }
/*
    public void inicializarMascotas() {
        mascotas = new ArrayList<>();

        mascotas.add(new Mascota("Chikis", R.drawable.pet3, 6));
        mascotas.add(new Mascota("Bugs", R.drawable.pet7, 3));
        mascotas.add(new Mascota("Max", R.drawable.pet1, 9));
        mascotas.add(new Mascota("Felix", R.drawable.pet5, 2));
        mascotas.add(new Mascota("Mili", R.drawable.pet2, 8));

    }

 */
}