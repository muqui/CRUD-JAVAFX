/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidateCustom;

import java.util.regex.Pattern;
import javafx.scene.control.TextField;

/**
 *
 * @author mq12
 */
public class FloatNumberTextField extends TextField{
    
    final Pattern pattern = Pattern.compile("^\\d*\\.?\\d*$");
    public FloatNumberTextField(){
        this.setPromptText("solo numeros");
    }

    @Override
   public void replaceText(int start, int end, String text) {
       String newText = getText().substring(0, start)+text+getText().substring(end);
        if (pattern.matcher(newText).matches()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        int start = getSelection().getStart();
        int end = getSelection().getEnd();
        String newText = getText().substring(0, start)+text+getText().substring(end);
        if (pattern.matcher(newText).matches()) {
            super.replaceSelection(text);
        }
    }
    
}
