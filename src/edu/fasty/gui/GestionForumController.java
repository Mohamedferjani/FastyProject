/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.services.ServiceForum;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class GestionForumController implements Initializable {

    @FXML
    private TextField tfUser_Id;
    @FXML
    private TextField tfTitreForum;
    @FXML
    private Label labelErrorMsg;
    
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnAjoutClicked(ActionEvent event) {
   
            try{
                
          labelErrorMsg.setText("");
           if((tfUser_Id.getText().isEmpty() || tfUser_Id.getText() == null) && (tfTitreForum.getText().isEmpty() || tfTitreForum.getText() == null)){
                    labelErrorMsg.setText("Enter a valid id and name !");
          }else if (tfUser_Id.getText().isEmpty() || tfUser_Id.getText() == null){
                                  labelErrorMsg.setText("Enter a valid id !");
          }
              else if (tfTitreForum.getText().isEmpty() || tfTitreForum.getText() == null){
                                          labelErrorMsg.setText("Enter a valid name !");
                      } else{
          id = Integer.parseInt(tfUser_Id.getText());
          String nom = tfTitreForum.getText();
             Forum f = new Forum(id,nom);
               ServiceForum sf = new ServiceForum();
               sf.ajouterForum(f);

              }
          }catch(NumberFormatException e){
              if(!tfUser_Id.getText().isEmpty()){
                    labelErrorMsg.setText("id must be a number !");
              }else   if((tfUser_Id.getText().isEmpty() || tfUser_Id.getText() == null) && (tfTitreForum.getText().isEmpty() || tfTitreForum.getText() == null)){
                    labelErrorMsg.setText("Enter a valid id and name !");
          }else if (tfUser_Id.getText().isEmpty() || tfUser_Id.getText() == null){
                                  labelErrorMsg.setText("Enter a valid id !");
          }
              else if (tfTitreForum.getText().isEmpty() || tfTitreForum.getText() == null){
                                          labelErrorMsg.setText("Enter a valid name !");
                      }
        }
            catch(Exception e){
                System.err.println(e);
        }
        
    }

    @FXML
    private void btnViderClicked(ActionEvent event) {
        tfUser_Id.clear();
        tfTitreForum.clear();
        labelErrorMsg.setText("");
    }
    
}
