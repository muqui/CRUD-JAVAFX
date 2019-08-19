/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import javafx.scene.control.Button;

/**
 *
 * @author mq12
 */
public class Celular {

    @Override
    public String toString() {
        return "Celular{" + "ID=" + ID + ", nombre=" + nombre + ", marca=" + marca + ", descripcion=" + descripcion + ", color=" + color + ", imagen=" + imagen + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }

    public Celular() {
         propertySupport = new PropertyChangeSupport(this);
    }

    /**
     * @return the botonActualizar
     */
    public Button getBotonActualizar() {
        return botonActualizar;
    }

    /**
     * @param botonActualizar the botonActualizar to set
     */
    public void setBotonActualizar(Button botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    /**
     * @return the botonBorrar
     */
    public Button getBotonBorrar() {
        return botonBorrar;
    }

    /**
     * @param botonBorrar the botonBorrar to set
     */
    public void setBotonBorrar(Button botonBorrar) {
        this.botonBorrar = botonBorrar;
    }

    private int ID;
    private String nombre;
    private String marca;
    private String descripcion;
    private String color;
    private String imagen;
    private int cantidad;
    private BigDecimal precio;
    private Button botonDetalles;
    private Button botonActualizar;
    private Button botonBorrar;
    private PropertyChangeSupport propertySupport;
    
  


    public Celular(String nombre, String marca, String descripcion, String color, String imagen, int cantidad, BigDecimal precio) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.imagen = imagen;
        this.cantidad = cantidad;
        this.precio = precio;
        this.botonDetalles = new Button("Detalles");
        this.botonActualizar = new Button("Actualizar");
        this.botonBorrar = new Button("Borrar");

    }

    public Celular(int id, String nombre, String marca, String descripcion, String color, String imagen, int cantidad, BigDecimal precio, Button botonDetalles, Button botonActualizar , Button botonBorrar) {
        this.ID = id;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.imagen = imagen;
        this.cantidad = cantidad;
        this.precio = precio;
        this.botonDetalles = botonDetalles;
        this.botonActualizar = botonActualizar;
        this.botonBorrar = botonBorrar;
        this.botonDetalles = new Button("Detalles");
        this.botonActualizar = new Button("Actualizar");
        this.botonBorrar = new Button("Borrar");
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * @return the botonDetalles
     */
    public Button getBotonDetalles() {
        return botonDetalles;
    }

    /**
     * @param botonDetalles the botonDetalles to set
     */
    public void setBotonDetalles(Button botonDetalles) {
        this.botonDetalles = botonDetalles;
    }

}
