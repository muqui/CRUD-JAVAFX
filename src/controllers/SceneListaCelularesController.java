/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.Celular;
import DataAdapters.tblCelular;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mq12
 */
public class SceneListaCelularesController implements Initializable {
    private Stage primaryStage;
    tblCelular daoCelular = new tblCelular();
    tblCelular d = new tblCelular();
    ObservableList<Celular> data;
    @FXML
    private TableView<Celular> tableViewCelulares;

    private void eventoTabla(ActionEvent event) {
        data.forEach((tab) -> {
            if (event.getSource() == tab.getBotonDetalles()) {
                System.out.println("ID = " + tab.getID());
            }
             if (event.getSource() == tab.getBotonBorrar()) {
                System.out.println("borrar ID = " + tab.getID());
                
                 if (JOptionPane.showConfirmDialog(null,
                    "¿Desea borrar " + tab.getID() + "?",
                    "A T E N C I Ó N",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                     daoCelular.borrarCelular(tab.getID());
                llenarTabla();
                 }
                else{
                     
                 }
                
                
                
            }
               if (event.getSource() == tab.getBotonActualizar()) {
                try {
                    System.out.println("Editar ID = " + tab.getID());
                    FXMLLoader f1 = new FXMLLoader();
                    f1.setLocation(getClass().getResource("/Resources/views/sceneModificarCelular.fxml"));
                    f1.load();
                    Parent root = f1.getRoot();
                    //obtenemos el controlador SceneModificarCelularController
                    SceneModificarCelularController sceneModificarCelularController = f1.getController();
                    //pasamos un parametro sceneModificarCelularController
                    sceneModificarCelularController.idCelular(tab.getID());
                    Stage modal_dialog = new Stage(StageStyle.DECORATED);
                    modal_dialog.initModality(Modality.APPLICATION_MODAL);
                    modal_dialog.initOwner(primaryStage);
                    Scene scene = new Scene(root);
                    modal_dialog.setScene(scene);
                    modal_dialog.show();



                } catch (IOException ex) {
                    Logger.getLogger(SceneListaCelularesController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

       
        llenarTabla();
        // tableViewCelulares.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
    }

    public void llenarTabla() {
        data = d.getInitialTableData();
        data.forEach((tab) -> {
            tab.getBotonDetalles().setOnAction(this::eventoTabla);
            tab.getBotonBorrar().setOnAction(this::eventoTabla);
             tab.getBotonActualizar().setOnAction(this::eventoTabla);
        });
         System.out.println("Llenar tabla");
        TableColumn tableColumnID = new TableColumn("ID");
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Celular, String>("ID"));

        TableColumn tableColumnNombre = new TableColumn("nombre");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Celular, String>("nombre"));

        TableColumn tableColumnmarca = new TableColumn("marca");
        tableColumnmarca.setCellValueFactory(new PropertyValueFactory<Celular, String>("marca"));

        TableColumn tableColumCantidad = new TableColumn("cantidad");
        tableColumCantidad.setCellValueFactory(new PropertyValueFactory<Celular, String>("cantidad"));

        TableColumn tableColumDetalles = new TableColumn("Detalles");
        tableColumDetalles.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonDetalles"));

        TableColumn tableColumActualizar = new TableColumn("Actualizar");
        tableColumActualizar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonActualizar"));

        TableColumn tableColumBorrar = new TableColumn("Borrar");
        tableColumBorrar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonBorrar"));

        tableViewCelulares.setItems(data);
        tableViewCelulares.getColumns().setAll(tableColumnID, tableColumnNombre, tableColumnmarca, tableColumCantidad, tableColumDetalles, tableColumActualizar, tableColumBorrar);

    }

}
