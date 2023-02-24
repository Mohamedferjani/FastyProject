/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Produit;
import edu.fasty.services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class Ajouter_produitController implements Initializable {
   @FXML
    private TextField tfNom_produit;
   @FXML
    private TextField tfDescription;
   @FXML
    private TextField tfvaleur;
   // private TextField tfimage;
    @FXML
    private Button btn;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    void ajouterProduit(ActionEvent event) {
      if (tfNom_produit.getText().isEmpty()|| tfDescription.getText().isEmpty() || tfvaleur.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nom produit ou valeur invalide(s)", ButtonType.OK);
            a.showAndWait();
        } else {
            int id_user= 1;
            int id_categorie= 1;
            String img="eafzkfzl";
            String description = "bjgj";
            ServiceProduit sp = new ServiceProduit();
            
            Produit p = new Produit(id_user,id_categorie,tfNom_produit.getText(),tfvaleur.getText(),img,description);
            System.out.println(p);
            sp.ajouter(p);
            
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Produit added !", ButtonType.OK);
            a.show();
          
            
            // Gestion_produitFXMLController apc = loader.getController();
            //apc.setNom_produit(tfNom_produit.getText());
            //apc.setDescription(tfDescription.getText());
            //apc.setvaleur(tfvaleur.getText());
        }

    
    }
}
