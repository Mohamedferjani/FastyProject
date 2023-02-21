/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.services.ServiceForum;
import java.io.IOException;
import javafx.geometry.Insets;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ServiceForum sf = new ServiceForum();

        List<Forum> forums = sf.getAllForums();

        
        for (Forum f : forums) {
listviewid.getItems().add(f.getTitre()+" "+f.getContenu());
        }
                 listviewid.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
              
                    String[] words=item.split("\\s",2);
                    String titre = words[0];
                    String contenu = words[1];
                    Text title = new Text(titre);
                    title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                    Text subtitle = new Text(contenu);
                    subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");

                    Button button = new Button("Modifier");

                    HBox buttonBox = new HBox(button);
                    buttonBox.setStyle("-fx-alignment: center-right;");

                    VBox vbox = new VBox(title, subtitle, buttonBox);
                    vbox.setStyle("-fx-background-color: white; -fx-padding: 10px;");
                    vbox.setPrefHeight(80);

                    setGraphic(vbox);
                }
            }
        });
        // TODO
    }    
 @FXML
    void afficherliste(MouseEvent event) {
//ListView<String> listView = new ListView<>();
//        ObservableList<String> items = FXCollections.observableArrayList();
//           sf.getAllForums();
//   List<Forum> forums = sf.getAllForums();
//        for (Forum f : forums) {
//           items.add(f.getTitre());
//        }
//      
//        
//
//        listView.setItems(items);
//
//        stackpane.getChildren().add(listView); 
            
        
//        for (CardItem item : items) {
//            vboxid.getChildren().add(item.getNode());
//        }

        
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
    
}



// class CardItem {
//    private String title;
//    private String description;
//
//    public CardItem(String title, String description) {
//        this.title = title;
//        this.description = description;
//    }
//
//    public Node getNode() {
//        // create the layout for the card item
//        BorderPane cardItem = new BorderPane();
//        cardItem.setTop(new Label(title));
//        cardItem.setCenter(new Label(description));
//        cardItem.setPadding(new Insets(10));
//
//        return cardItem;
//    }
//}
