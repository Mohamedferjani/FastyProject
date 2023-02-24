/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Produit;
import edu.fasty.services.ServiceProduit;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class GestionmesproduitsController implements Initializable {
ServiceProduit sp = new ServiceProduit();
ObservableList<Produit> produitList = (ObservableList<Produit>) sp.getAll();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void getOneByName(ActionEvent event) {
        String nom = getNom_produit.getText();
        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez remplir Le Champ 🙂 ");
            alert.show(); //champ vide alerte
        } else if (!nom.matches("^[a-zA-Z]*$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez entré le nom de produit 🙂 ");
            alert.show(); //entré n'est pas string
        } else {
            Optional<Produit> matchingTransaction = produitList.stream() //maching transaction t3abi valeur li 3malt 3lih recherche
                    .filter(transaction -> transaction.getNom_produit().toLowerCase().equals(nom.toLowerCase()))
                    .findFirst();
            //to lower case bech yefhem maj w miniscule 

            if (matchingTransaction.isPresent()) {
                produitList.clear();
                Produit produit = matchingProduit.get();
                produitList.add(produit);
                listViewNom_produit.setItems(produitList);
                listViewvaleur.setItems(produitList);
                listViewDesription.setItems(produitList);
                produitList = ts.getAllTransaction();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Y'a pas de produit avec ce nom 🙁 ");
                alert.show();
            }//transaction introuvable nest pas une transaction avec ce nom 
        }
    }
// n3abi beha f list view w n9asemfeha setcellfactory t5alini no3reth l5edma f listview param texcuti 3la kol element mel liste 
    private void initList() {
        listViewNom_produit.setCellFactory(param -> new ListCell<Produit>() {
            protected void updateItem(Produit trans, boolean empty) {
                super.updateItem(trans, empty);

                if (empty || trans == null) {
                    setText(null);
                } else {
                    setText(trans.getNom_produit());
                }
            }
        });

        listViewDescription.setCellFactory(param -> new ListCell<Produit>() {
            protected void updateItem(Produit trans, boolean empty) {
                super.updateItem(trans, empty);

                if (empty || trans == null) {
                    setText(null);
                } else {
                    setText(us.getUserFullNameById(trans.getDecription()));
                }
            }
        });

       

        listViewvaleur.setCellFactory(param -> new ListCell<Produit>() {
            protected void updateItem(Produit trans, boolean empty) {
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
        produitList = ts.getAllTransaction();
        listViewNom_produit.setItems(produitList);
        listViewvaleur.setItems(produitList);
        listViewDescription.setItems(produitList);
    }

    @FXML
    private void DeleteOne(ActionEvent event) {
        Produit t = listViewNom_produit.getSelectionModel().getSelectedItem();
        if (t != null) {
            if (ts.supprimerTransaction(t.getId_produit())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("La transaction a été supprimée 🙂 ");
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
                alert.setContentText("La produit n'a pas été supprimée");
                alert.showAndWait(); //si la transaction n'est pas supprimer
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(" Veuillez selectionnez une produit 🙂 ");
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
    }
    }
}
