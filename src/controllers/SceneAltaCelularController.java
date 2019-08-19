/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.Celular;
import Persistencia.DaoCelular;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author mq12
 */
public class SceneAltaCelularController implements Initializable {
    
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
    private Button buttonAlta;
    
    @FXML
      private void altaCelular(ActionEvent event) {
        DaoCelular daoCelular = new DaoCelular();
          Celular celular = new Celular();
          celular.setNombre(textFieldNombre.getText());
          celular.setMarca(textFieldMarca.getText());
          celular.setDescripcion(textFieldDescripcion.getText());
          celular.setColor(textFieldColor.getText());
          celular.setImagen(textFieldImagen.getText());
          celular.setCantidad(Integer.parseInt(textFieldCantidad.getText()));
          celular.setPrecio(new BigDecimal(textFieldPrecio.getText().toString()));
            System.out.println("Alta producto" +  celular.toString());
         daoCelular.altaCelular(celular);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
