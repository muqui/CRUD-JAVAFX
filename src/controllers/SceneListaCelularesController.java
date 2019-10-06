/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.Celular;
import DataAdapters.tblCelular;
import ValidateCustom.AbstractConvertCellFactory;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private TextField textFieldBuscar;

    @FXML
    private Button buttonBuscar;
    
        @FXML
    void mensaje(MouseEvent event) {
            System.out.println("mensaje");
    }

    @FXML
    void buttonBuscar(ActionEvent event) {
        System.out.println("buscar .....................");
        llenarTablaBuscar(textFieldBuscar.getText());
    }

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
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    daoCelular.borrarCelular(tab.getID());
                    llenarTabla();
                } else {

                }

            }
            if (event.getSource() == tab.getBotonActualizar()) {
                try {
                    System.out.println("Editar ID = " + tab.getID());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Resources/views/sceneModificarCelular.fxml"));
                    fxmlLoader.load();

                    Parent root = fxmlLoader.getRoot();

                    //obtenemos el controlador SceneModificarCelularController
                    SceneModificarCelularController sceneModificarCelularController = fxmlLoader.<SceneModificarCelularController>getController();
                    //pasamos un parametro sceneModificarCelularController
                    sceneModificarCelularController.idCelular(tab.getID());
                    sceneModificarCelularController.setAppMainObservableList(tableViewCelulares);
                    sceneModificarCelularController.setController(this);
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
        tableViewCelulares.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        data = d.getInitialTableData();
        data.forEach((tab) -> {
            tab.getBotonDetalles().setOnAction(this::eventoTabla);
            tab.getBotonBorrar().setOnAction(this::eventoTabla);
            tab.getBotonActualizar().setOnAction(this::eventoTabla);
        });
        System.out.println("Llenar tabla");
        //tabla ID
        TableColumn tableColumnID = new TableColumn("ID");
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Celular, String>("ID"));
        tableColumnID.setMaxWidth(1f * Integer.MAX_VALUE * 5);

        //celda Nombre
        TableColumn tableColumnNombre = new TableColumn("nombre");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Celular, String>("nombre"));
        tableColumnNombre.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda marca
        TableColumn tableColumnmarca = new TableColumn("marca");
        tableColumnmarca.setCellValueFactory(new PropertyValueFactory<Celular, String>("marca"));
        tableColumnmarca.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda descripción
        TableColumn tableColumnDescripcion = new TableColumn("descripcion");
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<Celular, String>("descripcion"));
        tableColumnDescripcion.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        //celda Color
        TableColumn tableColumnColor = new TableColumn("color");
        tableColumnColor.setCellValueFactory(new PropertyValueFactory<Celular, String>("color"));
        tableColumnColor.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        // celda cantidad
        TableColumn tableColumCantidad = new TableColumn("cantidad");
        tableColumCantidad.setCellValueFactory(new PropertyValueFactory<Celular, String>("cantidad"));
        tableColumCantidad.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda Precio
        TableColumn tableColumPrecio = new TableColumn("precio");
        // tableColumPrecio.setCellValueFactory(new PropertyValueFactory<Celular, Double>("precio"));
        tableColumPrecio.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Celular, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Celular, String> celular) {
                SimpleStringProperty property = new SimpleStringProperty();
               
                property.setValue(String.format("%.2f", celular.getValue().getPrecio()));
                return property;
            }
        });

        tableColumPrecio.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda botón detalles
        //TableColumn tableColumDetalles = new TableColumn("Detalles");
        //tableColumDetalles.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonDetalles"));
        //celda botón actualizar
        TableColumn tableColumActualizar = new TableColumn("Actualizar");
        tableColumActualizar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonActualizar"));
        tableColumActualizar.setMaxWidth(1f * Integer.MAX_VALUE * 6);
        // celda botón borrar
        TableColumn tableColumBorrar = new TableColumn("Borrar");
        tableColumBorrar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonBorrar"));
        tableColumBorrar.setMaxWidth(1f * Integer.MAX_VALUE * 4);
        // celda de la imagen
        TableColumn tableColumImagen = new TableColumn("Imagen");
        tableColumImagen.setCellValueFactory(new PropertyValueFactory<Celular, String>("image"));
        tableColumImagen.setMaxWidth(1f * Integer.MAX_VALUE * 5);

        tableViewCelulares.setItems(data);
        tableViewCelulares.getColumns().setAll(tableColumImagen, tableColumnNombre, tableColumnmarca, tableColumnDescripcion, tableColumnColor, tableColumCantidad, tableColumPrecio, tableColumActualizar, tableColumBorrar);

    }

    public void llenarTablaBuscar(String filtro) {
        tableViewCelulares.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        data = d.getBuscarTableData(filtro);
        data.forEach((tab) -> {
            tab.getBotonDetalles().setOnAction(this::eventoTabla);
            tab.getBotonBorrar().setOnAction(this::eventoTabla);
            tab.getBotonActualizar().setOnAction(this::eventoTabla);
        });
        System.out.println("Llenar tabla");
        //tabla ID
        TableColumn tableColumnID = new TableColumn("ID");
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Celular, String>("ID"));
        tableColumnID.setMaxWidth(1f * Integer.MAX_VALUE * 5);

        //celda Nombre
        TableColumn tableColumnNombre = new TableColumn("nombre");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Celular, String>("nombre"));
        tableColumnNombre.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda marca
        TableColumn tableColumnmarca = new TableColumn("marca");
        tableColumnmarca.setCellValueFactory(new PropertyValueFactory<Celular, String>("marca"));
        tableColumnmarca.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda descripción
        TableColumn tableColumnDescripcion = new TableColumn("descripcion");
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<Celular, String>("descripcion"));
        tableColumnDescripcion.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        //celda Color
        TableColumn tableColumnColor = new TableColumn("color");
        tableColumnColor.setCellValueFactory(new PropertyValueFactory<Celular, String>("color"));
        tableColumnColor.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        // celda cantidad
        TableColumn tableColumCantidad = new TableColumn("cantidad");
        tableColumCantidad.setCellValueFactory(new PropertyValueFactory<Celular, String>("cantidad"));
        tableColumCantidad.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda Precio
        TableColumn tableColumPrecio = new TableColumn("precio");
       // tableColumPrecio.setCellValueFactory(new PropertyValueFactory<Celular, String>("precio"));
        tableColumPrecio.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Celular, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Celular, String> celular) {
                SimpleStringProperty property = new SimpleStringProperty();
               
                property.setValue(String.format("%.2f", celular.getValue().getPrecio()));
                return property;
            }
        });
        tableColumPrecio.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        //celda botón detalles
        //TableColumn tableColumDetalles = new TableColumn("Detalles");
        //tableColumDetalles.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonDetalles"));
        //celda botón actualizar
        TableColumn tableColumActualizar = new TableColumn("Actualizar");
        tableColumActualizar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonActualizar"));
        tableColumActualizar.setMaxWidth(1f * Integer.MAX_VALUE * 6);
        // celda botón borrar
        TableColumn tableColumBorrar = new TableColumn("Borrar");
        tableColumBorrar.setCellValueFactory(new PropertyValueFactory<Celular, String>("botonBorrar"));
        tableColumBorrar.setMaxWidth(1f * Integer.MAX_VALUE * 4);
        // celda de la imagen
        TableColumn tableColumImagen = new TableColumn("Imagen");
        tableColumImagen.setCellValueFactory(new PropertyValueFactory<Celular, String>("image"));
        tableColumImagen.setMaxWidth(1f * Integer.MAX_VALUE * 5);

        tableViewCelulares.setItems(data);
        tableViewCelulares.getColumns().setAll(tableColumImagen, tableColumnNombre, tableColumnmarca, tableColumnDescripcion, tableColumnColor, tableColumCantidad, tableColumPrecio, tableColumActualizar, tableColumBorrar);

    }

}
