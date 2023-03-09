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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class ModifierForumController implements Initializable {

    @FXML
    private TextField titreid;
    @FXML
    private TextArea contenuid;
    
    private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
    private Preferences prefUser = Preferences.userNodeForPackage(LoginFXMLController.class);



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
      @FXML
    void CancelClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
     try {
          Parent root = loader.load();
        titreid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }

    @FXML
    void modifierForum(ActionEvent event) {
        if(titreid.getText().isEmpty() || contenuid.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR ");
                alert.setHeaderText("Titre ou contenu invalide !");
                alert.show();
        }else if(titreid.getText().matches("[0-9]+")){
        Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR ");
                alert.setHeaderText("Le titre ne peut pas être un nombre !");
                alert.show();
        }else{
         Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
         alertType.setTitle("CONFIRMATION !");
         alertType.setHeaderText("Êtes-vous sûr de vouloir mettre à jour ce forum ?");
          Optional<ButtonType> result  = alertType.showAndWait();
                        
 if(result.get() == ButtonType.OK){
     int id = prefUser.getInt("iduser", 0);
     IServiceUser su = new IServiceUser();
 ServiceForum sf = new ServiceForum();
 User u = su.getOneById(id);
 Forum f = new Forum(Integer.parseInt(prefs.get("idforum", "default")),titreid.getText(),contenuid.getText(),u);
 sf.modifierForum(f);
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
     try {
          Parent root = loader.load();
        titreid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
 }
        }

    }
    public void setTitreID(String message){
    this.titreid.setText(message);
    }
    public void setContenuID(String message){
    this.contenuid.setText(message);
    }
    
}
