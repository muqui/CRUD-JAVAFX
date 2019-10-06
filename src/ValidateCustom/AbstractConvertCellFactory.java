/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidateCustom;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author patyy
 */
public interface AbstractConvertCellFactory<E, T> extends Callback<TableColumn<E, T>, TableCell<E, T>> {

    @Override
    default TableCell<E, T> call(TableColumn<E, T> param) {
        return new TableCell<E, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                System.out.println("ITEM " +  item.toString());
                
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(convert(item));
                }
            }
        };
    }

    String convert(T value);        
}
