/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.Celular;
import DataAdapters.tblCelular;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author patyy
 */
public class SceneModificarCelularController implements Initializable {
    Celular celular = new Celular();
    tblCelular daoCelular = new tblCelular();
    private int id = 0;
    /**
     * Initializes the controller class.
     */
    
     @FXML
    private SceneListaCelularesController  sceneListaCelularesController; 
     
        @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldMarca;

    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private TextField textFieldColor;

    @FXML
    private TextField textFieldImagen;

    @FXML
    private TextField textFieldCantidad;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    private Button buttonModificar;

    @FXML
    private Label labelErrorNombre;

    @FXML
    private Label labelErrorMarca;

    @FXML
    private Label labelErrorDescripcion;

    @FXML
    private Label labelErrorColor;

    @FXML
    private Label labelErrorImagen;

    @FXML
    private Label labelErrorCantidad;

    @FXML
    private Label labelErrorPrecio;

    @FXML
    void modificarCelular(ActionEvent event) {
                limpiarMensajesDeerror();
        if (validarCampos()) {
            celular.setID(id);
            daoCelular.ModificarCelular(celular);
            limpiarCampos();
    //         sceneListaCelularesController.llenarTabla();
    
        }

    }
     /**
     * Limpia los campos del formulario de alta
     */
    public void limpiarCampos() {
        textFieldNombre.setText("");
        textFieldMarca.setText("");
        textFieldDescripcion.setText("");
        textFieldColor.setText("");
        textFieldImagen.setText("");
        textFieldCantidad.setText("");
        textFieldPrecio.setText("");
//         sceneListaCelularesController.llenarTabla();
    }

    /**
     * valida los campos del formulario
     */
    public boolean validarCampos() {
        boolean resultado = true;
        //valida campo Nombre
        if (textFieldNombre.getText().trim().equals("")) {
            labelErrorNombre.setText("Nombre es requerido");
            resultado = false;
        } else {
            celular.setNombre(textFieldNombre.getText());
        }
        //valida campo marca
        if (textFieldMarca.getText().trim().equals("")) {
            labelErrorMarca.setText("Marca es requerido");
            resultado = false;
        } else {
            celular.setMarca(textFieldMarca.getText());
        }
        //Valida campo descripcion
        if (textFieldDescripcion.getText().trim().equals("")) {
            labelErrorDescripcion.setText("Descripción es requerida");
            resultado = false;
        } else {
            celular.setDescripcion(textFieldDescripcion.getText());
        }
        //valida campo color
        if (textFieldColor.getText().trim().equals("")) {
            labelErrorColor.setText("Color es requerido.");
            resultado = false;
        } else {
            celular.setColor(textFieldColor.getText());
        }
        //valida campo imagen
        if (textFieldImagen.getText().trim().equals("")) {
            labelErrorImagen.setText("Imagen  es requerida.");
            resultado = false;
        } else {
            celular.setImagen(textFieldImagen.getText());
        }
        //valida campo cantidad  se ayuda con la clase IntegerNumberTextField para permitir solamente numeros enteros
        if (textFieldCantidad.getText().trim().equals("")) {
            labelErrorCantidad.setText("Cantidad  es requerida.");
            resultado = false;
        } else {
            celular.setCantidad(Integer.parseInt(textFieldCantidad.getText()));
        }
        // valida campo precio se ayuda con la clase FloatNumberTextField para permitir solo numeros flotantes
        if (textFieldPrecio.getText().trim().equals("")) {
            labelErrorPrecio.setText("Precio  es requerido.");
            resultado = false;
        } else {
           // celular.setPrecio(new BigDecimal(textFieldPrecio.getText().toString()));
             celular.setPrecio(Double.parseDouble(textFieldPrecio.getText()));
        }

        return resultado;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      //recibe el indice de la escena scenePrincipal
    public void idCelular(int id) {
       this.id = id;
       
    }
    
     /**
     * Limpia los mensajes de error;
     */
    public void limpiarMensajesDeerror() {
        labelErrorNombre.setText("");

        labelErrorMarca.setText("");

        labelErrorDescripcion.setText("");

        labelErrorColor.setText("");

        labelErrorImagen.setText("");

        labelErrorCantidad.setText("");

        labelErrorPrecio.setText("");

    }

   
    
}
