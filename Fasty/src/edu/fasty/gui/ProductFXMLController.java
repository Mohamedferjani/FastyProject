/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author MSI GAMING
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private Button logOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LogOutbtn(ActionEvent event) {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            try {
                Parent root = loader.load();

                logOut.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println("Error:" + ex.getMessage());
            }
    }
    
}
