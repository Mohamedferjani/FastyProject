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
    @FXML
    private TextField idforum;

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
                alert.setTitle("ERROR ");
                alert.setHeaderText("Invalid title or content !");
                alert.show();
        }else if(titreid.getText().matches("[0-9]+")){
        Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR ");
                alert.setHeaderText("Title cannot be a number !");
                alert.show();
        }else{
         Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
         alertType.setTitle("CONFIRMATION !");
         alertType.setHeaderText("Are you sure you want to update this forum ?");
          Optional<ButtonType> result  = alertType.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 ServiceForum sf = new ServiceForum();
 Forum f = new Forum(Integer.parseInt(idforum.getText()),titreid.getText(),contenuid.getText());
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
    public void setForumID(String message){
    this.idforum.setText(message);
    idforum.setEditable(false);
    }
    public void setTitreID(String message){
    this.titreid.setText(message);
    }
    public void setContenuID(String message){
    this.contenuid.setText(message);
    }
}
