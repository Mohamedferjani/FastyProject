/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.gui;

import FastyApp.entities.Facture;
import FastyApp.entities.Transaction;
import FastyApp.services.ServiceFacture;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ISSAM
 */
public class MesFactureController implements Initializable {

    @FXML
    private ListView<String> listViewnumf;

    /**
     * Initializes the controller class.
     */
    ServiceFacture sf = new ServiceFacture();

    @FXML
    private ImageView image;
    @FXML
    private ImageView generatePDF;
    @FXML
    private Label lb_nbFacture;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File fileLogo = new File("C:/Users/ISSAM/Desktop/FastyProject/Fasty/Fasty/src/FastyApp/ImageUsed/logo.png");
        Image logoI = new Image(fileLogo.toURI().toString());
        image.setImage(logoI);
        File filepdf = new File("C:/Users/ISSAM/Desktop/FastyProject/Fasty/Fasty/src/FastyApp/ImageUsed/pdf.png");

        Image pdfI = new Image(filepdf.toURI().toString());
        generatePDF.setImage(pdfI);
        List<Facture> Fac = sf.getAllFacture();
        for (Facture f : Fac) {
            listViewnumf.getItems().add(Integer.toString(f.getId_facture()));
        }

        listViewnumf.setCellFactory(param -> new ListCell<String>() {
            protected void updateItem(String Fac, boolean empty) {
                super.updateItem(Fac, empty);

                if (empty || Fac == null) {
                    // setText(null);
                } else {
                    setText(Fac);
                    //System.out.println(Fac);
                }
            }
        });
        setLb_nbFacture();
    }

    public void setLb_nbFacture() {
        this.lb_nbFacture.setText("Vous avez " + sf.getAllFacture().size() + " factures");
    }

    @FXML
    private void retourt(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SidesBarmesTransaction.fxml"));
        try {
            Parent root = loader.load();
            listViewnumf.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }

    @FXML
    private void generatePDF(MouseEvent event) {

        if (listViewnumf.getSelectionModel().getSelectedItem() != null) {
            Facture f = sf.getOneByIdF(Integer.parseInt(listViewnumf.getSelectionModel().getSelectedItem()));

            sf.exporterPDF(f);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(" Veuillez selectionnez une facture :) ");
            alert.show(); //en cas il ne selectionne pas aucun transaction
        }
    }
}
//allDone