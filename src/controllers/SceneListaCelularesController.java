/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.Celular;
import DataAdapters.tblCelular;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mq12
 */
public class SceneListaCelularesController implements Initializable {
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
                daoCelular.borrarCelular(tab.getID());
                llenarTabla();
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

        TableColumn tableColumBorrar = new TableColumn("Detalles");
        tableColumBorrar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonBorrar"));

        tableViewCelulares.setItems(data);
        tableViewCelulares.getColumns().setAll(tableColumnID, tableColumnNombre, tableColumnmarca, tableColumCantidad, tableColumDetalles, tableColumActualizar, tableColumBorrar);

    }

}
