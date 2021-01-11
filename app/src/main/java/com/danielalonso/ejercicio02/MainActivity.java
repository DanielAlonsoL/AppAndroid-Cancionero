package com.danielalonso.ejercicio02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.danielalonso.ejercicio02.dbCancion.BaseDatosCancion;
import com.danielalonso.ejercicio02.dbCancion.ConstructorCancion;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etTitulo;
    private TextInputEditText etArtista;
    private TextInputEditText etAnio;
    private RatingBar rbCalificacion;
    private MaterialButton btnGuardar;

    private MaterialToolbar toolbar;
    private TabLayout tabLayout;

    private Resources res;
    private MaterialAutoCompleteTextView ac_generos;
    private ArrayAdapter<String> adapter;
    private String[] generos;
    final String[] item = {""};

    private ConstructorCancion constructorCancion;
    private BaseDatosCancion dbCancion;

    private String titulo;
    private String artista;
    private String genero;
    private String anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = (MaterialButton) findViewById(R.id.btnGuardar);
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);

        //Agregando toolbar a la pantalla principal
        setSupportActionBar(toolbar);

        ac_generos = (MaterialAutoCompleteTextView) findViewById(R.id.spGeneros);

        res = getResources();
        generos = res.getStringArray(R.array.generos);

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ac_generos.setAdapter(adapter);

        ac_generos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item[0] = generos[i];
            }
        });

        guardar(btnGuardar);
    }

    public void limpiar() {
        etTitulo.setText("");
        etArtista.setText("");
        ac_generos.setText("");
        etAnio.setText("");
    }

    public void guardar(MaterialButton btnGuardar){
        etTitulo = (TextInputEditText) findViewById(R.id.etTitulo);
        etArtista = (TextInputEditText) findViewById(R.id.etArtista);
        ac_generos = (MaterialAutoCompleteTextView) findViewById(R.id.spGeneros);
        etAnio = (TextInputEditText) findViewById(R.id.etAnio);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaCampos()){
                    Toast.makeText(MainActivity.this, getString(R.string.saved),Toast.LENGTH_LONG).show();

                    rbCalificacion = (RatingBar) findViewById(R.id.rbCalificacion);

                    dbCancion = new BaseDatosCancion(MainActivity.this);
                    dbCancion.insertarCancion(titulo, artista, genero, Integer.parseInt(anio), rbCalificacion.getRating());
                    limpiar();
                }
            }
        });
    }

    public boolean validaCampos() {

        titulo = etTitulo.getText().toString();
        artista = etArtista.getText().toString();
        genero = item[0];
        anio = etAnio.getText().toString();

        if (titulo.equals("")) {
            etTitulo.setError(getString(R.string.error_titulo));
            etTitulo.requestFocus();
            return false;
        }

        if (artista.equals("")) {
            etArtista.setError(getString(R.string.error_artista));
            etArtista.requestFocus();
            return false;
        }

        if(genero.equals("")) {
            Toast.makeText(MainActivity.this, getString(R.string.error_genero),Toast.LENGTH_LONG).show();
            return false;
        }

        if (anio.equals("")) {
            etAnio.setError(getString(R.string.error_anio));
            etAnio.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutMe:
                // User chose the "Settings" item, show the app settings UI...
                Intent about = new Intent(this, AcercaDe.class);
                startActivity(about);
                break;

            case R.id.contact:
                Intent contacto = new Intent(this, Contacto.class);
                startActivity(contacto);
                break;

            case R.id.favorito:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent fav = new Intent(this, Favoritas.class);
                startActivity(fav);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}