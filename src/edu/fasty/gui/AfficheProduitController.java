/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Produit;
import edu.fasty.entities.User;
import edu.fasty.entities.Wishlist;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.MyListener;
import edu.fasty.services.ServiceWishlist;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private Button wishlistID;
    @FXML
    private Label valeur;
    @FXML
    private Button consulterID;
    private int idProduit;
            private Preferences prefs = Preferences.userNodeForPackage(LoginFXMLController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
        int currentuserid = prefs.getInt("iduser", 0);
consulterID.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt; -fx-background-radius: 20;");
wishlistID.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14pt; -fx-background-radius: 20; -fx-border-color: #2196F3; -fx-border-width: 2px; -fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 0);");

        if(currentuserid == p.getId_user().getId_user() ){
            consulterID.setVisible(false);
        }
        });
        
        // TODO
    }    
    public void setData(Produit p,MyListener myListener){
        this.p = p;
         this.myListener=myListener;
        nom.setText(p.getNom_produit());
        valeur.setText(p.getValeur()+" DT");
        description.setText(p.getDescription());
        description.setWrapText(true);
        idProduit = p.getId_produit();
        description.setMaxWidth(200);

       // String fileName = f.toURI().toString().substring(f.toURI().toString().indexOf("/"));
         Image img1 = new Image(p.getImg_produit());
         imageview.setImage(img1);
        
    }
    @FXML
    private void selectedbp(MouseEvent event) {
        myListener.onClickListener(p);
    }

    @FXML
    private void Consulter(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("TransactionFXML.fxml"));
        Parent root = loader.load();
        GestionFactureFXMLController mfc = loader.getController();
        mfc.setNom(nom.getText());
        mfc.setDescription(description.getText());
        mfc.setValeur(valeur.getText());
        mfc.setImage(p.getImg_produit());
        mfc.setUser(p.getId_user());
        mfc.setCurrentUserID(prefs.getInt("iduser",0));
        mfc.setIDProduit(p.getId_produit());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
       
    }
     @FXML
    void WishlistClicked(ActionEvent event) {
        ServiceWishlist sw = new ServiceWishlist();
        IServiceUser su = new IServiceUser();
        User u = su.getOneById(prefs.getInt("iduser",0));
        ArrayList<Produit> listeproduit = new ArrayList();
        listeproduit.add(p);
        Wishlist w = new Wishlist(u,listeproduit);
        sw.ajouter(w);
         System.err.println("aaaaaaaaaaaaaaaaaaaaaa");
    }

    
}
