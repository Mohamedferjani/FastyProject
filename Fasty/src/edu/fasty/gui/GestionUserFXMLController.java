/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class GestionUserFXMLController implements Initializable {

    private TextField TfNom;
    private TextField TfPrenom;
    private TextField TfID;
    @FXML
    private Button BtnAfficher;
    @FXML
    private Button BtnSupprimer;
    @FXML
    private TextField tfSuppId;
    private TextField TfAdresse;
    private TextField TfCin;
    private TextField TfNum;
    private TextField TfMail;
    private TextField TfPass;
    @FXML
    private ListView<User> LVAffiche;
    @FXML
    private Button BtnWishlist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AfficherUser(ActionEvent event) {
        
        IServiceUser u = new IServiceUser();
        List<User> myusers = u.getAll();
        LVAffiche.getItems().addAll(myusers);
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
        int id = Integer.parseInt(tfSuppId.getText());
        IServiceUser sc = new IServiceUser();
        sc.supprimer(id);
    }

    @FXML
    private void GOWishlist(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WhishlistFXML.fxml"));
        try {
            Parent root = loader.load();

            tfSuppId.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

}
