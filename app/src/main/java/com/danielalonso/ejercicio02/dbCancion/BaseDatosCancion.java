package com.danielalonso.ejercicio02.dbCancion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.danielalonso.ejercicio02.pojo.Cancion;
import com.danielalonso.ejercicio02.db.ConstantesCancion;

import java.util.ArrayList;

public class BaseDatosCancion extends SQLiteOpenHelper {

    private Context context;

    public BaseDatosCancion(@Nullable Context context) {
        super(context, ConstantesCancion.DATABASE_NAME, null, ConstantesCancion.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaCancion = "CREATE TABLE "+ConstantesCancion.TABLE_CANCION+"("+
                ConstantesCancion.TABLE_CANCION_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesCancion.TABLE_CANCION_TITULO   + " TEXT, "+
                ConstantesCancion.TABLE_CANCION_ARTISTA   + " TEXT, "+
                ConstantesCancion.TABLE_CANCION_GENERO   + " TEXT, "+
                ConstantesCancion.TABLE_CANCION_ANIO     + " INTEGER, "+
                ConstantesCancion.TABLE_CANCION_CALIFICACION + " REAL "+
                ")";
        sqLiteDatabase.execSQL(queryCrearTablaCancion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ConstantesCancion.TABLE_CANCION);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Cancion> obtenerTodasLasCanciones(){
        ArrayList<Cancion> canciones = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesCancion.TABLE_CANCION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Cancion cancionActual = new Cancion();
            cancionActual.setId_cancion(registros.getInt(0));
            cancionActual.setTitulo(registros.getString(1));
            cancionActual.setArtista(registros.getString(2));
            cancionActual.setGenero(registros.getString(3));
            cancionActual.setAnio(registros.getInt(4));
            cancionActual.setCalificacion(registros.getFloat(5));

            canciones.add(cancionActual);
        }

        db.close();

        return canciones;
    }

    public void insertarCancion(String titulo, String artista, String genero, int anio, float calificacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ConstantesCancion.TABLE_CANCION_ID, id);
        contentValues.put(ConstantesCancion.TABLE_CANCION_TITULO, titulo);
        contentValues.put(ConstantesCancion.TABLE_CANCION_ARTISTA, artista);
        contentValues.put(ConstantesCancion.TABLE_CANCION_GENERO, genero);
        contentValues.put(ConstantesCancion.TABLE_CANCION_ANIO, anio);
        contentValues.put(ConstantesCancion.TABLE_CANCION_CALIFICACION, calificacion);

        // Inserta en la tabla cancion
        db.insert(ConstantesCancion.TABLE_CANCION, null, contentValues);
        //Log.i("SUCCESS","La cancion "+titulo+" se ha agredado con exito, con calificacion de "+calificacion);
        db.close();
    }

    public void borrarCancion(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesCancion.TABLE_CANCION, ConstantesCancion.TABLE_CANCION_ID+"=?", new String[] {ID});
        db.close();
    }



}
