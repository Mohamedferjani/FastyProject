/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Event;
import edu.fasty.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class ModifyEventController implements Initializable {

    @FXML
    private TextField txtfieldtitre;
    @FXML
    private TextArea txtareadescr;
    
    private String value;

    /**
     * Initializes the controller class.
     * @param url
     */

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.print(value);
        //txtareadescr.setText(""+value);

// TODO
    }    
       
       private void modifierEvent(ActionEvent event) {

              ServiceEvent se = new ServiceEvent();
              //se.getOnbyTitre(value);
 Event Ev=new Event(txtfieldtitre.getText(),txtareadescr.getText());
             se.modifier(Ev);

//System.out.println(value);
//System.out.println(value);
   // System.out.println(txtfieldtitre.getText());  
               
       }
//
    public void setValue(String value) {
 System.out.println("String parameter: " + value);
    this.value = value;
    txtareadescr.setText(value);    }

    public String getValue() {
        return value;
    }
   
 
    
}
