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
import java.io.BufferedReader;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    private ListView<Question> listviewid;
    
     private String idforum;
     private String titre;
     private boolean isReported = false;
     boolean found = false;
    /**
     * Initializes the controller class.
     */
     
     
     int i= 0;
     
     private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
     private Preferences prefs1 = Preferences.userNodeForPackage(AffichageQuestionsController.class);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        //  isReported = prefs1.getBoolean("isreported", false);

         ServiceForum sf = new ServiceForum();
          ServiceQuestion sq= new ServiceQuestion();
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
 List<Question> questions = sq.getAllQuestionsById(f.getId_forum());
    
 for (Question q : questions) {
            listviewid.getItems().add(q);
        }
    }
});
                 listviewid.setCellFactory(param -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question item, boolean empty) {
                
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                   Text Question = new Text("Question "+Integer.toString(i)+" :");
                    Text title = new Text(item.getContenu());
                    prefs1.put("idquestion", Integer.toString(item.getId_question()));
                     setOnMouseClicked(event -> {
      if(event.getClickCount() == 2){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
                        prefs1.put("idquestion", Integer.toString(item.getId_question()));
                        try {
                             Parent root = loader.load();
                             AffichageReponsesController aqc = loader.getController();
                          aqc.setLabelID("Question : "+item.getContenu());
                          
                      //  aqc.setForumID(idforum);
                           labelid.getScene().setRoot(root);
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }  
                        
                      
      }
      });
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                    
                    Path filePath = Paths.get("src/Reports/report.txt");



try (BufferedReader reader = Files.newBufferedReader(filePath)) {
    String line;
    while ((line = reader.readLine()) != null) {
        if (line.contains("Question numero : "+Integer.toString(item.getId_question()))) {
            found = true;
           //isReported = true;
           break;
        }else{
        found = false;
        }
    }
} catch (IOException e) {
    // Handle exception
}
                    
                    Button button = new Button(found ?"Unreport":"Report");
                    button.setStyle("-fx-background-color: #d32f2f;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 6px 10px;-fx-border-radius: 4px;-fx-cursor: hand;");
                   
button.setOnAction(e -> {
    // Toggle the isReported flag
    found = !found;
    //prefs1.put("isreported", new Boolean(isReported).toString());
    // Add or remove the question ID from the report file

    File reportFile = new File("src/Reports/report.txt");
    try {
        if (found) {
            Files.write(reportFile.toPath(), Collections.singletonList("Question numero : "+Integer.toString(item.getId_question())), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            button.setText("Unreport");
           // isReported = false;
        } else {
            List<String> lines = Files.lines(reportFile.toPath()).filter(line -> !line.contains("Question numero : "+Integer.toString(item.getId_question()))).collect(Collectors.toList());
            Files.write(reportFile.toPath(), lines, StandardOpenOption.TRUNCATE_EXISTING);
            button.setText("Report");
           //isReported = true;
        }
    } catch (IOException ex) {
        System.err.println(ex);
    }
});
                    

                    HBox buttonBox = new HBox(button);
                     
                     //buttonBox.setSpacing(10);
                    buttonBox.setStyle("-fx-alignment: center-right;");
                    VBox vbox = new VBox(Question,title,buttonBox);
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
    
    public void setLabelID(String message){
    this.labelid.setText(message);
    }
    
    public void setForumID(String message){
    this.idforum = message;
    }
    
    
}
