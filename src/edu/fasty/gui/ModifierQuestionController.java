/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
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

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class ModifierQuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea questiontype;
        private Preferences prefs = Preferences.userNodeForPackage(AffichageMesQuestionsController.class);
        private Preferences prefsForum = Preferences.userNodeForPackage(AffichageForumController.class);

         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   


    @FXML
    void CancelClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
        questiontype.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }

    @FXML
    void modifierquestion(ActionEvent event) {
 if(questiontype.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR ");
                alert.setHeaderText("Invalid Question !");
                alert.show();
        }else if(questiontype.getText().matches("[0-9]+")){
        Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR ");
                alert.setHeaderText("Question cannot be a number !");
                alert.show();
        }else{
         Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
         alertType.setTitle("CONFIRMATION !");
         alertType.setHeaderText("Are you sure you want to update this question ?");
          Optional<ButtonType> result  = alertType.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 ServiceForum sf = new ServiceForum();
  ServiceQuestion sq= new ServiceQuestion();
 Forum f = sf.getForumById(Integer.parseInt(prefsForum.get("idforum", "default")));
 
 // while integrating , modify 15 with current userid that you got from other pages
 
 Question q=  new Question(Integer.parseInt(prefs.get("idquestion", "default")),f,questiontype.getText(),15);
 sq.modifierQuestion(q);
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
        questiontype.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
 }
        }
    }
    public void setContenuID(String message){
    this.questiontype.setText(message);
    }
    
}
