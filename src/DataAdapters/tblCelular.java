/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAdapters;

import Beans.Celular;
import Persistencia.ConnectionMYSQLManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author mq12
 */
public class tblCelular {
    
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Regresa un objeto tipo celular desde la base de datos
     *
     * @param id
     * @return
     */
    public Celular getCelular(int id) {
        Celular celular = new Celular();
        try {
            
            con = ConnectionMYSQLManager.getConnection();
            
            String query = "SELECT * FROM celulares where cel_id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                celular.setNombre(resultSet.getString(2));
                celular.setMarca(resultSet.getString(3));
                celular.setDescripcion(resultSet.getString(4));
                celular.setColor(resultSet.getString(5));
                celular.setImagen(resultSet.getString(6));
                celular.setCantidad(resultSet.getInt(7));
                celular.setPrecio(resultSet.getDouble(8));
                
                System.out.println("PRECIO " +  resultSet.getString(8).toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
        }
        return celular;
    }
    
    public ObservableList<Celular> getInitialTableData() {
        List<Celular> list = new ArrayList<>();
        con = ConnectionMYSQLManager.getConnection();
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM celulares";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("" + rs.getString(6));
                // FileInputStream fileImagen = new FileInputStream(rs.getString(6));
                File file = new File(rs.getString(6));
                Image image = new Image(file.toURI().toString());
                ImageView imagenView = new ImageView(image);
                imagenView.setFitHeight(100);
                imagenView.setFitWidth(50);
                list.add(new Celular(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), new Button(), new Button(), new Button(), imagenView));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Celular> data = FXCollections.observableList(list);
        
        return data;
    }
        public ObservableList<Celular> getBuscarTableData(String filtro) {
        List<Celular> list = new ArrayList<>();
        con = ConnectionMYSQLManager.getConnection();
        try {
           
            String sql = " select * from celulares where  cel_nombre LIKE '%"+filtro+"%' OR cel_color LIKE '%"+filtro+"%' OR cel_descripcion LIKE '%"+filtro+"%'";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
           // preparedStmt.setString(1, filtro);
           // preparedStmt.setString(2, filtro);
           // preparedStmt.setString(3, filtro);
            ResultSet rs = preparedStmt.executeQuery();
           
            while (rs.next()) {
                System.out.println("" + rs.getString(6));
                // FileInputStream fileImagen = new FileInputStream(rs.getString(6));
                File file = new File(rs.getString(6));
                Image image = new Image(file.toURI().toString());
                ImageView imagenView = new ImageView(image);
                imagenView.setFitHeight(100);
                imagenView.setFitWidth(50);
                list.add(new Celular(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), new Button(), new Button(), new Button(), imagenView));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Celular> data = FXCollections.observableList(list);
        
        return data;
    }
    
    public void altaCelular(Celular celular) {
        
        try {
            
            con = ConnectionMYSQLManager.getConnection();
            String query = " insert into celulares (cel_nombre, cel_marca, cel_descripcion, cel_color, cel_imagen, cel_cantidad, cel_precio)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";
            System.out.println("nombre " + celular.getNombre());
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, celular.getNombre());
            preparedStmt.setString(2, celular.getMarca());
            preparedStmt.setString(3, celular.getDescripcion());
            preparedStmt.setString(4, celular.getColor());
            preparedStmt.setString(5, celular.getImagen());
            preparedStmt.setInt(6, celular.getCantidad());
            preparedStmt.setDouble(7, celular.getPrecio());
            
            preparedStmt.execute();
            
            con.close();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo registrar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /**
     * Elimina registro de la tabla celulares por id
     *
     * @param id
     */
    public void borrarCelular(int id) {
        con = ConnectionMYSQLManager.getConnection();
        String query = "delete from celulares where cel_id = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro borrado.");
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ModificarCelular(Celular celular) {
        con = ConnectionMYSQLManager.getConnection();
        try {
            String query = "UPDATE celulares set cel_nombre = ?, cel_marca = ?, cel_descripcion = ?, cel_color = ?, cel_imagen = ?, cel_cantidad = ?, cel_precio = ? WHERE cel_ID = ?   ";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, celular.getNombre());
            preparedStmt.setString(2, celular.getMarca());
            preparedStmt.setString(3, celular.getDescripcion());
            preparedStmt.setString(4, celular.getColor());
            preparedStmt.setString(5, celular.getImagen());
            preparedStmt.setInt(6, celular.getCantidad());
            preparedStmt.setDouble(7, celular.getPrecio());
            preparedStmt.setInt(8, celular.getID());
            
            preparedStmt.execute();            
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
