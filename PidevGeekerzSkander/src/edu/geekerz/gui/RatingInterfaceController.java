/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.geekerz.gui;

import edu.geekerz.entities.CategoryRating;
import edu.geekerz.entities.Rating;
import edu.geekerz.services.ServiceCategoryRating;
import edu.geekerz.services.ServiceRating;
import edu.geekerz.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RatingInterfaceController implements Initializable {
    
    public static boolean isValidDate(String input) {
        String format = "yyyy-MM-dd"; // format de date MySQL
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); // interdire les dates invalides, comme le 31 février
        try {
            Date date = sdf.parse(input);
            // vérifier si la date est égale à la chaîne d'entrée (pour prendre en compte les formats de date différents)
            return input.equals(sdf.format(date));
        } catch (ParseException e) {
            return false;
        }
    }
    
    public static boolean isUserValid(int id){
        Connection conn=DataSource.getInstance().getConn();
        try{
        String req = "SELECT * FROM `user` WHERE `id_user` = "+id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
        if(!rs.next()){
            return false;
        }
        else{return true;}
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    public static boolean isRatingValid(int id){
        Connection conn=DataSource.getInstance().getConn();
        try{
        String req = "SELECT * FROM `rating` WHERE `id_rating` = "+id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
        if(!rs.next()){
            return false;
        }
        else{return true;}
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    @FXML
    private Button bRatingCategory,bDelete,bUpdate;
    @FXML
    private Button bRating;
    @FXML
    private Button bScore;
    @FXML
    private TextField tfUserID,tfRatingValue,tfRatingDate,tfComment,tfCategoryName;
    @FXML
    private TextField tfChooseRating,tfUpdateUserId,tfUpdateValue,tfUpdateDate,tfUpdateComment,tfUpdateCategory,tfChooseDelete;
    
    @FXML
    private ListView<Rating> ratingView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceRating sr = new ServiceRating();
        List<Rating> myRatings = sr.getAll();
        ratingView.getItems().addAll(myRatings);
    }    
    
    
    @FXML
    private void Onmouseentred(MouseEvent event) throws IOException {
        Button b=(Button) event.getSource();
        String fxid="";
        if (b.getId() != null){fxid=b.getId();}
        if(fxid.equals("bAddRating")||fxid.equals("bUpdate") ||fxid.equals("bDelete") ){
        b.setStyle("-fx-background-color: #594778;");
        }else{b.setStyle("-fx-background-color: #32452E;");}
       
                
    }
    @FXML
    private void Onmouseexited(MouseEvent event) throws IOException {
        
        Button b=(Button) event.getSource();
        String fxid="";
        if (b.getId() != null){fxid=b.getId();}
        if(fxid.equals("bAddRating")||fxid.equals("bUpdate") ||fxid.equals("bDelete")){
        b.setStyle("-fx-background-color: #866BB4;");
        }else{
        b.setStyle("-fx-background-color: #98D08B;");}
       
                
    }
    
    
    
    @FXML
    private void OnRatingCategoryClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingCategoryInterface.fxml"));
        Parent root = loader.load();
        bRatingCategory.getScene().setRoot(root);
       
                
    }
      
     @FXML
    private void OnScoreClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoresInterface.fxml"));
        Parent root = loader.load();
        bScore.getScene().setRoot(root);
       
                
    }
    
    @FXML
    private void OnAddClicked(ActionEvent event) throws IOException {
        
        if((tfUserID.getText().isEmpty()) || (tfCategoryName.getText().isEmpty()) || (tfRatingValue.getText().isEmpty()) || (tfRatingDate.getText().isEmpty()) || (tfComment.getText().isEmpty()) ){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid data there are some missing !");
                    alertType.show();
          }else if (!(tfUserID.getText().toString().matches("[0-9]+")  && tfRatingValue.getText().toString().matches("[0-9]+"))){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("User ID and Rating Value must be  only a number !");
                    alertType.show();  
          }else if(!isUserValid(Integer.parseInt(tfUserID.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("User ID not found !");
                    alertType.show();
              
	 }else if(!(isValidDate(tfRatingDate.getText()))){
             Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Date format is incorrect pls enter in this format 'yyyy-MM-dd' with an available date !");
                    alertType.show();
         }else {
              ServiceCategoryRating scr = new ServiceCategoryRating();
              ServiceRating sr=new ServiceRating();
              Rating r=null;
              if(scr.getOneByName(tfCategoryName.getText())== null)
              {Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("No such Category named: "+tfCategoryName.getText()+" !");
                    alertType.show(); }
              else{
                  CategoryRating cr=scr.getOneByName(tfCategoryName.getText());
                  r=new Rating(Integer.parseInt(tfUserID.getText()), Double.parseDouble(tfRatingValue.getText()), tfRatingDate.getText(), tfComment.getText(), cr);
                  sr.ajouter(r);
                  tfUserID.setText("");
                  tfRatingValue.setText("");
                  tfRatingDate.setText("");
                  tfComment.setText("");
                  tfCategoryName.setText("");
              Alert a = new Alert(Alert.AlertType.INFORMATION, "Rate added !", ButtonType.OK);
              a.showAndWait();}
              
        }
       
  }
    
    
     @FXML
    private void OnUpdateClicked(ActionEvent event) throws IOException {
        
        if((tfChooseRating.getText().isEmpty()) || (tfUpdateUserId.getText().isEmpty()) || (tfUpdateValue.getText().isEmpty()) || (tfUpdateDate.getText().isEmpty()) || (tfUpdateComment.getText().isEmpty()) || (tfUpdateCategory.getText().isEmpty())){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a full data to update!");
                    alertType.show();
          }else if (!(tfChooseRating.getText().toString().matches("[0-9]+") || tfUpdateUserId.getText().toString().matches("[0-9]+") || tfUpdateValue.getText().toString().matches("[0-9]+"))){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Choose rating , User ID and Value must be only a number !");
                    alertType.show();                
	 }else if(!isRatingValid(Integer.parseInt(tfChooseRating.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Rating ID not found !");
                    alertType.show();
         }else if(!isUserValid(Integer.parseInt(tfUpdateUserId.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("User ID not found !");
                    alertType.show();
         }else if(!(isValidDate(tfUpdateDate.getText()))){
             Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Date format is incorrect pls enter in this format 'yyyy-MM-dd' with an available date !");
                    alertType.show();
         }else {
              ServiceCategoryRating scr = new ServiceCategoryRating();
              Rating nr=null;
              if(scr.getOneByName(tfUpdateCategory.getText())== null)
              {Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("No such Category named: "+tfCategoryName.getText()+" !");
                    alertType.show(); }
              else{
                  
              ServiceRating sr=new ServiceRating();
              Rating r=sr.getOneByID(Integer.parseInt(tfChooseRating.getText()));
              
                  CategoryRating cr=scr.getOneByName(tfUpdateCategory.getText());
                  nr=new Rating(Integer.parseInt(tfUpdateUserId.getText()), Double.parseDouble(tfUpdateValue.getText()), tfUpdateDate.getText(), tfUpdateComment.getText(), cr);
                  sr.modifier(r.getIdRating(), nr);
                  tfChooseRating.setText("");
                  tfUpdateUserId.setText("");
                  tfUpdateValue.setText("");
                  tfUpdateDate.setText("");
                  tfUpdateComment.setText("");
                  tfUpdateCategory.setText("");
              Alert a = new Alert(Alert.AlertType.INFORMATION, "Rate Updated !", ButtonType.OK);
              a.showAndWait();}
              
        }
       
  }
    
    @FXML
    private void OnDeleteClicked(ActionEvent event) throws IOException {
        
        if((tfChooseDelete.getText().isEmpty())){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("empty !");
                    alertType.show();
          }else if (!(tfChooseDelete.getText().toString().matches("[0-9]+"))){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Name must be only a number !");
                    alertType.show();                
	 }else if(!isRatingValid(Integer.parseInt(tfChooseDelete.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Rating ID not found !");
                    alertType.show();
         }
          else {
              ServiceRating sr = new ServiceRating();
              
                Rating r = sr.getOneByID(Integer.parseInt(tfChooseDelete.getText()));
                sr.supprimer(r.getIdRating());
                tfChooseDelete.setText("");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "RateCategory deleted !", ButtonType.OK);
                a.showAndWait();
              
        }
       
  }
    
}
