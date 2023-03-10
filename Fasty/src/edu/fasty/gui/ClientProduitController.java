/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Categorie;
import edu.fasty.entities.Produit;
import edu.fasty.services.MyListener;
import edu.fasty.services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class ClientProduitController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Label idgetter;

    /**
     * Initializes the controller class.
     */
    ServiceProduit Sp=new ServiceProduit();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(ClientProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
        
   MyListener myListener;
    public void selectedProduit(Produit p){
        idgetter.setText(String.valueOf(p.getId_produit()));
         System.out.println(idgetter.getText());
        
    }
     public void refresh() throws SQLException{
    
          
         
         List<Produit> produits = Sp.afficher();
          
         
         if(produits.size() > 0){
             
          selectedProduit(produits.get(0));
          myListener = new MyListener() {
              @Override
              public void onClickListener(Produit p) {
                  selectedProduit(p);
              }


              @Override
              public void onClickListener2(Categorie c) {
                  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
              }
              
         };
                  }
         
        int column = 0;
        int row = 1;
        
        try {
            for (int i = 0; i < produits.size(); i++) {
                
                
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("edu/fasty/gui/AfficheProduit.fxml"));
            
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheProduitController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
