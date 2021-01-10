package com.danielalonso.ejercicio02.dbCancion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.danielalonso.ejercicio02.pojo.Cancion;
import com.danielalonso.ejercicio02.db.ConstantesCancion;

import java.util.ArrayList;

public class ConstructorCancion {

    private Context context;
    private BaseDatosCancion db;

    public ConstructorCancion(Context context){
        this.context = context;
    }

    public ArrayList<Cancion> obtenerCanciones(){
        ArrayList<Cancion> canciones = new ArrayList<>();
        BaseDatosCancion db = new BaseDatosCancion(context);
        return db.obtenerTodasLasCanciones();
    }



}
