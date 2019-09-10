/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidateCustom;

import javafx.scene.control.TextField;

/**
 *
 * @author mq12
 */
public class IntegerNumberTextField extends TextField {
    
     public IntegerNumberTextField(){
        this.setPromptText("solo numeros");
    }
    public void replaceText(int i, int i1, String string){
       
        
       if(string.matches("[0-9]*") || string.isEmpty()){
           super.replaceText(i, i1, string);
       } 
    }
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
}
