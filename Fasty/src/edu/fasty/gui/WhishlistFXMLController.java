/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Whishlist;
import edu.fasty.services.IServiceWhishlist;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MSI GAMING
 */
public class WhishlistFXMLController implements Initializable {

    @FXML
    private ListView<Whishlist> LVWishlist;
    @FXML
    private Button BtnAfficheWish;
    @FXML
    private TextField tfAfficheWish;
    @FXML
    private ImageView ImgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficheWish(ActionEvent event) {
        IServiceWhishlist w = new IServiceWhishlist();
        List<Whishlist> wishliist = w.getAll();
        LVWishlist.getItems().addAll(wishliist);
        
        
    }

    @FXML
    private void Retour(ActionEvent event) {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUserFXML.fxml"));
        try {
            Parent root = loader.load();

            LVWishlist.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }
    
    
}
