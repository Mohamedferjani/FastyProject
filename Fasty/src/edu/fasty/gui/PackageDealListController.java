
package edu.fasty.gui;

import edu.fasty.entities.PackageDeal;
import edu.fasty.services.ServicePackageDeal;
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
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class PackageDealListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<PackageDeal> EventTab;
    @FXML
    private TableColumn<PackageDeal, Integer> IDPackageTab;
    @FXML
    private TableColumn<PackageDeal, String> ProduitTab;
    @FXML
    private Button Retour;
    @FXML
    private TextField TFSearch;
    @FXML
    private Button btn_delete;
    @FXML
    private ComboBox<Integer> cb_UserPackageDeal;
    ObservableList<Integer> optionsPackage=FXCollections.observableArrayList();
    @FXML
    private TextField BodyPackageDealTf;

    /**
     * Initializes the controller class.
     */
    ServicePackageDeal sp =new ServicePackageDeal();
    int id;
    PackageDeal pa;
    ObservableList<PackageDeal> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<PackageDeal, Integer> EventIDTab;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
        fillcomboUser();
        try {
            
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
            public void fillcomboUser(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from evenement";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsPackage.add(rs.getInt("id_evenement"));
            }
            cb_UserPackageDeal.setItems(optionsPackage);
        } catch (SQLException ex) {
        }
    } 
                public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(sp.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDPackageTab.setVisible(false);
        EventIDTab.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        ProduitTab.setCellValueFactory(new PropertyValueFactory<>("listeproduit"));
        EventTab.setItems(data);
    }
    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sp.afficher());
            //System.out.println(data);
            FilteredList<PackageDeal> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_event()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getId_package()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(p.getListeproduit().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<PackageDeal> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(EventTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		EventTab.setItems(sortedData);
             
        }   

    @FXML
    private void AjouterPackage(ActionEvent event) {
                               String erreurs="";
        if(BodyPackageDealTf.getText().trim().isEmpty()){
            erreurs+="Liste Produit vide vide\n";
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout PackageDeal");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        PackageDeal e = new PackageDeal(cb_UserPackageDeal.getValue(),
                                                 BodyPackageDealTf.getText()
                                                 );
                  sp.ajouter(e);        
                   refreshlist();                                                               //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="PackageDeal créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
         
    }
    }

    @FXML
    private void EditPackage(ActionEvent event) throws SQLException {
        PackageDeal e = new PackageDeal(cb_UserPackageDeal.getValue(),
                                                 BodyPackageDealTf.getText()
                                                 );
        sp.modifier(id, e);
        refreshlist(); 
    }

    @FXML
    private void fillforum(MouseEvent event) {
        PackageDeal e=EventTab.getSelectionModel().getSelectedItem();
        id=e.getId_package();
        cb_UserPackageDeal.setValue(e.getId_event());
        BodyPackageDealTf.setText(e.getListeproduit());
    }

    @FXML
    private void Retour(ActionEvent event) {
                                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuEvent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Deletepackage(ActionEvent event) {
                int Id;
        Id=EventTab.getSelectionModel().getSelectedItem().getId_event();
        try {
            sp.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
