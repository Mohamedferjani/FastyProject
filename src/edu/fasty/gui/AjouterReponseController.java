/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
import edu.fasty.services.ServiceReponse;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class AjouterReponseController implements Initializable {

    @FXML
    private TextArea answerID;
    @FXML
    private Button addbtn;
    @FXML
    private Button cancelbtn;

        private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
        private Preferences prefs1 = Preferences.userNodeForPackage(AffichageQuestionsController.class);

final ServiceForum sf= new ServiceForum();
final ServiceQuestion sq = new ServiceQuestion();
final ServiceReponse sr = new ServiceReponse();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterClicked(ActionEvent event) {
        if(answerID.getText().isEmpty()){
         Alert alertType=new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid Response !");
                    alertType.show();
        }else if(answerID.getText().matches("[0-9]+")){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Answer cannot be a number !");
                    alertType.show();
        }else {
            Forum f = sf.getForumById(Integer.parseInt(prefs.get("idforum", "default")));
          Question q = sq.getQuestionById(Integer.parseInt(prefs.get("idquestion", "default")));
          // Change 15 while integrating ...
          Reponse r = new Reponse(f,answerID.getText(),q,15);
           sr.ajouterReponse(r);
               Alert ok=new Alert(Alert.AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Response successfully created !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        answerID.getScene().setRoot(root);
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
    private void annulerClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        answerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
}
