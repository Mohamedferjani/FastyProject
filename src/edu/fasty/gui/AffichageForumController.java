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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class AffichageForumController implements Initializable {

    @FXML
    private TextField searchbyname;
    @FXML
    private StackPane stackpane;
    @FXML
    private VBox vboxid;
    @FXML
    private ListView<String> listviewid;
    @FXML
    private Button logoutbtn;
    @FXML
    private CheckBox checkboxID;
    
    private Scene scene;
    
    private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
    
    private int i = 0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       ServiceForum sf = new ServiceForum();
logoutbtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;-fx-cursor: hand;");
        List<Forum> forums = sf.getAllForums();
        
        for (Forum f : forums) {
            listviewid.getItems().add(f.getId_forum()+" "+f.getTitre()+" "+f.getContenu());
        }
        
                 listviewid.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                       String[] words=item.split("\\s",3);
                    String idforum = words[0];
                    String titre = words[1];
                    String contenu = words[2];
                    prefs.put("titre", titre);
                    prefs.put("idforum", idforum);
                    Text title = new Text("TITLE : "+titre);
                    
                     setOnMouseClicked(event -> {
      if(event.getClickCount() == 2){
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
                        try {
                             Parent root = loader.load();
                             prefs.put("idforum", idforum);
                             prefs.put("titre", titre);
                            // AffichageQuestionsController aqc = loader.getController();
                            // aqc.setLabelID("Welcome to "+titre+" Forum");
                            // Forum f = new Forum(idforum,titre,contenu);
                       // aqc.setForumID(idforum);
                           searchbyname.getScene().setRoot(root);
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }  
                        
                      
      }
      });
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                    Text subtitle = new Text("Description : "+contenu);
                    subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");

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
                             ModifierForumController mfc = loader.getController();
                             mfc.setTitreID(titre);
                             mfc.setContenuID(contenu);
                           searchbyname.getScene().setRoot(root);
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
                alert.setContentText("Are you sure you want to delete this forum?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceForum sf = new ServiceForum();
                        sf.supprimerForum(Integer.parseInt(idforum));
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
                        try {
                             Parent root = loader.load();
                            
                             searchbyname.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                    HBox buttonBox = new HBox(button, button1);
                 buttonBox.setSpacing(10);
                    buttonBox.setStyle("-fx-alignment: center-right;");
                    VBox vbox = new VBox(title, subtitle, buttonBox);
                  
                    vbox.setPrefHeight(80);

                    setGraphic(vbox);
                }
            }
        });
                 
                
                searchbyname.textProperty().addListener((obs,oldValue,newValue)->{
                 listviewid.getItems().clear();
                 List<Forum> forumss = findForumByTitle(newValue);
                 for (Forum f : forumss) {
                     listviewid.getItems().add(f.getId_forum()+" "+f.getTitre()+" "+f.getContenu());
        }
           listviewid.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                       String[] words=item.split("\\s",3);
                    String idforum = words[0];
                    String titre = words[1];
                    String contenu = words[2];
                    Text title = new Text("TITLE : "+titre);
                    prefs.put("titre", titre);
                    prefs.put("idforum", idforum);
                     setOnMouseClicked(event -> {
      if(event.getClickCount() == 2){
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
                        try {
                             Parent root = loader.load();
                            // AffichageQuestionsController aqc = loader.getController();
                            // aqc.setLabelID("Welcome to "+titre+" Forum");
                            // Forum f = new Forum(idforum,titre,contenu);
                          prefs.put("idforum", idforum);
                          prefs.put("titre", titre);
                       // aqc.setForumID(idforum);
                           searchbyname.getScene().setRoot(root);
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }  
                        
                      
      }
      });
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                    Text subtitle = new Text("Description : "+contenu);
                    subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");

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
                             ModifierForumController mfc = loader.getController();
                             mfc.setTitreID(titre);
                             mfc.setContenuID(contenu);
                           searchbyname.getScene().setRoot(root);
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
                alert.setContentText("Are you sure you want to delete this forum?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceForum sf = new ServiceForum();
                        sf.supprimerForum(Integer.parseInt(idforum));
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
                        try {
                             Parent root = loader.load();
                            
                             searchbyname.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                    HBox buttonBox = new HBox(button, button1);
                 buttonBox.setSpacing(10);
                    buttonBox.setStyle("-fx-alignment: center-right;");
                    VBox vbox = new VBox(title, subtitle, buttonBox);
                  
                    vbox.setPrefHeight(80);

                    setGraphic(vbox);
                }
            }
        });      
                });
                
                checkboxID.selectedProperty().addListener((obs, oldValue, newValue) -> {
            listviewid.getItems().clear();
            if (newValue) {
                TreeSet<Forum> Sortedforums = SortForumByTitle();
                for (Forum f : Sortedforums) {
                     listviewid.getItems().add(f.getId_forum()+" "+f.getTitre()+" "+f.getContenu());
        }
            listviewid.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                       String[] words=item.split("\\s",3);
                    String idforum = words[0];
                    String titre = words[1];
                    String contenu = words[2];
                    Text title = new Text("TITLE : "+titre);
                    prefs.put("titre", titre);
                    prefs.put("idforum", idforum);
                     setOnMouseClicked(event -> {
      if(event.getClickCount() == 2){
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
                        try {
                             Parent root = loader.load();
                            // AffichageQuestionsController aqc = loader.getController();
                            // aqc.setLabelID("Welcome to "+titre+" Forum");
                            // Forum f = new Forum(idforum,titre,contenu);
                          prefs.put("idforum", idforum);
                          prefs.put("titre", titre);
                       // aqc.setForumID(idforum);
                           searchbyname.getScene().setRoot(root);
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }  
                        
                      
      }
      });
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                    Text subtitle = new Text("Description : "+contenu);
                    subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");

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
                             ModifierForumController mfc = loader.getController();
                             mfc.setTitreID(titre);
                             mfc.setContenuID(contenu);
                           searchbyname.getScene().setRoot(root);
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
                alert.setContentText("Are you sure you want to delete this forum?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceForum sf = new ServiceForum();
                        sf.supprimerForum(Integer.parseInt(idforum));
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
                        try {
                             Parent root = loader.load();
                            
                             searchbyname.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                    HBox buttonBox = new HBox(button, button1);
                 buttonBox.setSpacing(10);
                    buttonBox.setStyle("-fx-alignment: center-right;");
                    VBox vbox = new VBox(title, subtitle, buttonBox);
                  
                    vbox.setPrefHeight(80);

                    setGraphic(vbox);
                }
            }
        });    
            } else {
               List<Forum> forum = sf.getAllForums();
        
        for (Forum f : forum) {
            listviewid.getItems().add(f.getId_forum()+" "+f.getTitre()+" "+f.getContenu());
        }
        
                 listviewid.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                       String[] words=item.split("\\s",3);
                    String idforum = words[0];
                    String titre = words[1];
                    String contenu = words[2];
                    prefs.put("titre", titre);
                    prefs.put("idforum", idforum);
                    Text title = new Text("TITLE : "+titre);
                    
                     setOnMouseClicked(event -> {
      if(event.getClickCount() == 2){
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageQuestions.fxml"));
                        try {
                             Parent root = loader.load();
                             prefs.put("idforum", idforum);
                             prefs.put("titre", titre);
                            // AffichageQuestionsController aqc = loader.getController();
                            // aqc.setLabelID("Welcome to "+titre+" Forum");
                            // Forum f = new Forum(idforum,titre,contenu);
                       // aqc.setForumID(idforum);
                           searchbyname.getScene().setRoot(root);
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }  
                        
                      
      }
      });
                 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                    Text subtitle = new Text("Description : "+contenu);
                    subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");

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
                             ModifierForumController mfc = loader.getController();
                             mfc.setTitreID(titre);
                             mfc.setContenuID(contenu);
                           searchbyname.getScene().setRoot(root);
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
                alert.setContentText("Are you sure you want to delete this forum?");
                Optional<ButtonType> result =  alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    ServiceForum sf = new ServiceForum();
                        sf.supprimerForum(Integer.parseInt(idforum));
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageForum.fxml"));
                        try {
                             Parent root = loader.load();
                            
                             searchbyname.getScene().setRoot(root);
                        
                        } catch (IOException e) {
                            System.err.println("Error: "+e.getMessage());
                        }
                }
                    }
});
                    HBox buttonBox = new HBox(button, button1);
                 buttonBox.setSpacing(10);
                    buttonBox.setStyle("-fx-alignment: center-right;");
                    VBox vbox = new VBox(title, subtitle, buttonBox);
                  
                    vbox.setPrefHeight(80);

                    setGraphic(vbox);
                }
            }
        }); 
            }
        });
        // TODO
    }    
 @FXML
    void afficherliste(MouseEvent event) {
    }
    @FXML
    private void ajouterforum(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionForum.fxml"));
     try {
          Parent root = loader.load();
        searchbyname.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
    
    public void setScene(Scene scene){
    this.scene = scene;
    }
    
    //Recherche par nom
    public List<Forum> findForumByTitle(String title){
        ServiceForum sf = new ServiceForum();
    List<Forum> result = sf.getAllForums().stream().filter((p)->p.getTitre().toUpperCase().contains(title.toUpperCase())).collect(Collectors.toList());
    return result;
  
    }
    
    // trie par nom
    public TreeSet<Forum> SortForumByTitle(){
        ServiceForum sf = new ServiceForum();
        TreeSet<Forum> forums =sf.getAllForums().stream().collect(Collectors.toCollection(()-> new TreeSet<Forum>((a,b)->a.getTitre().toUpperCase().compareTo(b.getTitre().toUpperCase()))));
        return forums;
    }
    
}

