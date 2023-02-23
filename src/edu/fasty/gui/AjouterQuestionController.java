/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class AjouterQuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextArea question;

    @FXML
    private Button addbtn;

    @FXML
    private Button cancelbtn;
    
    private String idforum;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addbtn.setStyle("-fx-background-color: #4CAF50;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;;-fx-cursor: hand;");
        cancelbtn.setStyle(" -fx-background-color: #e74c3c;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-border-color: #c0392b;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;");
    } 
    
    @FXML
    void ajouterClicked(ActionEvent event) {
        if(question.getText().isEmpty()){
         Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid question !");
                    alertType.show();
        }else if(question.getText().matches("[0-9]+")){
            Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Question cannot be a number !");
                    alertType.show();
        }else {
          Question f = new Question(Integer.parseInt(idforum),question.getText());
               ServiceForum sf = new ServiceForum();
           sf.ajouterQuestion(f);
               Alert ok=new Alert(AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Question successfully created !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
          AffichageMesQuestionsController amqc = loader.getController();
          amqc.SetForumID(idforum);
        question.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }

 
 }
     
else if(result.get() == ButtonType.CANCEL){
    ok.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
}
    }
        }

    

    @FXML
    void annulerClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
          AffichageMesQuestionsController amqc = loader.getController();
          amqc.SetForumID(idforum);
        question.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }

    }
  public void SetForumID(String message){
    this.idforum = message;
    } 
    
}
