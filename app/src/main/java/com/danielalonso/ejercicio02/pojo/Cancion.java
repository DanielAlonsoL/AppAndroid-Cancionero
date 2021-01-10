package com.danielalonso.ejercicio02.pojo;

public class Cancion {
    private int id_cancion;
    private String titulo;
    private String artista;
    private String genero;
    private int imgGenero;
    private int anio;
    private float calificacion;

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public int getImgGenero() {
        return imgGenero;
    }

    public void setImgGenero(int imgGenero) {
        this.imgGenero = imgGenero;
    }

}
