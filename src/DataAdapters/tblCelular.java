/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAdapters;

import Beans.Celular;
import Persistencia.ConnectionMYSQLManager;
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
import javax.swing.JOptionPane;

/**
 *
 * @author mq12
 */
public class tblCelular {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ObservableList<Celular> getInitialTableData() {
        List<Celular> list = new ArrayList<>();
        con = ConnectionMYSQLManager.getConnection();
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM celulares";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                list.add(new Celular(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getBigDecimal(8), new Button(), new Button(), new Button()));
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
          
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, celular.getNombre());
            preparedStmt.setString(2, celular.getMarca());
            preparedStmt.setString(3, celular.getDescripcion());
            preparedStmt.setString(4, celular.getColor());
            preparedStmt.setString(5, celular.getImagen());
            preparedStmt.setInt(6,celular.getCantidad());
            preparedStmt.setBigDecimal(7, celular.getPrecio());

           preparedStmt.execute();
           
             con.close();
              JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Could not open file", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }

    /**
     * Elimina registro de la tabla celulares por id
     * @param id
     */
    public void borrarCelular(int id){
         con = ConnectionMYSQLManager.getConnection();
          String query = "delete from celulares where cel_id = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,id);
            preparedStmt.executeUpdate();
              JOptionPane.showMessageDialog(null, "Registro borrado.");
        } catch (SQLException ex) {
            Logger.getLogger(tblCelular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
