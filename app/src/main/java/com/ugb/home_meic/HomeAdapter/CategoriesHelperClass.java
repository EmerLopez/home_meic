package com.ugb.home_meic.HomeAdapter;

public class CategoriesHelperClass {

    int imagen, imagenlogo;
    String titulo, producto, precio, exclusivo, nombre;

    public CategoriesHelperClass(int imagen, String titulo) {
        this.imagen = imagen;
        this.imagenlogo = imagenlogo;
        this.titulo = titulo;
        this.producto = producto;
        this.precio = precio;
        this.exclusivo = exclusivo;
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public int getImagenlogo() {
        return imagenlogo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getProducto() {
        return producto;
    }

    public String getPrecio() {
        return precio;
    }

    public String getExclusivo() {
        return exclusivo;
    }

    public String getNombre() {
        return nombre;
    }

}
