/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Facture;
import edu.fasty.entities.Produit;
import edu.fasty.entities.Transaction;
import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceFacture;
import edu.fasty.services.ServiceTransaction;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ISSAM
 */
public class GestionFactureFXMLController implements Initializable {


    // moule el produit
    //User u = new User(1, "issam", "guezmir", "address", "Guezmir.issam@esprit.tn", "4845", "555555", "", "");
    String cuurentuserEmail = "fastyappesprit@gmail.com";
    String cuurentuserPwd = "emijdbxxavbajbng";
    int cuurentuserId; //id cheri

    // String cuurentuserEmail = "Guezmir.issam@esprit.tn";
    //  String cuurentuserPwd = "vouimiebvgadpmbw";
   // Produit p = new Produit(653,"Sac de cochage ", "Sac de cochage en cuire importé de la France ", "", 150.0, 1, 25); // 1 id moula produit

    @FXML
    private Button btnEnvoiyer;
        @FXML
    private Label NomProdID;

    @FXML
    private Label valeurProdID1;

    @FXML
    private Label DescProdID1;
        @FXML
    private Button AfficherNumID;

    @FXML
    private Button AcheteID;
        @FXML
    private ImageView imageviewID;
        
            @FXML
    private Button Retour;

private User usr = null;
int idProduit;
    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        AcheteID.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16pt;");
btnEnvoiyer.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 16pt; -fx-background-radius: 25; -fx-border-color: #FF9800; -fx-border-radius: 25; -fx-border-width: 2;");

AfficherNumID.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14pt; -fx-background-radius: 20;");

        // TODO
    }

    @FXML
    private void sendEmail(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Email Dialog");
        VBox vbox = new VBox(10);

        Label subjectLabel = new Label("Subject:");
        TextField subjectField = new TextField();

        Label bodyLabel = new Label("Email body:");
        TextArea bodyTextArea = new TextArea();
        bodyTextArea.setPrefColumnCount(20);
        bodyTextArea.setPrefRowCount(10);

        Button sendButton = new Button("Send");
        Button cancelButton = new Button("Cancel");
        HBox hbox = new HBox(10, sendButton, cancelButton);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        vbox.getChildren().addAll(subjectLabel, subjectField, bodyLabel, bodyTextArea, hbox);
        vbox.setPadding(new Insets(10));

        sendButton.setOnAction(events -> {
            if (subjectField.getText().isEmpty() || bodyTextArea.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Subject and body cannot be empty!");
                alert.showAndWait();
            } else {
                new ServiceFacture().sendEmail(cuurentuserEmail, cuurentuserPwd, usr.getEmail(), subjectField.getText(), bodyTextArea.getText());

                dialog.close();
            }
        });

        cancelButton.setOnAction(events -> {
            dialog.close();
        });

        Scene dialogScene = new Scene(vbox, 400, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    @FXML
    private void afficherNumero(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Numero de tel");
        alert.setHeaderText(null);
        alert.setContentText("Le numero de " +usr.getPrenom()+ " "+usr.getNom() + " est " + usr.getNum_tel());
        alert.showAndWait();
    }

    @FXML
    private void acheterLeProduit(ActionEvent event) {
        IServiceUser su = new IServiceUser();
        User user2 = su.getOneById(cuurentuserId);
       Facture f = new Facture(valeurProdID1.getText(), usr, user2, idProduit);
        Transaction t = new Transaction(usr, user2, idProduit,NomProdID.getText()); 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir acheter ce produit ?");
         Optional<ButtonType> result  = alert.showAndWait();
                        
 if(result.get() == ButtonType.OK){
     
try {
    new ServiceFacture().ajouterFacture(f);
    new ServiceTransaction().ajouterTransaction(t);
            Parent root = FXMLLoader.load(getClass().getResource("ClientProduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    }
    
        @FXML
    void Retour(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("ClientProduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
    public void setNom(String message) {
        this.NomProdID.setText(message);
    }
    public void setValeur(String message) {
        this.valeurProdID1.setText(message);
    }
    public void setDescription(String message) {
        this.DescProdID1.setText(message);
        DescProdID1.setWrapText(true);
    }
    public void setImage(String message) {
        Image image = new Image(message);
        this.imageviewID.setImage(image);
    }
    public void setUser(User user) {
        this.usr = user;
    }
    public void setCurrentUserID(int id){
    this.cuurentuserId = id;
    }
    public void setIDProduit(int id){
    this.idProduit = id;
    }

}
