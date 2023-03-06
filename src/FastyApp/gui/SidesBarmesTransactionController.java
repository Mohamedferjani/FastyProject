/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.gui;

import FastyApp.entities.Transaction;
import FastyApp.services.ServiceProduit;
import FastyApp.services.ServiceTransaction;
import FastyApp.services.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ISSAM
 */
public class SidesBarmesTransactionController implements Initializable {

    @FXML
    private TextField getNom;

    @FXML
    private ListView<Transaction> listViewNom;
    @FXML
    private ListView<Transaction> listViewRecepteur;
    @FXML
    private ListView<Transaction> listViewExepediteur;
    @FXML
    private ListView<Transaction> listViewMontant;

    ServiceUser us = new ServiceUser();
    ServiceTransaction ts = new ServiceTransaction();
    ServiceProduit ps = new ServiceProduit();

    ObservableList<Transaction> transactionList = ts.getAllTransaction();
    @FXML
    private ImageView image;
    @FXML
    private Label lb_totalTransac;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initList();
        File fileLogo = new File("C:/Users/ISSAM/Desktop/FastyProject/Fasty/Fasty/src/FastyApp/ImageUsed/logo.png");
        Image logoI = new Image(fileLogo.toURI().toString());
        image.setImage(logoI);
        
        setLb_totalTransac(ts.getAllTransaction().stream()
                                  .mapToDouble(t -> ps.getMontantById(t.getId_produit()))
                                  .sum());

    }

    public void setLb_totalTransac(double total) {
        this.lb_totalTransac.setText(String.valueOf(total)+ " DT");
    }

    @FXML
    private void getOneByName(ActionEvent event) {
        String nom = getNom.getText();
        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez remplir Le Champ :) ");
            alert.show(); //champ vide alerte
        } else if (!nom.matches("^[a-zA-Z]*$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez entré le nom de la transaction :) ");
            alert.show(); //entré n'est pas string
        } else {
            Optional<Transaction> matchingTransaction = transactionList.stream() //maching transaction t3abi valeur li 3malt 3lih recherche
                    .filter(transaction -> transaction.getNomTransaction().toLowerCase().equals(nom.toLowerCase()))
                    .findFirst();
            //to lower case bech yefhem maj w miniscule 

            if (matchingTransaction.isPresent()) {
                transactionList.clear();
                Transaction transaction = matchingTransaction.get();
                transactionList.add(transaction);
                listViewNom.setItems(transactionList);
                listViewExepediteur.setItems(transactionList);
                listViewMontant.setItems(transactionList);
                listViewRecepteur.setItems(transactionList);
                transactionList = ts.getAllTransaction();
                setLb_totalTransac(ps.getMontantById(transaction.getId_produit()));

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Y'a pas de transaction avec ce nom :( ");
                alert.show();
            }//transaction introuvable nest pas une transaction avec ce nom 
        }
    }
// n3abi beha f list view w n9asemfeha setcellfactory t5alini no3reth l5edma f listview param texcuti 3la kol element mel liste 

    private void initList() {
        listViewNom.setCellFactory(param -> new ListCell<Transaction>() {
            protected void updateItem(Transaction trans, boolean empty) {
                super.updateItem(trans, empty);

                if (empty || trans == null) {
                    setText(null);
                } else {
                    setText(trans.getNomTransaction());
                }
            }
        });

        listViewRecepteur.setCellFactory(param -> new ListCell<Transaction>() {
            protected void updateItem(Transaction trans, boolean empty) {
                super.updateItem(trans, empty);

                if (empty || trans == null) {
                    setText(null);
                } else {
                    setText(us.getUserFullNameById(trans.getId_recepteur()));
                }
            }
        });

        listViewExepediteur.setCellFactory(param -> new ListCell<Transaction>() {
            protected void updateItem(Transaction trans, boolean empty) {
                super.updateItem(trans, empty);

                if (empty || trans == null) {
                    setText(null);
                } else {
                    setText(us.getUserFullNameById(trans.getId_expiditeur()));
                }
            }
        });

        listViewMontant.setCellFactory(param -> new ListCell<Transaction>() {
            protected void updateItem(Transaction trans, boolean empty) {
                super.updateItem(trans, empty);

                if (empty || trans == null) {
                    setText(null);
                } else {
                    setText(ps.getMontantById(trans.getId_produit()) + " DT");
                }
            }
        });
    }

    @FXML
    private void getAll(ActionEvent event) {
        setListItemsFromDatabase();
        
    }

    private void setListItemsFromDatabase() {
        transactionList = ts.getAllTransaction();
        listViewNom.setItems(transactionList);
        listViewExepediteur.setItems(transactionList);
        listViewMontant.setItems(transactionList);
        listViewRecepteur.setItems(transactionList);
        setLb_totalTransac(ts.getAllTransaction().stream()
                                  .mapToDouble(t -> ps.getMontantById(t.getId_produit()))
                                  .sum());
    }

    @FXML
    private void DeleteOne(ActionEvent event) {
        Transaction t = listViewNom.getSelectionModel().getSelectedItem();
        if (t != null) {
            if (ts.supprimerTransaction(t.getId_transaction())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("La transaction a été supprimée :) ");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
                    setListItemsFromDatabase();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("La transaction n'a pas été supprimée");
                alert.showAndWait(); //si la transaction n'est pas supprimer
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(" Veuillez selectionnez une transaction :) ");
            alert.show(); //en cas il ne selectionne pas aucun transaction
        }
    }

    @FXML
    private void GoMesFacture(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesFacture.fxml"));
        try {
            Parent root = loader.load();
            listViewRecepteur.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
