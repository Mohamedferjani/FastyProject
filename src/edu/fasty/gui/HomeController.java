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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label evenementlbl;
    @FXML
    private Button logoutID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                logoutID.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;-fx-cursor: hand;");

        // TODO
    }    

    @FXML
    private void Produit(ActionEvent event) {
                            try {
            Parent root = FXMLLoader.load(getClass().getResource("ProduitList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void Categorie(ActionEvent event) {
//                            try {
//            Parent root = FXMLLoader.load(getClass().getResource("CategorieList.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void FrontClient(ActionEvent event) {
                                    try {
            Parent root = FXMLLoader.load(getClass().getResource("ClientProduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logoutClicked(ActionEvent event) {
                             try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ForumClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageForum.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void transactionClicked(ActionEvent event) {
                      try {
            Parent root = FXMLLoader.load(getClass().getResource("SidesBarmesTransaction.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BidClicked(ActionEvent event) {
                 try {
            Parent root = FXMLLoader.load(getClass().getResource("MakeBid.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
