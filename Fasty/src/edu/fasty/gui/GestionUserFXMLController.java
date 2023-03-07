/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import javafx.scene.paint.Color;
import java.awt.Paint;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class GestionUserFXMLController implements Initializable {

//    private TextField TfNom;
//    private TextField TfPrenom;
//    private TextField TfID;
//    private TextField tfSuppId;
//    private TextField TfAdresse;
//    private TextField TfCin;
//    private TextField TfNum;
//    private TextField TfMail;
//    private TextField TfPass;
    @FXML
    private ListView<User> LVAffiche;
    @FXML
    private Button logoutbtn;
    @FXML
    private TextField searchbyname;
    @FXML
    private Button BtnAjouterUser;
    @FXML
    private Button aaa;

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
                    System.out.println(item.getNom());
//                    String[] words = item.split("\\s", 5);
//                    String cin = words[0];
//                    String nom = words[1];
//                    String prenom = words[2];
//                    String email = words[3];
//                    String adresse = words[4];

                    Text title = new Text("CIN : " + item.getCin());

                    
                        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
                        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                        Text subtitle = new Text(item.getNom());
                        subtitle.setStyle("-fx-font-size: 14px; -fx-fill: gray;");

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
                                    
                                    searchbyname.getScene().setRoot(root);
                                } catch (IOException e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                            }
                        });
                        button1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirm Delete ");
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to delete this user?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    IServiceUser su = new IServiceUser();
                                    User u = new User();
                                    su.supprimer(u.getId_user());
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUserFXML.fxml"));
                                    try {
                                        Parent root = loader.load();

                                        searchbyname.getScene().setRoot(root);

                                    } catch (IOException e) {
                                        System.err.println("Error: " + e.getMessage());
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

}
