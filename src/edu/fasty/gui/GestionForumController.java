/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.services.ServiceForum;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid title and content !");
                    alertType.show();
          }
              else if (tfTitreForum.getText().isEmpty()){
                    alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid title !");
                    alertType.show();
                      } else if (tfContenu.getText().isEmpty()){
                    alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid content !");
                    alertType.show();
                      }else if(tfTitreForum.getText().toString().matches("[0-9]+")){
                    alertType.setTitle("Error");
                    alertType.setHeaderText("title must be string not number !");
                    alertType.show();
                      } else{
          String nom = tfTitreForum.getText();
          String contenu = tfContenu.getText();
               System.err.println(contenu);
                  Forum f = new Forum(nom,contenu);
               ServiceForum sf = new ServiceForum();
           boolean test = sf.ajouterForum(f);
           if(test == true){
               Alert ok=new Alert(AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Forum successfully created !");
              
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
        tfTitreForum.clear();
        tfContenu.clear();
    }
    
}
