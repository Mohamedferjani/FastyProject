/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Question;
import edu.fasty.services.ServiceForum;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
public class AffichageMesQuestionsController implements Initializable {
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
    private ListView<String> listviewid;
    
    @FXML
    private Button allquestions;
    
    @FXML
    private Button addbtn;
    
    private String idforum;
    
    int i = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ServiceForum sf = new ServiceForum();
addbtn.setStyle("-fx-background-color: #4CAF50;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;;-fx-cursor: hand;");
logoutbtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;-fx-cursor: hand;");
backbtn.setStyle("-fx-background-color: #F7F7F7; -fx-border-color: #C2C2C2; -fx-border-radius: 50; -fx-border-width:2; -fx-padding: 10 12; -fx-cursor: hand;");
allquestions.setStyle("-fx-background-color: #1E90FF;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 10px 20px;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
mesquestionsid.setStyle("-fx-background-color: #f9a825;-fx-text-fill: white; -fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
resoluid.setStyle("-fx-background-color: #4caf50;-fx-text-fill: white;-fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
nonresoluid.setStyle("-fx-background-color: #f44336;-fx-text-fill: white;-fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
//Platform.runLater(()->{
 List<Question> questions = sf.getALLQuestionByUser(15);
 for (Question q : questions) {
            listviewid.getItems().add(q.getId_question()+" "+q.getContenu());
        }
        
//});
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
                    Button button = new Button("Modify");
                    button.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                    Button button1 = new Button("Delete");
                    button1.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                   
                    button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierForum.fxml"));
                        try {
                             Parent root = loader.load();
                           listviewid.getScene().setRoot(root);
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                    }
});
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete ");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this question?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceForum sf = new ServiceForum();
                        sf.supprimerQuestion(Integer.parseInt(idquestion));
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
                        try {
                             Parent root = loader.load();
                            AffichageMesQuestionsController amq = loader.getController();
                            amq.SetForumID(idforum);
                             listviewid.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                    HBox buttonBox = new HBox(button, button1);
                 buttonBox.setSpacing(10);
                    buttonBox.setStyle("-fx-alignment: center-right;");
                                 


                  
                  //  button1Box.setStyle("-fx-alignment: center-right;");
                    VBox vbox = new VBox(Question,title,buttonBox);
                  //  vbox.setStyle("-fx-background-color: white; -fx-padding: 10px;");
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

    }
    @FXML
    void AllQuestionsClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
     try {
          Parent root = loader.load();
          AffichageQuestionsController aqc = loader.getController();
          aqc.setForumID(idforum);
        listviewid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    @FXML
    void ajouterClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterQuestion.fxml"));
     try {
          Parent root = loader.load();
          AjouterQuestionController aqc = loader.getController();
          aqc.SetForumID(idforum);
        listviewid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }

    }
    
    public void SetForumID(String message){
    this.idforum = message;
    }
    
    
}
