package edu.fasty.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminFXMLController  implements Initializable {

    @FXML
    private Button btnUser;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnCotegories;
    @FXML
    private Button bRatingCategory;
    @FXML
    private Button bRating;
    @FXML
    private Button bScore;
    @FXML
    private StackPane ContentID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

          btnUser.fire();
    }
    
    @FXML
    private void OnUserClicked(ActionEvent event) throws IOException {
     Node page1 = FXMLLoader.load(getClass().getResource("AffichageUserFXML.fxml"));
        ContentID.getChildren().clear();
        ContentID.getChildren().add(page1);
       
    }

    @FXML
    private void Onmouseentred(MouseEvent event) throws IOException {
        Button b=(Button) event.getSource();
        b.setStyle("-fx-background-color: #32452E;");
       
                
    }
    @FXML
    private void Onmouseexited(MouseEvent event) throws IOException {
        
        Button b=(Button) event.getSource();
        b.setStyle("-fx-background-color: #98D08B;");
       
                
    }


    @FXML
    private void OnCategoriesClicked(ActionEvent event) throws IOException {
        //            Parent root = FXMLLoader.load(getClass().getResource("CategorieList.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
             Node page1 = FXMLLoader.load(getClass().getResource("CategorieList.fxml"));
Scene scene = ContentID.getScene();

// Set the size of the scene to match the size of the page
//scene.setRoot(null); // Workaround to avoid issue
scene.setRoot(new StackPane(page1)); // setRoot will trigger a size change
scene.getWindow().sizeToScene();
    }

    @FXML
    private void OnRatingCategoryClicked(ActionEvent event) {
    }

    @FXML
    private void OnRatingClicked(ActionEvent event) {
    }
        @FXML
    void OnEventClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuEvent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}
