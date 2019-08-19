/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author mq12
 */
public class ScenePrincipalController implements Initializable {

    @FXML
    TabPane tabPanePrincipal;

    @FXML
    private SceneListaCelularesController  sceneListaCelularesController; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tabPanePrincipal.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
           

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                
                 sceneListaCelularesController.llenarTabla();
            
            }
        });

    }

}
