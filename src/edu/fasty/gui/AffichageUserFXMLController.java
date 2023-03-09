/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
public class AffichageUserFXMLController implements Initializable {

    @FXML
    private ListView<User> LVAffiche;
    @FXML
    private Button BtnAjouterUser;
    @FXML
    private TextField searchbyname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  IServiceUser su = new IServiceUser();
        List<User> myusers = su.getAll();
        for (User u : myusers) {
        LVAffiche.getItems().add(u);
        }
        LVAffiche.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
//                    String[] words = item.split("\\s", 5);
//                    String cin = words[0];
//                    String nom = words[1];
//                    String prenom = words[2];
//                    String email = words[3];
//                    String adresse = words[4];

                    Text title = new Text("CIN : " + item.getCin());
                    Text nom = new Text("NOM : "+item.getNom());
                    Text prenom = new Text("PRENOM : " + item.getPrenom());
                       Image image = new Image(item.getImage());
                       ImageView imageview = new ImageView();
                       imageview.setImage(image);
                       double aspectRatio = image.getWidth() / image.getHeight();
                       double scaledWidth = 90 * aspectRatio;
                       imageview.setFitWidth(scaledWidth);
                       imageview.setFitHeight(90);
                       
                        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                        title.setStyle("-fx-text-alignment: center;-fx-font-size: 18px; -fx-font-weight: bold;");
                        
                        nom.setStyle("-fx-text-alignment: center;-fx-font-size: 14px; -fx-font-weight: bold;");
                        prenom.setStyle("-fx-text-alignment: center;-fx-font-size: 14px; -fx-font-weight: bold; ");

                        Button button = new Button("Modify");
                        button.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                        Button button1 = new Button("Delete");
                        button1.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");

                        button.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUserFXML.fxml"));
                                try {
                                    Parent root = loader.load();
                                    ModifierUserFXMLController muc = loader.getController();
                                    muc.setAdresse(item.getAdresse());
                                    muc.setEmail(item.getEmail());
                                    muc.setMdp(item.getPassword());
                                    muc.setNom(item.getNom());
                                    muc.setPrenom(item.getPrenom());
                                    muc.setTel(Integer.toString(item.getNum_tel()));
                                    muc.setCin(Integer.toString(item.getCin()));
                                    muc.setImage(item.getImage());
                                    muc.setID(item.getId_user());
                                    
                                    LVAffiche.getScene().setRoot(root);
                                } catch (IOException e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                            }
                        });
                        button1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirme suppression ");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez-vous vraiment supprimer cet utilisateur ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    IServiceUser su = new IServiceUser();
                                  
                                    su.supprimer(item.getId_user());
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFXML.fxml"));
                                    try {
                                        Parent root = loader.load();

                                        LVAffiche.getScene().setRoot(root);

                                    } catch (IOException e) {
                                        System.err.println("Error: " + e.getMessage());
                                    }
                                }
                            }
                        });
                        VBox imageBox = new VBox(imageview);
                        imageBox.setStyle("-fx-alignment: center;");
                        
                        HBox buttonBox = new HBox(button, button1);
                        buttonBox.setSpacing(25);
                        buttonBox.setStyle("-fx-alignment: center;");
                        VBox content = new VBox(title, nom,prenom);
                        content.setStyle("-fx-alignment: center;");
                        VBox vbox = new VBox(imageBox,content,buttonBox);
                        vbox.setMargin(imageBox,new Insets(5,0,15,0));
                        vbox.setMargin(title,new Insets(0,0,5,0));
                        vbox.setMargin(nom,new Insets(0,0,10,0));
                        vbox.setMargin(prenom,new Insets(0,0,15,0));
                        vbox.setMargin(buttonBox,new Insets(0,0,20,0));




                        setGraphic(vbox);                  
                }
            }
        });
        
searchbyname.textProperty().addListener((obs,oldValue,newValue)->{
                 LVAffiche.getItems().clear();
                 List<User> users = findUserByName(newValue);
                 for (User f : users) {
                     LVAffiche.getItems().add(f);
        }
         LVAffiche.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
//                    String[] words = item.split("\\s", 5);
//                    String cin = words[0];
//                    String nom = words[1];
//                    String prenom = words[2];
//                    String email = words[3];
//                    String adresse = words[4];

                    Text title = new Text("CIN : " + item.getCin());
                    Text nom = new Text("NOM : "+item.getNom());
                    Text prenom = new Text("PRENOM : " + item.getPrenom());
                       Image image = new Image(item.getImage());
                       ImageView imageview = new ImageView();
                       imageview.setImage(image);
                       double aspectRatio = image.getWidth() / image.getHeight();
                       double scaledWidth = 90 * aspectRatio;
                       imageview.setFitWidth(scaledWidth);
                       imageview.setFitHeight(90);
                       
                        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                        title.setStyle("-fx-text-alignment: center;-fx-font-size: 18px; -fx-font-weight: bold;");
                        
                        nom.setStyle("-fx-text-alignment: center;-fx-font-size: 14px; -fx-font-weight: bold;");
                        prenom.setStyle("-fx-text-alignment: center;-fx-font-size: 14px; -fx-font-weight: bold; ");

                        Button button = new Button("Modify");
                        button.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");
                        Button button1 = new Button("Delete");
                        button1.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-font-size: 12pt; -fx-font-weight: bold; -fx-padding:5 10; -fx-background-radius: 5;");

                        button.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUserFXML.fxml"));
                                try {
                                    Parent root = loader.load();
                                    ModifierUserFXMLController muc = loader.getController();
                                    muc.setAdresse(item.getAdresse());
                                    muc.setEmail(item.getEmail());
                                    muc.setMdp(item.getPassword());
                                    muc.setNom(item.getNom());
                                    muc.setPrenom(item.getPrenom());
                                    muc.setTel(Integer.toString(item.getNum_tel()));
                                    muc.setCin(Integer.toString(item.getCin()));
                                    muc.setImage(item.getImage());
                                    muc.setID(item.getId_user());
                                    
                                    LVAffiche.getScene().setRoot(root);
                                } catch (IOException e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                            }
                        });
                        button1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirme suppression ");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez-vous vraiment supprimer cet utilisateur ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    IServiceUser su = new IServiceUser();
                                  
                                    su.supprimer(item.getId_user());
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFXML.fxml"));
                                    try {
                                        Parent root = loader.load();

                                        LVAffiche.getScene().setRoot(root);

                                    } catch (IOException e) {
                                        System.err.println("Error: " + e.getMessage());
                                    }
                                }
                            }
                        });
                        VBox imageBox = new VBox(imageview);
                        imageBox.setStyle("-fx-alignment: center;");
                        
                        HBox buttonBox = new HBox(button, button1);
                        buttonBox.setSpacing(25);
                        buttonBox.setStyle("-fx-alignment: center;");
                        VBox content = new VBox(title, nom,prenom);
                        content.setStyle("-fx-alignment: center;");
                        VBox vbox = new VBox(imageBox,content,buttonBox);
                        vbox.setMargin(imageBox,new Insets(5,0,15,0));
                        vbox.setMargin(title,new Insets(0,0,5,0));
                        vbox.setMargin(nom,new Insets(0,0,10,0));
                        vbox.setMargin(prenom,new Insets(0,0,15,0));
                        vbox.setMargin(buttonBox,new Insets(0,0,20,0));




                        setGraphic(vbox);                  
                }
            }
        });      
                });
    }    

    @FXML
    private void AjouterOnClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterUserFXML.fxml"));
     try {
          Parent root = loader.load();
        LVAffiche.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    public List<User> findUserByName(String text){
  IServiceUser su = new IServiceUser();
    List<User> result = su.getAll().stream().filter((p)->p.getPrenom().toUpperCase().contains(text.toUpperCase())|| p.getNom().toUpperCase().contains(text.toUpperCase()) || Integer.toString(p.getCin()).contains(text)).collect(Collectors.toList());
    return result;
  
    }
    
}
