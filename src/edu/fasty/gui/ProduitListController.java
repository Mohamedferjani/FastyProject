/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;

import edu.fasty.entities.Categorie;
import edu.fasty.entities.Produit;
import edu.fasty.entities.User;
import edu.fasty.services.IPService;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceCategorie;
import edu.fasty.services.ServiceProduit;
import edu.fasty.utils.DataSource;
import edu.fasty.utils.SMSApi;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
public class ProduitListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField DescriptionTf;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Produit> ProduitTab;
    @FXML
    private TableColumn<Produit, Integer> IDProduitTab;
    @FXML
    private TableColumn<Produit, Integer> UserIdTab;
    @FXML
    private TableColumn<Produit, String> NomProduitTab;
    @FXML
    private TableColumn<Produit, String> descriptionTab;
    @FXML
    private TableColumn<Produit, String> valeurTab;
    @FXML
    private TableColumn<Produit, String> ImageTab;
    @FXML
    private TableColumn<Produit, String> CatégorieIdTab;
    @FXML
    private Button Retour;
    @FXML
    private TextField TFSearch;
    @FXML
    private Button btn_delete1;
    @FXML
    private ComboBox<String> cb_Categorie;
    ObservableList<String> optionsCategorie=FXCollections.observableArrayList();
    @FXML
    private TextField NomProduitTf;
    @FXML
    private Button image;
    @FXML
    private Label file_path;
    @FXML
    private TextField ValeurTf;
    @FXML
    private ImageView image_view;
    private ComboBox<Integer> cb_User;
    private String pathImg;
    File selectedFile;
        private Preferences prefs = Preferences.userNodeForPackage(LoginFXMLController.class);

    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    ServiceProduit sp =new ServiceProduit();
    int id;
    Produit p;
    ObservableList<Produit> data=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
         IServiceUser sc = new IServiceUser();
        User u = sc.getOneById(prefs.getInt("iduser", 0));
            System.err.println(u.getId_user());
        });
          // TODO
        refreshlist();
//        fillcomboUser();
        fillcomboCategorie();
        try {
            
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
//    public void fillcomboUser(){
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
    public void fillcomboCategorie(){
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            String req = " select * from categorie";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsCategorie.add(rs.getString("nom_categorie"));
                
            }
            cb_Categorie.setItems(optionsCategorie);
        } catch (SQLException ex) {
          //  Logger.getLogger(AjouterResponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
                public void refreshlist(){
                    Categorie c = null;
                    ServiceCategorie sc = new ServiceCategorie();
        data.clear();
        try {
            data=FXCollections.observableArrayList(sp.afficher());
            for(Produit i : data){
              c = sc.getCategorieByid(i.getId_categorie());
        }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDProduitTab.setVisible(false);
        IDProduitTab.setCellValueFactory(new PropertyValueFactory<>("id_produit "));
      UserIdTab.setCellValueFactory(new PropertyValueFactory<>("id_user"));
      UserIdTab.setVisible(false);
        NomProduitTab.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        descriptionTab.setCellValueFactory(new PropertyValueFactory<>("description"));
        valeurTab.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        ImageTab.setCellValueFactory(new PropertyValueFactory<>("img_produit"));
        CatégorieIdTab.setVisible(false);
        CatégorieIdTab.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        ProduitTab.setItems(data);
    }
    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sp.afficher());
            //System.out.println(data);
            FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_produit()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getId_categorie()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }                    
                    else if(p.getNom_produit().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getValeur().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(ProduitTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		ProduitTab.setItems(sortedData);
             
        }


    @FXML
    private void AjouterProduit(ActionEvent event) {
        IServiceUser sc = new IServiceUser();
        User u = sc.getOneById(prefs.getInt("iduser", 0));
                      String erreurs="";
        if(NomProduitTf.getText().trim().isEmpty()){
            erreurs+="Nom vide\n";
        }
        if(DescriptionTf.getText().trim().isEmpty()){
            erreurs+="Description vide\n";
        }
        if(ValeurTf.getText().trim().isEmpty()){
            erreurs+="Valeur vide\n";
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Produit");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }else if(selectedFile == null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une image pour le produit !");
            alert.show();
        }else if(cb_Categorie.getValue() == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une catégorie pour le produit !");
            alert.show();
        }
        else{
        ServiceProduit Sp = new ServiceProduit();
       ServiceCategorie scc = new ServiceCategorie();
       Categorie c = scc.getCategorieByNom(cb_Categorie.getValue());
            System.err.println(c.getId_categorie());
        Produit p = new Produit(NomProduitTf.getText(),
                                                 DescriptionTf.getText(),
                                                 pathImg, 
                                                  ValeurTf.getText(),
                                                 u,
                                                 c.getId_categorie());
                  Sp.ajouter(p);        
                  refreshlist();
                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Produit créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(15000));
        
        // SEND MAIL
        //MailerApiProduit mailer = new MailerApiProduit();
        //mailer.SendMail("hamza@esprit.tn", "Admin.");
        
                //SEND SMS
        //SMSApi sms = new SMSApi();
        //sms.sendSMS("+21626457879", "Admin.");
                  
    }
    }

    @FXML
    private void EditProduit(ActionEvent event) throws SQLException {
        IServiceUser sc = new IServiceUser();
        User u = sc.getOneById(prefs.getInt("iduser", 0));
        ServiceCategorie scc = new ServiceCategorie();
       Categorie c = scc.getCategorieByNom(cb_Categorie.getValue());
        Produit p = new Produit(NomProduitTf.getText(),
                                                 DescriptionTf.getText(),
                                                 pathImg, 
                                                  ValeurTf.getText(),
                                                 u,
                                                 c.getId_categorie());
        sp.modifier(id, p);
        refreshlist(); 
    }

    @FXML
    private void fillforum(MouseEvent event) {
        ServiceCategorie sc = new ServiceCategorie();
        Produit p=ProduitTab.getSelectionModel().getSelectedItem();
        id=p.getId_produit();
        //cb_User.setValue(p.getId_user());
        Categorie c = sc.getCategorieByid(p.getId_categorie());
      //  cb_Categorie.setValue(c.getNom_categorie());
        NomProduitTf.setText(p.getNom_produit());
        DescriptionTf.setText(p.getDescription());
        ValeurTf.setText(p.getValeur());
        pathImg = p.getImg_produit();
        Image image = new Image(pathImg);
         image_view.setImage(image); 
        
    }

    @FXML
    private void Retour(ActionEvent event) {
                                            try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteProduit(ActionEvent event) {
                int Id;
        Id=ProduitTab.getSelectionModel().getSelectedItem().getId_produit();
        try {
            sp.supprimer(id);
            refreshlist();
            NomProduitTf.clear();
            DescriptionTf.clear();
            ValeurTf.clear();
            image_view.setImage(null);
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void image(ActionEvent event) {
                  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
         selectedFile = fileChooser.showOpenDialog(left_main.getScene().getWindow());
        if (selectedFile != null) {
            // The user selected an image file
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Path path = Paths.get(selectedFile.getAbsolutePath());
    Path relativePath = path.subpath(path.getNameCount() - 2, path.getNameCount()).normalize();
           String  imagePath = relativePath.toString().replace('\\', '/');
           pathImg= imagePath;
             Image image = new Image(pathImg);
             image_view.setImage(image);       
        }else{

            System.out.println("NO DATA EXIST!");

        }
    }
    
}
