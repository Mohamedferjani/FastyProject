/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package FastyApp.gui;

import FastyApp.entities.Facture;
import FastyApp.entities.Produit;
import FastyApp.entities.Transaction;
import FastyApp.entities.User;
import FastyApp.services.ServiceFacture;
import FastyApp.services.ServiceTransaction;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    User u = new User(1, "issam", "guezmir", "address", "Guezmir.issam@esprit.tn", "4845", "58257761", "", "");
    String cuurentuserEmail = "fastyappesprit@gmail.com";
    String cuurentuserPwd = "emijdbxxavbajbng";
    int cuurentuserId = 2; //id cheri

    // String cuurentuserEmail = "Guezmir.issam@esprit.tn";
    //  String cuurentuserPwd = "vouimiebvgadpmbw";
    Produit p = new Produit(653,"Sac de cochage ", "Sac de cochage en cuire importé de la France ", "", 150.0, 1, 25); // 1 id moula produit

    @FXML
    private Button btnEnvoiyer;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
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
                new ServiceFacture().sendEmail(cuurentuserEmail, cuurentuserPwd, u.getEmail(), subjectField.getText(), bodyTextArea.getText());

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
        alert.setContentText("Le numero de " + u.getNom() + " est " + u.getNumTel());
        alert.showAndWait();
    }

    @FXML
    private void acheterLeProduit(ActionEvent event) {
        Facture f = new Facture(String.valueOf(p.getValeur()), p.getId_user(), cuurentuserId, p.getId_produit());
        new ServiceFacture().ajouterFacture(f);
        Transaction t = new Transaction(p.getId_user(), cuurentuserId, p.getId_produit(), "trans "+p.getNom_produit());
        new ServiceTransaction().ajouterTransaction(t);
    }

}
