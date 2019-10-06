/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.Celular;
import DataAdapters.tblCelular;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author patyy
 */
public class SceneModificarCelularController implements Initializable {
    
    private Stage primaryStage;
    tblCelular d = new tblCelular();
    TableView<Celular> tableViewCelulares;
    ObservableList<Celular> data;
    Celular celular = new Celular();
    tblCelular daoCelular = new tblCelular();
    private int id = 0;
    /**
     * Initializes the controller class.
     */

   
    private SceneListaCelularesController sceneListaCelularesController;

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
          
           sceneListaCelularesController.llenarTabla();
            closeStage(event);

        }

    }
    
    @FXML
    private void seleccionarImagen(ActionEvent event){
        System.out.println("SELLECCIONAR IMAGEN");
        showSingleFileChooser();
    }
    
      private void showSingleFileChooser(){
       FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg");
       
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if(selectedFile != null){
            System.out.println("" +  selectedFile.getName());
            textFieldImagen.setText(selectedFile.toString());
        }else{
            System.out.println("seleccion cancelalada");
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
            try {
                 celular.setCantidad(Integer.parseInt(textFieldCantidad.getText()));
            } catch (Exception e) {
                 labelErrorCantidad.setText("El numero mayor permitido es 2147483647.");
                 resultado = false;
            }
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

    //recibe el indice de la escena scenePrincipal
    public void idCelular(int id) {
        this.id = id;
        Celular  celular = d.getCelular(id);
        textFieldNombre.setText(celular.getNombre());
        textFieldMarca.setText(celular.getMarca());
        textFieldDescripcion.setText(celular.getDescripcion());
        textFieldColor.setText(celular.getColor());
        textFieldImagen.setText(celular.getImagen());
        textFieldCantidad.setText(""+celular.getCantidad());
        textFieldPrecio.setText(String.format("%.2f", celular.getPrecio()));

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

    void setAppMainObservableList(TableView<Celular> tableViewCelulares) {
        this.tableViewCelulares = tableViewCelulares;
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    

    void setController(SceneListaCelularesController sceneListaCelularesController) {
       this.sceneListaCelularesController = sceneListaCelularesController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limpiarMensajesDeerror(); 
        
    }

}
