/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI GAMING
 */
public class GestionUserFXMLController implements Initializable {

    @FXML
    private TextField TfNom;
    @FXML
    private TextField tfPrenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterUser(ActionEvent event) {
        IServiceUser su = new IServiceUser();
       // User u = new User(TfNom.getText(), TfPrenom.getText());
                }
    
}
