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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */

public class AffichageQuestionsController implements Initializable {
    @FXML
    private Button logoutbtn;

    @FXML
    private Label labelid;

    @FXML
    private Label labelid1;
    
    @FXML
    private Button mesquestionsid;

    @FXML
    private Button resoluid;

    @FXML
    private Button nonresoluid;
    
    @FXML
    private Button backbtn;
    @FXML
    private Button allquestions;
    
    @FXML
    private ListView<String> listviewid;
    
     private String idforum;
     private String titre;
    /**
     * Initializes the controller class.
     */
     
     
     int i= 0;
     
     private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                           

         ServiceForum sf = new ServiceForum();
logoutbtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;-fx-cursor: hand;");
backbtn.setStyle("-fx-background-color: #F7F7F7; -fx-border-color: #C2C2C2; -fx-border-radius: 50; -fx-border-width:2; -fx-padding: 10 12; -fx-cursor: hand;");
allquestions.setStyle("-fx-background-color: #1E90FF;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 10px 20px;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
mesquestionsid.setStyle("-fx-background-color: #f9a825;-fx-text-fill: white; -fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
resoluid.setStyle("-fx-background-color: #4caf50;-fx-text-fill: white;-fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
nonresoluid.setStyle("-fx-background-color: #f44336;-fx-text-fill: white;-fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
Platform.runLater(()->{
    titre = prefs.get("titre", "default");
    labelid.setText("Welcome to "+titre+" Forum");
   idforum = prefs.get("idforum", "default");
    if (idforum != null && !idforum.isEmpty()) {
Forum f = sf.getForumById(Integer.parseInt(idforum));
 List<Question> questions = sf.getAllQuestionsById(f.getId_forum());
    
 for (Question q : questions) {
            listviewid.getItems().add(q.getId_question()+" "+q.getContenu());
        }
    }
});
                 listviewid.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                       String[] words=item.split("\\s",2);
                    String idquestion = words[0];
                    String question = words[1];
                   Text Question = new Text("Question "+Integer.toString(i)+" :");
                    Text title = new Text(question);
                    
//                     setOnMouseClicked(event -> {
//      if(event.getClickCount() == 2){
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
//                        try {
//                             Parent root = loader.load();
//                             AffichageQuestionsController aqc = loader.getController();
//                          aqc.setLabelID("Welcome to "+titre+" Forum");
//                        aqc.setForumID(idforum);
//                           labelid.getScene().setRoot(root);
//                        } catch (IOException e) {
//                            System.err.println("Error: "+e.getMessage());
//                        }  
//                        
//                      
//      }
//      });
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                    VBox vbox = new VBox(Question,title);
                    vbox.setPrefHeight(80);
                    setGraphic(vbox);
                    i++;
                }
            }
        });
    } 
    @FXML
    void BackBtnClicked(ActionEvent event) {
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
     try {
          Parent root = loader.load();
        listviewid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    @FXML
    void MesQuestionsClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
        listviewid.getScene().setRoot(root);
//        AffichageMesQuestionsController amqc = loader.getController();
//        amqc.SetForumID(idforum);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
    public void setLabelID(String message){
    this.labelid.setText(message);
    }
    
    public void setForumID(String message){
    this.idforum = message;
    }
    
    
}
