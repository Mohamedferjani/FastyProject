/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GAMING
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Button inscrire_user;
    @FXML
    private Button connect;
    @FXML
    private TextField donneradresse;
    @FXML
    private PasswordField password;
    
    private String emailRegex = "\\w+\\.?\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}";

    /**
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void inscrire(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("inscriptionUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }
//    private void admin(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
//    Scene scene = new Scene(root);
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    stage.setScene(scene);
//    stage.show();
//    }


    @FXML
    private void seconnecter(ActionEvent event)throws SQLException, IOException {
        
   Connection cnx = DataSource.getInstance().getCnx();
    String email = donneradresse.getText();
    String mdp = password.getText();
    // Récupérer l'adresse email de l'utilisateur


// Rechercher l'utilisateur par email
IServiceUser userService = new IServiceUser();


    if (email.isEmpty() || mdp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir l'email et le mot de passe.");
        alert.show();
        
    }else if(!email.matches(emailRegex)){
        Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("L'adresse email est invalide. Veuillez saisir une adresse email valide (ex: nom_utilisateur@domaine.com) !");
            alertType.show();
    } else{

User user = userService.RechercherUserparEmailMdp(email,mdp);
       if (user != null) {
    int idRole = user.getId_role();
    if (idRole == 1) {
        // Admin user, load admin.fxml
        Parent root = FXMLLoader.load(getClass().getResource("GestionUserFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else if (idRole == 2) {
        // User with role 2, load connexionutil.fxml
        User u = new User();
        u.getId_user();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUserFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Email ou mot de passe invalide !.");
        alert.show();
       }
//        resultSet.close();
//        statement.close();
  
    }
    
    
    }


    }
    

    

