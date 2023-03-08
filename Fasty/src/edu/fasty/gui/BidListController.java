/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Bid;
import edu.fasty.services.ServiceBid;
import edu.fasty.utils.Myconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class BidListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TableColumn<Bid, Integer> IDBidTab;
    @FXML
    private TableColumn<Bid, Integer> IDEventTab;
    @FXML
    private TableColumn<Bid, Integer> IDProduitTab;
    @FXML
    private TableColumn<Bid, Integer> StartingPriceTab;
    @FXML
    private Button Retour;
    @FXML
    private TextField TFSearch;
    @FXML
    private ComboBox<Integer> cb_UserEvent;
    ObservableList<Integer> optionsEvent=FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> cb_UserProduit;
    ObservableList<Integer> optionsProduit=FXCollections.observableArrayList();
    @FXML
    private TableView<Bid> BidTab;

    /**
     * Initializes the controller class.
     */
    ServiceBid sb =new ServiceBid();
    int id;
    Bid b;
    ObservableList<Bid> data=FXCollections.observableArrayList();
    @FXML
    private TextField StartingPricetf;
    @FXML
    private ComboBox<Integer> cb_User;
    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Bid, Integer> IDUserTab;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_delete1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        refreshlist();
        fillcomboEvent();
        fillcomboProduit();
        fillcomboUser();

        try {
            
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
            public void fillcomboProduit(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from produit";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsProduit.add(rs.getInt("id_produit"));
            }
            cb_UserProduit.setItems(optionsProduit);
        } catch (SQLException ex) {

        }
    } 
    public void fillcomboEvent(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from evenement";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsEvent.add(rs.getInt("id_evenement"));
            }
            cb_UserEvent.setItems(optionsEvent);
        } catch (SQLException ex) {

        }
    } 
        public void fillcomboUser(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from user";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsUser.add(rs.getInt("id_user"));
            }
            cb_User.setItems(optionsUser);
        } catch (SQLException ex) {

        }
    } 
                public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(sb.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDBidTab.setVisible(false);
        IDBidTab.setCellValueFactory(new PropertyValueFactory<>("id_bid"));
        IDEventTab.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        IDProduitTab.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        IDUserTab.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        StartingPriceTab.setCellValueFactory(new PropertyValueFactory<>("starting_price"));
        BidTab.setItems(data);
    }
    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sb.afficher());
            //System.out.println(data);
            FilteredList<Bid> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_bid()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getId_event()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    if (String.valueOf(p.getId_produit()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(String.valueOf(p.getStarting_price()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Bid> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(BidTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		BidTab.setItems(sortedData);
             
        }    

    @FXML
    private void AjouterBid(ActionEvent event) {
        String erreurs="";
        if(StartingPricetf.getText().trim().isEmpty()){
            erreurs+="Starting Price vide\n";
        }
        
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Bid");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        Bid b = new Bid(cb_UserEvent.getValue(),
                                                 cb_UserProduit.getValue(),
                                                 Integer.parseInt(StartingPricetf.getText()),
                                                 cb_User.getValue());
                  sb.ajouter(b);    
                  
                   refreshlist();   }
    }
    @FXML
    private void EditBid(ActionEvent event) throws SQLException {
        Bid b = new Bid(cb_UserEvent.getValue(),
                                                 cb_UserProduit.getValue(),
                                                 Integer.parseInt(StartingPricetf.getText()),
                                                 cb_User.getValue());
        sb.modifier(id, b);
        refreshlist(); 
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Bid b=BidTab.getSelectionModel().getSelectedItem();
        id=b.getId_bid();
        cb_UserEvent.setValue(b.getId_event());
        cb_UserProduit.setValue(b.getId_produit());
        cb_User.setValue(b.getId_produit());
        StartingPricetf.setText(Integer.toString(b.getStarting_price()));
    }

    @FXML
    private void Retour(ActionEvent event) {
                                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/fasty/gui/ChooseEvent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void DeleteBid(ActionEvent event) {
        int Id;
        Id=BidTab.getSelectionModel().getSelectedItem().getId_bid();
        try {
            sb.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
