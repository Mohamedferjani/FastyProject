/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Whishlist;
import edu.fasty.services.IServiceWhishlist;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        int id = Integer.parseInt(tfAfficheWish.getText());
        w.getOneById(id);
        
        
    }
    
    
}
