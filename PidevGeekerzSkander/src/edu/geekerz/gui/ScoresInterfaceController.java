/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.geekerz.gui;

import edu.geekerz.entities.Score;
import static edu.geekerz.gui.RatingInterfaceController.isUserValid;
import static edu.geekerz.gui.RatingInterfaceController.isValidDate;
import edu.geekerz.services.ServiceScore;
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
public class ScoresInterfaceController implements Initializable {
    
    
    
    public static boolean isScoreValid(int id){
        Connection conn=DataSource.getInstance().getConn();
        try{
        String req = "SELECT * FROM `score` WHERE `id_score` = "+id;
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
    private Button bRatingCategory,bUpdate,bDelete;
    @FXML
    private Button bRating;
    @FXML
    private Button bScore;
    @FXML
    private TextField tfUserID,tfScoreValue,tfFeeling,tfDate,tfChooseScore,tfUpdateUserId,tfUpdateValue,tfUpdateFeelings,tfUpdateDate,tfChooseDelete;
    
    @FXML
    private ListView<Score> scoreView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceScore ss = new ServiceScore();
        List<Score> myScores = ss.getAll();
        scoreView.getItems().addAll(myScores);
    }    
    
    
    @FXML
    private void Onmouseentred(MouseEvent event) throws IOException {
        Button b=(Button) event.getSource();
        String fxid="";
        if (b.getId() != null){fxid=b.getId();}
        if(fxid.equals("bAddScore")||fxid.equals("bUpdate") ||fxid.equals("bDelete")){
        b.setStyle("-fx-background-color: #594778;");
        }else{b.setStyle("-fx-background-color: #32452E;");}
       
                
    }
    @FXML
    private void Onmouseexited(MouseEvent event) throws IOException {
        
        Button b=(Button) event.getSource();
        String fxid="";
        if (b.getId() != null){fxid=b.getId();}
        if(fxid.equals("bAddScore")||fxid.equals("bUpdate") ||fxid.equals("bDelete")){
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
    private void OnRatingClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingInterface.fxml"));
        Parent root = loader.load();
        bRating.getScene().setRoot(root);
       
                
    }
    
    @FXML
    private void OnAddClicked(ActionEvent event) throws IOException {
        
        if((tfUserID.getText().isEmpty()) || (tfScoreValue.getText().isEmpty()) || (tfFeeling.getText().isEmpty()) || (tfDate.getText().isEmpty())){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid data there are some missing !");
                    alertType.show();
          }else if (!(tfUserID.getText().toString().matches("[0-9]+") && tfScoreValue.getText().toString().matches("[0-9]+"))){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("User ID and Score Value must be  only a number !");
                    alertType.show();                
	 }else if(!isUserValid(Integer.parseInt(tfUserID.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("User ID not found !");
                    alertType.show();
              
	 }else if(!(isValidDate(tfDate.getText()))){
             Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Date format is incorrect pls enter in this format 'yyyy-MM-dd' with an available date !");
                    alertType.show();
         }else {
              ServiceScore sc = new ServiceScore();
              Score s = new Score(Integer.parseInt(tfUserID.getText()), Double.parseDouble(tfScoreValue.getText()), tfFeeling.getText(), tfDate.getText());
                sc.ajouter(s);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Score added !", ButtonType.OK);
                a.showAndWait();
              
        }
       
  }
    
    @FXML
    private void OnUpdateClicked(ActionEvent event) throws IOException {
        
        if((tfChooseScore.getText().isEmpty()) || (tfUpdateUserId.getText().isEmpty()) || (tfUpdateValue.getText().isEmpty()) || (tfUpdateDate.getText().isEmpty()) || (tfUpdateFeelings.getText().isEmpty()) ){
                Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a full data to update!");
                    alertType.show();
          }else if (!(tfChooseScore.getText().toString().matches("[0-9]+") || tfUpdateUserId.getText().toString().matches("[0-9]+") || tfUpdateValue.getText().toString().matches("[0-9]+"))){
		Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Choose rating , User ID and Value must be only a number !");
                    alertType.show();                
	 }else if(!isScoreValid(Integer.parseInt(tfChooseScore.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Score ID not found !");
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
              
              
                  
              ServiceScore ss=new ServiceScore();
              Score s=ss.getOneByID(Integer.parseInt(tfChooseScore.getText()));
             
                  Score ns=new Score(Integer.parseInt(tfUpdateUserId.getText()), Double.parseDouble(tfUpdateValue.getText()), tfUpdateFeelings.getText(), tfUpdateDate.getText());
                  ss.modifier(s.getIdScore(), ns);
                  tfChooseScore.setText("");
                  tfUpdateUserId.setText("");
                  tfUpdateValue.setText("");
                  tfUpdateDate.setText("");
                  tfUpdateFeelings.setText("");
                 
              Alert a = new Alert(Alert.AlertType.INFORMATION, "Score Updated !", ButtonType.OK);
              a.showAndWait();
              
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
	 }else if(!isScoreValid(Integer.parseInt(tfChooseDelete.getText()))){
              Alert alertType=new Alert(AlertType.ERROR);
		alertType.setTitle("Error");
                    alertType.setHeaderText("Score ID not found !");
                    alertType.show();
         }
          else {
              ServiceScore ss = new ServiceScore();
              
                Score s = ss.getOneByID(Integer.parseInt(tfChooseDelete.getText()));
                ss.supprimer(s.getIdScore());
                tfChooseDelete.setText("");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Score deleted !", ButtonType.OK);
                a.showAndWait();
              
        }
       
  }
    
    
}
