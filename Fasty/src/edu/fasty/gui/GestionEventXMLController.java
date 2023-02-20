/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class GestionEventXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextArea tfdescription;
    @FXML
    private CheckBox chbox;
    @FXML
    private Button btn6;
    @FXML
    private TextField txtfieldtitre;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
        private void ajouetrEvent(ActionEvent event) throws IOException {
        if (txtfieldtitre.getText().isEmpty() || tfdescription.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nom ou prenom invalide(s)", ButtonType.OK);
            a.showAndWait();
        } else {
            try {
                       LocalDate myObj = LocalDate.now();

                ServiceEvent sp = new ServiceEvent();
                Event ne= new Event(myObj,txtfieldtitre.getText(),"hi",true);

                Personne p = new Personne(tfNom.getText(), tfPrenom.getText());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Personne added !", ButtonType.OK);
                a.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonne.fxml"));
                Parent root = loader.load();
                tfNom.getScene().setRoot(root);
                
                AfficherPersonneController apc = loader.getController();
                apc.setNom(tfNom.getText());
                apc.setPrenom(tfPrenom.getText());
                
            } catch (SQLException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }

    }

    @FXML
    private void GestionEventXML(MouseEvent event) {
    }

    @FXML
    private void myEvents(MouseEvent event) {
    }

}

    @FXML
    private void GestionEventXML(MouseEvent event) {
    bp.setCenter(ap);
    }

    @FXML
    private void myEvents(MouseEvent event) {
    loadPage("myEvents");
    }
    
    private void loadPage(String page){
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GestionEventXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
        
}
}