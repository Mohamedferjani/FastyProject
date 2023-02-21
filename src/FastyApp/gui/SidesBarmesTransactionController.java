/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ISSAM
 */
public class SidesBarmesTransactionController implements Initializable {

    @FXML
    private TextField getNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnaffiche(ActionEvent event) {
        String nom = getNom.getText();
      if(nom.isEmpty()){
       Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Remplir le champ");
    alert.show();}
   else if (!nom.matches("^[a-zA-Z]*$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("veuillez entré le nom de la facture");
    alert.show();
    }else{
            System.err.println("working");

    }
    }
    
}
