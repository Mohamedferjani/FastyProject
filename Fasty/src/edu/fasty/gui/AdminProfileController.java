/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AdminProfileController implements Initializable {
    
    @FXML
    private Button bRatingCategory;
    @FXML
    private Button bRating;
    @FXML
    private Button bScore;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void OnRatingCategoryClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingCategoryInterface.fxml"));
        Parent root = loader.load();
        bRatingCategory.getScene().setRoot(root);
       
                
    }
     @FXML
    private void OnRatingClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingInterface.fxml"));
        Parent root = loader.load();
        bRating.getScene().setRoot(root);
       
                
    }
     @FXML
    private void OnScoreClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoresInterface.fxml"));
        Parent root = loader.load();
        bScore.getScene().setRoot(root);
       
                
    }
    
    
    
}
