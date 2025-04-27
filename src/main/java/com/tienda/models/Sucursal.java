package com.tienda.models;

public class Sucursal {
    private int id;
    private String nombre;
    private String direccion;

    public Sucursal() {}

    public Sucursal(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
}