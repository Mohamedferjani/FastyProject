/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Question;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ListView<Question> listviewid;
    
    @FXML
    private Button allquestions;
    
    @FXML
    private Button addbtn;
    
    private String idforum;
    private Preferences prefs = Preferences.userNodeForPackage(AffichageMesQuestionsController.class);
    private Preferences prefsUser = Preferences.userNodeForPackage(LoginFXMLController.class);

      ServiceForum sf = new ServiceForum();
         ServiceQuestion sq= new ServiceQuestion();
    int i = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
         
addbtn.setStyle("-fx-background-color: #4CAF50;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;;-fx-cursor: hand;");
logoutbtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;-fx-cursor: hand;");
backbtn.setStyle("-fx-background-color: #F7F7F7; -fx-border-color: #C2C2C2; -fx-border-radius: 50; -fx-border-width:2; -fx-padding: 10 12; -fx-cursor: hand;");
allquestions.setStyle("-fx-background-color: #1E90FF;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 10px 20px;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
mesquestionsid.setStyle("-fx-background-color: #f9a825;-fx-text-fill: white; -fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
resoluid.setStyle("-fx-background-color: #4caf50;-fx-text-fill: white;-fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
nonresoluid.setStyle("-fx-background-color: #f44336;-fx-text-fill: white;-fx-font-size: 14;-fx-padding: 10 20;-fx-font-weight: bold;-fx-border-radius: 25px;-fx-cursor: hand;-fx-background-radius: 25px;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
//Platform.runLater(()->{
 List<Question> questions = sq.getALLQuestionByUser(prefsUser.getInt("iduser", 0));
 for (Question q : questions) {
            listviewid.getItems().add(q);
        }
        
//});
                 listviewid.setCellFactory(param -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question item, boolean empty) {
                
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                     
                    String idquestion = Integer.toString(item.getId_question());
                    String question = item.getContenu();
                   Text Question = new Text("Question "+Integer.toString(i)+" :");
                    Text title = new Text(question);
                    prefs.put("idquestion", idquestion);
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
                    
                    HBox shareBox = new HBox(10);
        shareBox.setPadding(new Insets(10));
        
        // Create the Facebook logo image
        Image facebookLogo = new Image("/img/facebook.png");
        ImageView facebookImageView = new ImageView(facebookLogo);
        facebookImageView.setFitHeight(24);
        facebookImageView.setFitWidth(24);
        // Create the Share button
        Button shareButton = new Button("Share");
        shareButton.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-weight: bold;");
        shareButton.setGraphic(facebookImageView);
       // shareBox.getChildren().addAll(facebookImageView, shareButton);
                 shareButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       sq.PostQuestionOnFacebook(item);
                    }
});   
                    Button button = new Button("Modifier");
                    button.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                    Button button1 = new Button("Supprimer");
                    button1.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                   
                    button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierQuestion.fxml"));
                        try {
                            prefs.put("idquestion", idquestion);
                             Parent root = loader.load();
                             ModifierQuestionController mqc = loader.getController();
                             mqc.setContenuID(question);
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
                alert.setTitle("Confirme Suppression ");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer cette question ?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceForum sf = new ServiceForum();
                        sq.supprimerQuestion(Integer.parseInt(idquestion));
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
                        try {
                             Parent root = loader.load();
                            //AffichageMesQuestionsController amq = loader.getController();
                          //  amq.SetForumID(idforum);
                             listviewid.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                    HBox buttonBox = new HBox(shareButton,button, button1);
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
    @FXML
    void SolvedClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsResolues.fxml"));
     try {
          Parent root = loader.load();
        listviewid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }

    @FXML
    void UnsolvedClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsNonResolues.fxml"));
     try {
          Parent root = loader.load();
        listviewid.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
    public void SetForumID(String message){
    this.idforum = message;
    }
    
    
}
