/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.beans.PropertyChangeSupport;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author mq12
 */
public class Celular {

  

    private IntegerProperty ID= new SimpleIntegerProperty();
    private   StringProperty  nombre =  new SimpleStringProperty();
    private StringProperty  marca=  new SimpleStringProperty();
    private StringProperty  descripcion=  new SimpleStringProperty();
    private StringProperty  color=  new SimpleStringProperty();
    private StringProperty  imagen=  new SimpleStringProperty();
    private IntegerProperty  cantidad=  new SimpleIntegerProperty();
    private DoubleProperty  precio=  new SimpleDoubleProperty();

    public Celular() {
      
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
       
        this.nombre.set(nombre);
        System.out.println("NOmnre xxx" + this.nombre);
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getImagen() {
        return imagen.get();
    }

    public void setImagen(String imagen) {
        this.imagen.set(imagen);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public Button getBotonDetalles() {
        return botonDetalles;
    }

    public void setBotonDetalles(Button botonDetalles) {
        this.botonDetalles = botonDetalles;
    }

    public Button getBotonActualizar() {
        return botonActualizar;
    }

    public void setBotonActualizar(Button botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public Button getBotonBorrar() {
        return botonBorrar;
    }

    public void setBotonBorrar(Button botonBorrar) {
        this.botonBorrar = botonBorrar;
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }
    private Button botonDetalles;
    private Button botonActualizar;
    private Button botonBorrar;
    private PropertyChangeSupport propertySupport;
    
    public Celular(int id, String nombre, String marca, String descripcion, String color, String imagen, int cantidad, double precio, Button botonDetalles, Button botonActualizar , Button botonBorrar) {
        // this.nombre = new SimpleStringProperty(nombre);
        this.ID = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.marca =new SimpleStringProperty(marca);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.color = new SimpleStringProperty(color);
        this.imagen = new SimpleStringProperty(imagen);
        this.cantidad =  new SimpleIntegerProperty(cantidad);
        this.precio = new SimpleDoubleProperty(precio);
        this.botonDetalles = botonDetalles;
        this.botonActualizar = botonActualizar;
        this.botonBorrar = botonBorrar;
        this.botonDetalles = new Button("Detalles");
        this.botonActualizar = new Button("Actualizar");
        this.botonBorrar = new Button("Borrar");
    }



}
