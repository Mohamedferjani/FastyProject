/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class MenuEventController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label evenementlbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eventManagement(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/fasty/gui/EventList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BidManagement(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BidList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BidListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void PackageDealManagement(ActionEvent event) {
                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PackageDealList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PackageDealListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
