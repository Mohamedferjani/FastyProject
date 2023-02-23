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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class GestionUserFXMLController implements Initializable {

    @FXML
    private TextField TfNom;
    @FXML
    private TextField TfPrenom;
    @FXML
    private TextField TfID;
    @FXML
    private Button BtnAfficher;
    @FXML
    private Button BtnSupprimer;
    @FXML
    private TextField tfSuppId;
    @FXML
    private TextField TfAdresse;
    @FXML
    private TextField TfCin;
    @FXML
    private TextField TfNum;
    @FXML
    private TextField TfMail;
    @FXML
    private TextField TfPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AfficherUser(ActionEvent event) {
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
        String id = tfSuppId.getText();
        User s = new User;
        IServiceUser sc = new IServiceUser();
        sc.ajouter(s);
    }

    public void SetTfID(String message) {
        this.TfID.setText(message);
    }

    public void SetTfNom(String message) {
        this.TfNom.setText(message);

    }

    public void SetTfPrenom(String message) {
        this.TfPrenom.setText(message);
    }

    public void SetTfAdresse(String message) {
        this.TfAdresse.setText(message);
    }

    public void SetTfCin(String message) {
        this.TfCin.setText(message);
    }

    public void SetTfNum(String message) {
        this.TfNum.setText(message);
    }

    public void SetTfMail(String message) {
        this.TfMail.setText(message);
    }

    public void SetTfPass(String message) {
        this.TfPass.setText(message);
    }

}
