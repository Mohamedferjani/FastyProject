/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Produit;
import edu.fasty.services.MyListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class AfficheProduitController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label image;
    @FXML
    private Label lreact;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
            private Produit p;
    private MyListener myListener;
    @FXML
    private Label description;
    @FXML
    private Label valeur;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Produit p,MyListener myListener){
        this.p = p;
         this.myListener=myListener;
        nom.setText(p.getNom_produit());
        valeur.setText(p.getValeur());
        description.setText(p.getDescription());
        File f = new File("" + p.getImg_produit());
         Image img1 = new Image(f.toURI().toString());
         imageview.setImage(img1);
        
    }
    @FXML
    private void selectedbp(MouseEvent event) {
        myListener.onClickListener(p);
    }
    
}
