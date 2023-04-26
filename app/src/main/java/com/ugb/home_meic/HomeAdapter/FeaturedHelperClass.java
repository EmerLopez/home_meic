package com.ugb.home_meic.HomeAdapter;

public class FeaturedHelperClass {

    int imagen;
    String titulo, descripcion;

    public FeaturedHelperClass(int imagen, String titulo, String descripcion) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
