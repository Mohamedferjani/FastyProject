/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Categorie;
import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceCategorie;
import edu.fasty.services.ServiceProduit;
import edu.fasty.utils.JfreeChartApi;
import edu.fasty.utils.DataSource;
import edu.fasty.utils.SMSApi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
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
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class CategorieListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField NomCategorieTf;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;
    @FXML
    private TableColumn<Categorie, Integer> IDCategorieTab;
    @FXML
    private TableColumn<Categorie, Integer> UserIdTab;
    @FXML
    private TableColumn<Categorie, String> NomCategorieTab;
    @FXML
    private TableColumn<Categorie, String> TypeTab;
    @FXML
    private Button Retour;
    @FXML
    private TextField TFSearch;
    @FXML
    private Button Statistique;
    @FXML
    private Button btn_delete1;

    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();
    @FXML
    private TextField TypeCategorieTf;

    /**
     * Initializes the controller class.
     */
    ServiceCategorie sc =new ServiceCategorie();
    int id;
    Categorie c;
    ObservableList<Categorie> data=FXCollections.observableArrayList();
    @FXML
    private TableView<Categorie> CategorieTab;
        private Preferences prefs = Preferences.userNodeForPackage(LoginFXMLController.class);

    
    IServiceUser us = new IServiceUser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
          // TODO
        refreshlist();
//        fillcomboUser();

        try {
            
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
//            public void fillcomboUser(){
//        try {
//            Connection cnx = DataSource.getInstance().getCnx();
//            String req = " select * from user";
//            PreparedStatement cs = cnx.prepareStatement(req);
//            ResultSet rs = cs.executeQuery(req);
//            while(rs.next()){
//                optionsUser.add(rs.getInt("id_user"));
//            }
//            cb_User.setItems(optionsUser);
//        } catch (SQLException ex) {
//
//        }
//    } 
                public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(sc.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDCategorieTab.setVisible(false);
        IDCategorieTab.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        UserIdTab.setVisible(false);
        UserIdTab.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        NomCategorieTab.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        TypeTab.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
        CategorieTab.setItems(data);
    }
    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sc.afficher());
            //System.out.println(data);
            FilteredList<Categorie> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_categorie()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getId_user()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(p.getNom_categorie().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getType_categorie().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Categorie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CategorieTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CategorieTab.setItems(sortedData);
             
        }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
                               String erreurs="";
        if(NomCategorieTf.getText().trim().isEmpty()){
            erreurs+="Title vide\n";
        }
        
        if(TypeCategorieTf.getText().trim().isEmpty()){
            erreurs+="Title vide\n";
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Categorie");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
            User u = us.getOneById(prefs.getInt("iduser", 0));
            System.err.println(u.getId_user());
        Categorie c = new Categorie(NomCategorieTf.getText(),
                                                 TypeCategorieTf.getText(),
                                                 u);
                  sc.ajouter(c);        
                   refreshlist();                                                               //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Evenement créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
        
        // SEND MAIL
        //MailerApi mailer = new MailerApi();
        //mailer.SendMail("Iheb.Rezgui@esprit.tn", "Admin.");
        
                //SEND SMS
        SMSApi sms = new SMSApi();
       // sms.sendSMS("+21651089470", "Admin.");
         
    }
    }

    @FXML
    private void EditCategorie(ActionEvent event) throws SQLException {
        User u = us.getOneById(prefs.getInt("iduser", 0));
        Categorie c = new Categorie(NomCategorieTf.getText(),
                                                 TypeCategorieTf.getText(),
                                                u);
        sc.modifier(id, c);
        refreshlist(); 
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Categorie c=CategorieTab.getSelectionModel().getSelectedItem();
        id=c.getId_categorie();
        //cb_User.setValue(c.getId_user());
        NomCategorieTf.setText(c.getNom_categorie());
        TypeCategorieTf.setText(c.getType_categorie());
    }

    @FXML
    private void Retour(ActionEvent event) {
                                    try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Statistique(ActionEvent event) {
        HashMap<String, Double> data = sc.StatistiqueParType();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }

    @FXML
    private void DeleteCategorie(ActionEvent event) {
             int Id;
        Id=CategorieTab.getSelectionModel().getSelectedItem().getId_categorie();
        try {
            sc.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
