package com.danielalonso.ejercicio02.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.danielalonso.ejercicio02.Favoritas;
import com.danielalonso.ejercicio02.MainActivity;
import com.danielalonso.ejercicio02.dbCancion.BaseDatosCancion;
import com.danielalonso.ejercicio02.pojo.Cancion;
import com.danielalonso.ejercicio02.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CancionAdaptador extends RecyclerView.Adapter<CancionAdaptador.CancionViewHolder> {
    private ArrayList<Cancion> canciones;
    private Activity activity;
    private BaseDatosCancion dbCancion;

    //Constructor
    public CancionAdaptador(ArrayList<Cancion> canciones, Activity activity){
        this.canciones = canciones;
        this.activity = activity;
    }

    //Inflar el layout y lo pasara al viewHolder para obtener los views
    @NonNull
    @Override
    public CancionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cancion, parent, false);
        return new CancionViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(@NonNull final CancionViewHolder cancionViewHolder, final int position) {
        final Cancion cancion = canciones.get(position);

        String genero = cancion.getGenero();
        String calificacion = Float.toString(cancion.getCalificacion());
        final String id = Integer.toString(cancion.getId_cancion());

        String totalCalif = cancionViewHolder.setCalificacion(calificacion);
        int imgGenero = cancionViewHolder.setImagen(genero);

        final String texto_eliminar = cancionViewHolder.getTextoEliminar();

        cancionViewHolder.imgGenero.setImageResource(imgGenero);
        cancionViewHolder.tvTitutlo.setText(cancion.getTitulo());
        cancionViewHolder.tvArtista.setText(cancion.getArtista());
        cancionViewHolder.tvGenero.setText(cancion.getGenero());
        cancionViewHolder.tvAnio.setText(Integer.toString(cancion.getAnio()));
        cancionViewHolder.tvCalificacion.setText(totalCalif);

        final String textoToast = cancionViewHolder.setToast(id);

        // Mostrar mensaje cuando se seleccione una cardview
        cancionViewHolder.cvCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, textoToast,Toast.LENGTH_SHORT).show();
            }
        });

        cancionViewHolder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, texto_eliminar+id, Toast.LENGTH_SHORT).show();
                dbCancion = new BaseDatosCancion(activity);
                canciones.remove(position);
                dbCancion.borrarCancion(id);

                //Reinicia la actividad pero sin el elemento que se eliminó
                activity.finish();
                activity.startActivity(activity.getIntent());
            }
        });
    }

    //cantidad de elementos que contiene mi lista
    @Override
    public int getItemCount() {
        return canciones.size();
    }

    public static class CancionViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView imgGenero;
        private MaterialTextView tvTitutlo;
        private MaterialTextView tvArtista;
        private MaterialTextView tvGenero;
        private MaterialTextView tvCalificacion;
        private MaterialTextView tvAnio;

        private MaterialCardView cvCancion;
        private ImageView icDelete;

        private Resources res;
        private String[] generosArray;

        // Constructor de CancionViewHolder
        public CancionViewHolder(@NonNull View itemView) {
            super(itemView);

            res = itemView.getResources();
            generosArray = res.getStringArray(R.array.generos);

            imgGenero = (ShapeableImageView) itemView.findViewById(R.id.imgGenero);
            tvTitutlo = (MaterialTextView) itemView.findViewById(R.id.tvTitulo);
            tvArtista = (MaterialTextView) itemView.findViewById(R.id.tvArtista);
            tvGenero = (MaterialTextView) itemView.findViewById(R.id.tvGenero);
            tvAnio = (MaterialTextView) itemView.findViewById(R.id.tvAnio);
            tvCalificacion = (MaterialTextView) itemView.findViewById(R.id.tvCalificacion);

            icDelete = (ImageView) itemView.findViewById(R.id.icDelete);

            cvCancion = (MaterialCardView) itemView.findViewById(R.id.cvCancion);
        }

        // Renderiza la imagen dependiendo del genero
        public int setImagen(String genero){
            int imagen;

            if(generosArray[1].equals(genero)){
                imagen = R.drawable.country;
            }
            else if(generosArray[2].equals(genero)){
                imagen = R.drawable.cumbia;
            }
            else if(generosArray[3].equals(genero)){
                imagen = R.drawable.electronica;
            }
            else if(generosArray[4].equals(genero)){
                imagen = R.drawable.hip_hop;
            }
            else if(generosArray[5].equals(genero)){
                imagen = R.drawable.metal;
            }
            else if(generosArray[6].equals(genero)){
                imagen = R.drawable.pop;
            }
            else if(generosArray[7].equals(genero)){
                imagen = R.drawable.rock;
            }
            else if(generosArray[8].equals(genero)){
                imagen = R.drawable.reggae;
            }
            else if(generosArray[9].equals(genero)){
                imagen = R.drawable.reggaeton;
            }
            else{
                imagen = 0;
            }
            return imagen;
        }

        // Accede a los recursos para concatenar la calificacion con el total que es 10.0
        public String setCalificacion(String calificacion){
            return calificacion + res.getString(R.string.total);
        }

        // Accede a los recursos para concatenar el Toast al seleccionar
        public String setToast(String string_ID){
            return  res.getString(R.string.seleccion) + string_ID;
        }

        public String getTextoEliminar() {
            return res.getString(R.string.eliminar);
        }
    }
}
