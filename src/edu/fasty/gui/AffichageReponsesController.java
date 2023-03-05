/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;


import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import edu.fasty.entities.Reponse;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
import edu.fasty.services.ServiceReponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.OverrunStyle;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class AffichageReponsesController implements Initializable {

    @FXML
    private Label selectedQID;
    @FXML
    private ListView<Reponse> listviewID;
    @FXML
    private Button logoutbtn;
    @FXML
    private Label RCountID;
    @FXML
    private Button backbtn;
    @FXML
    private Button addResponse;
    
    private Preferences prefs = Preferences.userNodeForPackage(AffichageQuestionsController.class);
        private Preferences prefs1 = Preferences.userNodeForPackage(AffichageReponsesController.class);

    int i;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     if(selectedQID.getText().isEmpty()){
     selectedQID.setText(prefs1.get("QuestionTitre", "default"));
     }
    ServiceReponse sr = new ServiceReponse();
    ServiceQuestion sq = new ServiceQuestion();
logoutbtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;-fx-cursor: hand;");
addResponse.setStyle("-fx-background-color: #0072C6;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8px 16px;-fx-border-radius: 24px;-fx-background-radius: 24px;-fx-cursor: hand;");
backbtn.setStyle("-fx-background-color: #F7F7F7; -fx-border-color: #C2C2C2; -fx-border-radius: 50; -fx-border-width:2; -fx-padding: 10 12; -fx-cursor: hand;");
backbtn.setAlignment(Pos.CENTER_RIGHT);
Question q = sq.getQuestionById(Integer.parseInt(prefs.get("idquestion", "default")));
       // System.err.println(q.getId_question());
List<Reponse> reponses = sr.getAllReponsesByQuestion(q.getId_question());
i = reponses.size();
if(i==1 || i == 0){
    RCountID.setText("There is "+i+" Response");

}else{
RCountID.setText("There is "+i+" Responses");
}
        for (Reponse r : reponses) {
            listviewID.getItems().add(r);
        }
                 listviewID.setCellFactory(param -> new ListCell<Reponse>() {
       
                     private ImageView imageView = new ImageView();             
            @Override
            protected void updateItem(Reponse item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                              

                    Text sentby = new Text("Sent by : ");
                    Text title = new Text("Reponse : "+item.getContenu());      
    
                    title.wrappingWidthProperty().bind(listviewID.widthProperty().subtract(10));
             Image image = new Image("/Images/user.png");
                    imageView.setImage(image);
                     double aspectRatio = image.getWidth() / image.getHeight();
                    double scaledWidth = 50 * aspectRatio;
                    imageView.setFitWidth(scaledWidth);
                    imageView.setFitHeight(50);
            
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                 //   Text subtitle = new Text(contenu);
                  //  subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");
                      
if(item.getIduser() == 15){
                    Button button = new Button("Modify");
                    button.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                    Button button1 = new Button("Delete");
                    button1.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                   HBox imageBox = new HBox(imageView);
                    imageBox.setStyle("-fx-alignment: center;");
                   
                    HBox buttonBox = new HBox(button, button1);
                 buttonBox.setSpacing(60);
                    buttonBox.setStyle("-fx-alignment: center;");
                    VBox vbox = new VBox(imageBox,sentby,title, buttonBox);
                     // title.wrappingWidthProperty().bind(listviewID.widthProperty().subtract(10));
                  vbox.setMargin(imageBox, new Insets(5, 0, 15, 0));
                  vbox.setMargin(sentby, new Insets(0, 0, 5, 0));
                  vbox.setMargin(title, new Insets(0, 0, 10, 0));
                  //  vbox.maxWidthProperty().bind(listviewID.widthProperty().subtract(10));
                    //vbox.maxHeightProperty().bind(listviewID.heightProperty().subtract(10));
                  //  vbox.setSpacing(20);
                    //vbox.setPrefHeight(160);
                  //  vbox.setPrefHeight(200);

                    setGraphic(vbox);
                    button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete ");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this Response?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceReponse sr = new ServiceReponse();
                       sr.supprimerReponse(item.getId_reponse());
                       System.out.println(item.getId_reponse());
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
                        try {
                             Parent root = loader.load();
                            
                             selectedQID.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                                    //                    button.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                       FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierForum.fxml"));
//                        try {
//                             Parent root = loader.load();
//                             ModifierForumController mfc = loader.getController();
//                             mfc.setTitreID(titre);
//                             mfc.setContenuID(contenu);
//                           searchbyname.getScene().setRoot(root);
//                        } catch (IOException e) {
//                            System.err.println("Error: "+e.getMessage());
//                        }
//                    }
//});
}else{
                   HBox imageBox = new HBox(imageView);
                    imageBox.setStyle("-fx-alignment: center;");
                    VBox vbox = new VBox(imageBox,sentby,title);
                     // title.wrappingWidthProperty().bind(listviewID.widthProperty().subtract(10));
                  vbox.setMargin(imageBox, new Insets(5, 0, 15, 0));
                  vbox.setMargin(sentby, new Insets(0, 0, 5, 0));
                  vbox.setMargin(title, new Insets(0, 0, 10, 0));
                  //  vbox.maxWidthProperty().bind(listviewID.widthProperty().subtract(10));
                    //vbox.maxHeightProperty().bind(listviewID.heightProperty().subtract(10));
                  //  vbox.setSpacing(20);
                    //vbox.setPrefHeight(160);
                  //  vbox.setPrefHeight(200);

                    setGraphic(vbox);
}
                }
            }
        });
      
    } 
    @FXML
    void BackBtnClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
     try {
          Parent root = loader.load();
        selectedQID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
    public void setLabelID(String message){
    this.selectedQID.setText(message);
    prefs1.put("QuestionTitre", message);
    }
    @FXML
    void AjouterClicked(ActionEvent event) {
      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReponse.fxml"));
                        try {
                            Parent root = loader.load();
                           logoutbtn.getScene().setRoot(root);
                    
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                    


    }
    
    
}

