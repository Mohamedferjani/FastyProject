/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.geekerz.gui;

import edu.geekerz.entities.CategoryRating;
import edu.geekerz.services.ServiceCategoryRating;
import edu.geekerz.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class RatingCategoryInterfaceController implements Initializable {
    
    
    @FXML
    private Button bRatingCategory,bDelete;
    @FXML
    private Button bRating;
    @FXML
    private Button bScore;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDescription,tfChooseCat,tfUpdateName,tfUpdateDesc,tfChooseDelete;
    
    @FXML
    private ListView<CategoryRating> ratingCategoryView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceCategoryRating scr = new ServiceCategoryRating();
        List<CategoryRating> myRatingCats = scr.getAll();
        ratingCategoryView.getItems().addAll(myRatingCats);
    }    
    
    
    @FXML
    private void Onmouseentred(MouseEvent event) throws IOException {
        
        Button b=(Button) event.getSource();
        String fxid="";
        if (b.getId() != null){fxid=b.getId();}
        if(fxid.equals("bAddCategory") || fxid.equals("bBrowse") || fxid.equals("bBrowseUpdate") || fxid.equals("bUpdate") || fxid.equals("bDelete") ){
        b.setStyle("-fx-background-color: #594778;");
        }else{b.setStyle("-fx-background-color: #32452E;");}
                
    }
    @FXML
    private void Onmouseexited(MouseEvent event) throws IOException {
        
        Button b=(Button) event.getSource();
        String fxid="";
        if (b.getId() != null){fxid=b.getId();}
        if(fxid.equals("bAddCategory") || fxid.equals("bBrowse") || fxid.equals("bBrowseUpdate") || fxid.equals("bUpdate") || fxid.equals("bDelete")  ){
        b.setStyle("-fx-background-color: #866BB4;");
        }else{
        b.setStyle("-fx-background-color: #98D08B;");}
       
                
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
    
    
    @FXML
    private void OnAddClicked(ActionEvent event) throws IOException {
        
        if((tfName.getText().isEmpty()) || (tfDescription.getText().isEmpty())){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid name and content !");
                    alertType.show();
          }else if (tfName.getText().toString().matches("[0-9]+")){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Name must be not only a number !");
                    alertType.show();                
	 }else {
              ServiceCategoryRating scr = new ServiceCategoryRating();
              CategoryRating cr = new CategoryRating(tfName.getText(), tfDescription.getText(),"");
              if(scr.getOneByName(tfName.getText())!= null)
              {Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Unvailebal Name !");
                    alertType.show(); }
              else{scr.ajouter(cr);
              tfName.setText("");
              tfDescription.setText("");
              Alert a = new Alert(Alert.AlertType.INFORMATION, "RateCategory added !", ButtonType.OK);
              a.showAndWait();}
              
        }
       
  }
    
     @FXML
    private void OnUpdateClicked(ActionEvent event) throws IOException {
        
        if((tfChooseCat.getText().isEmpty()) || (tfUpdateName.getText().isEmpty()) || (tfUpdateDesc.getText().isEmpty())){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a full data to update!");
                    alertType.show();
          }else if (tfUpdateName.getText().toString().matches("[0-9]+") || tfChooseCat.getText().toString().matches("[0-9]+")){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Name must be not only a number !");
                    alertType.show();                
	 }else {
              ServiceCategoryRating scr = new ServiceCategoryRating();
              if(scr.getOneByName(tfChooseCat.getText())== null)
              {Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Unvailebal Name !");
                    alertType.show(); }
              else{
                  
              CategoryRating cr = scr.getOneByName(tfChooseCat.getText());
              CategoryRating ncr = new CategoryRating(tfUpdateName.getText(), tfUpdateDesc.getText(), "Logo");
                if(tfUpdateName.getText().equals(cr.getName_Cat_Rating()) ){
                    scr.modifier(cr.getId_Cat_Rating(), ncr);
                    tfChooseCat.setText("");
                    tfUpdateName.setText("");
                    tfUpdateDesc.setText("");
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "RateCategory Updated !", ButtonType.OK);
                    a.showAndWait();
                    
                }else if(scr.getOneByName(tfUpdateName.getText())!= null)
                    {Alert alertType=new Alert(AlertType.ERROR);
		     alertType.setTitle("Error");
                     alertType.setHeaderText("Taken Name try an other !");
                     alertType.show(); }else{  
                scr.modifier(cr.getId_Cat_Rating(), ncr);
                tfChooseCat.setText("");
                tfUpdateName.setText("");
                tfUpdateDesc.setText("");
              Alert a = new Alert(Alert.AlertType.INFORMATION, "RateCategory added !", ButtonType.OK);
              a.showAndWait();}}
              
        }
       
  }
    
    @FXML
    private void OnDeleteClicked(ActionEvent event) throws IOException {
        
        if((tfChooseDelete.getText().isEmpty())){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid name and content !");
                    alertType.show();
          }else if (tfChooseDelete.getText().toString().matches("[0-9]+")){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Name must be not only a number !");
                    alertType.show();                
	 }
          else {
              ServiceCategoryRating scr = new ServiceCategoryRating();
              if(scr.getOneByName(tfChooseDelete.getText())== null)
              {Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("No such category with this name !");
                    alertType.show(); }
              else{
                CategoryRating cr = scr.getOneByName(tfChooseDelete.getText());
                scr.supprimer(cr.getId_Cat_Rating());
                tfChooseDelete.setText("");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "RateCategory deleted !", ButtonType.OK);
                a.showAndWait();}
              
        }
       
  }
        
       
                
}
    
    
    

