package com.ugb.home_meic;

public class ProjectModelClient {
    private String nombreProducto, descripcion, precio, marca, disponibilidad, especificaciones;
    private String imagenPructo;

    public ProjectModelClient() {
    }

    public ProjectModelClient(String nombreProducto, String descripcion, String precio, String marca, String disponibilidad, String especificaciones, String imagenProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
        this.disponibilidad = disponibilidad;
        this.especificaciones = especificaciones;
        this.imagenPructo = imagenProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getImagenPructo() {
        return imagenPructo;
    }

    public void setImagenPructo(String imagenPructo) {
        this.imagenPructo = imagenPructo;
    }
}
