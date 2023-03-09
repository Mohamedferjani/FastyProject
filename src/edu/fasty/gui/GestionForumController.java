/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceForum;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class GestionForumController implements Initializable {

    @FXML
    private TextField tfTitreForum;
    @FXML
    private TextArea tfContenu;
        private Preferences prefs = Preferences.userNodeForPackage(LoginFXMLController.class);

    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAjoutClicked(ActionEvent event) {
         Alert alertType=new Alert(AlertType.ERROR);
            try{
                
           if((tfTitreForum.getText().isEmpty()) && (tfContenu.getText().isEmpty())){
                alertType.setTitle("Erreur");
                    alertType.setHeaderText("Entrez un titre et un contenu valide !");
                    alertType.show();
          }
              else if (tfTitreForum.getText().isEmpty()){
                    alertType.setTitle("Erreur");
                    alertType.setHeaderText("Entrez un titre valide !");
                    alertType.show();
                      } else if (tfContenu.getText().isEmpty()){
                    alertType.setTitle("Erreur");
                    alertType.setHeaderText("Entrez un contenu valide !");
                    alertType.show();
                      }else if(tfTitreForum.getText().toString().matches("[0-9]+")){
                    alertType.setTitle("Erreur");
                    alertType.setHeaderText("le titre doit être une chaîne et non un nombre !");
                    alertType.show();
                      } else{
          String nom = tfTitreForum.getText();
          String contenu = tfContenu.getText();
          int id = prefs.getInt("iduser", 0);
          IServiceUser su = new IServiceUser();
          User u = su.getOneById(id);
                  Forum f = new Forum(nom,contenu,u);
               ServiceForum sf = new ServiceForum();
           boolean test = sf.ajouterForum(f);
           if(test == true){
               Alert ok=new Alert(AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Forum créé avec succès !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
     try {
          Parent root = loader.load();
        tfTitreForum.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
 }
     
else if(result.get() == ButtonType.CANCEL){
    ok.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
}
    }
                      }
            }
            catch(Exception e){
                System.err.println(e);
        }
    }

    @FXML
    private void btnViderClicked(ActionEvent event) {
//        tfTitreForum.clear();
//        tfContenu.clear();
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
     try {
          Parent root = loader.load();
        tfTitreForum.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
}
