/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author MSI GAMING
 */
public class ModifierUserFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfCin;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private Button BtnModif;
    private String emailRegex = "\\w+\\.?\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}";
    private String checkEmailQuery = "SELECT COUNT(*) FROM user WHERE email=?";
    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private ImageView imageviewID;
    private String image;
    private int iduser;
    @FXML
    private Button BtnModif1;
        String imagePath;
    File selectedFile;
    @FXML
    private Button BtnModif2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Image img = new Image(image);
            imageviewID.setImage(img);
        });

    }
public void setID(int id) {
        this.iduser = id;
    }
    public void setNom(String message) {
        this.tfNom.setText(message);
    }

    public void setPrenom(String message) {
        this.tfPrenom.setText(message);
    }

    public void setTel(String message) {
        this.tfTel.setText(message);
    }

    public void setAdresse(String message) {
        this.tfAdresse.setText(message);
    }

    public void setCin(String message) {
        this.tfCin.setText(message);
    }

    public void setEmail(String message) {
        this.tfEmail.setText(message);
    }

    public void setMdp(String message) {
        this.tfMdp.setText(message);
    }

    public void setImage(String message) {
        this.image = message;
    }

    @FXML
    private void ModifierUser(ActionEvent event) {
        if ((tfNom.getText().isEmpty()) || (tfPrenom.getText().isEmpty()) || (tfTel.getText().isEmpty()) || (tfAdresse.getText().isEmpty()) || (tfCin.getText().isEmpty()) || (tfEmail.getText().isEmpty()) || (tfMdp.getText().isEmpty())) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Enter a valid content !");
            alertType.show();
        } else if (tfNom.getText().toString().matches("[0-9]+")) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom must be string not number !");
            alertType.show();
        } else if (tfPrenom.getText().toString().matches("[0-9]+")) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Prenom must be string not number !");
            alertType.show();
        } else if (tfAdresse.getText().toString().matches("[0-9]+")) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Adresse must be string not number !");
            alertType.show();
        } else if (tfCin.getText().toString().matches("[A-Z]+") || tfCin.getText().toString().matches("[a-z]+") || (tfCin.getText().length() != 8)) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Le numéro de CIN doit comporter exactement 8 chiffres.");
            alertType.show();
        } else if (tfTel.getText().toString().matches("[A-Z]+") || tfTel.getText().toString().matches("[a-z]+") || (tfTel.getText().length() != 8)) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Le numéro de téléphone doit comporter exactement 8 chiffres.");
            alertType.show();
        } else if (!tfEmail.getText().matches(emailRegex)) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("L'adresse email est invalide. Veuillez saisir une adresse email valide (ex: nom_utilisateur@domaine.com) !");
            alertType.show();
        } /*        else if (TestExist() != true) {
            Alert alertType = new Alert(AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("L'adresse email est invalide.");
            alertType.show();
        } */ else {
            Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
         alertType.setTitle("CONFIRMATION !");
         alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
          Optional<ButtonType> result  = alertType.showAndWait();
                        
 if(result.get() == ButtonType.OK){
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            int tel = Integer.parseInt(tfTel.getText());
            String adresse = tfAdresse.getText();
            int cin = Integer.parseInt(tfCin.getText());
            String email = tfEmail.getText();
            String mdp = tfMdp.getText();
            User s = new User(iduser,cin, tel, nom, prenom, adresse, email, mdp, 2, image);
            IServiceUser sc = new IServiceUser();
            sc.modifier(s);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFXML.fxml"));
            try {
                Parent root = loader.load();

                tfNom.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println("Error:" + ex.getMessage());
            }
        }
        }

    }

    @FXML
    private void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
         selectedFile = fileChooser.showOpenDialog(imageviewID.getScene().getWindow());
        if (selectedFile != null) {
            // The user selected an image file
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Path path = Paths.get(selectedFile.getAbsolutePath());
    Path relativePath = path.subpath(path.getNameCount() - 2, path.getNameCount()).normalize();
             imagePath = relativePath.toString().replace('\\', '/');
             Image imagee= new Image(imagePath);
             imageviewID.setImage(imagee);
             image = imagePath;
            // Add your code here to process the selected image file
        }
    }

    @FXML
    private void annulerClick(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFXML.fxml"));
            try {
                Parent root = loader.load();

                tfNom.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println("Error:" + ex.getMessage());
            }
    }

}
