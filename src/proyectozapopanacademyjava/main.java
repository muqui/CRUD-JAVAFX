/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectozapopanacademyjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mq12
 */
public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
       Parent root = FXMLLoader.load(getClass().getResource("/Resources/views/scenePrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
       // stage.setResizable(false);
       stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
