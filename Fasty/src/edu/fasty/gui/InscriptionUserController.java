/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IService;
import edu.fasty.services.IServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI GAMING
 */
public class InscriptionUserController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfCin;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private Button BtnInscri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterUser(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        int tel = Integer.parseInt(tfTel.getText());
        String adresse = tfAdresse.getText();
        int cin = Integer.parseInt(tfCin.getText());
        String email = tfEmail.getText();
        String mdp = tfMdp.getText();

        User s = new User(cin, tel, nom, prenom, adresse, email, prenom);
        IServiceUser sc = new IServiceUser();
        sc.ajouter(s);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUserFXML.fxml"));
        try {
            Parent root = loader.load();
           GestionUserFXMLController guc = loader.getController();
           guc.SetTfAdresse(adresse);
           guc.SetTfCin(""+s.getCin());
           guc.SetTfMail(s.getEmail());
           guc.SetTfNum(""+s.getNum_tel());
           guc.SetTfNom(s.getNom());
           guc.SetTfPrenom(s.getPrenom());
           guc.SetTfID(""+s.getId_user());
           guc.SetTfPass(s.getPassword());
           
           tfNom.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println("Error:"+ex.getMessage());        
        }
    }

}
