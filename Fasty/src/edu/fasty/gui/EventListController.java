/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fasty.gui;


import com.twilio.exception.AuthenticationException;
import edu.fasty.entities.Evenement;
import edu.fasty.services.ServiceEvent;
import edu.fasty.utils.JfreeChartApi;
import edu.fasty.utils.MailerApi;
import edu.fasty.utils.Myconnexion;
import edu.fasty.utils.SMSApi;
import java.io.IOException;
import static java.lang.System.err;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import javafx.scene.control.DatePicker;
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
public class EventListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField TitleEventTf;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Evenement> EventTab;
    @FXML
    private TableColumn<Evenement, Integer> IDEventTab;
    @FXML
    private TableColumn<Evenement, Integer> UserIdTab;
    @FXML
    private TableColumn<Evenement, String> TitleTab;
    @FXML
    private TableColumn<Evenement, LocalDateTime> DateTab;
    @FXML
    private Button Retour;
    @FXML
    private TextField TFSearch;
    @FXML
    private DatePicker dateEvent;
    @FXML
    private Button Statistique;
    @FXML
    private ComboBox<Integer> cb_UserEvent;
    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();
    @FXML
    private Button btn_delete1;

    /**
     * Initializes the controller class.
     */
    ServiceEvent se =new ServiceEvent();
    int id;
    Evenement e;
    ObservableList<Evenement> data=FXCollections.observableArrayList();
    
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
            String req = " select * from user";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsUser.add(rs.getInt("id_user"));
            }
            cb_UserEvent.setItems(optionsUser);
        } catch (SQLException ex) {
        }
    } 
                public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(se.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDEventTab.setVisible(false);
        IDEventTab.setCellValueFactory(new PropertyValueFactory<>("id_evenement "));
        UserIdTab.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        TitleTab.setCellValueFactory(new PropertyValueFactory<>("titre"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("date"));
        EventTab.setItems(data);
    }
    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(se.afficher());
            //System.out.println(data);
            FilteredList<Evenement> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_evenement()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getId_user()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(p.getTitre().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getDate()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Evenement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(EventTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		EventTab.setItems(sortedData);
             
        }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
                        String erreurs="";
        if(TitleEventTf.getText().trim().isEmpty()){
            erreurs+="Title vide\n";
        }
        if(  dateEvent.getValue() != null
             &&   dateEvent.getValue().isBefore(LocalDate.now())
               ){
            erreurs+="date must be after\n";
        } 
        if(dateEvent.getValue() == null){
            erreurs+="date vide\n";
        } 
        
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Evenement");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        Evenement e = new Evenement(dateEvent.getValue().atStartOfDay(),
                                                 TitleEventTf.getText(),
                                                 cb_UserEvent.getValue());
                  se.ajouter(e);        
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
                  try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/fasty/gui/ChooseEventController.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PackageDealListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // SEND MAIL
        //MailerApi mailer = new MailerApi();
        //mailer.SendMail("Iheb.Rezgui@esprit.tn", "Admin.");
        
//        SMSApi sms = new SMSApi();
//        sms.sendSMS("+21651089470", "Admin.");
//            }catch(AuthenticationException e){
//            System.out.print(e);
//            }
         
//    
    }
    }

    @FXML
    private void EditEvenement(ActionEvent event) throws SQLException {
        Evenement e = new Evenement(dateEvent.getValue().atStartOfDay(),
                                                 TitleEventTf.getText(),
                                                 cb_UserEvent.getValue());
        se.modifier(id, e);
        refreshlist(); 
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Evenement e=EventTab.getSelectionModel().getSelectedItem();
        id=e.getId_evenement();
        cb_UserEvent.setValue(e.getId_user());
        TitleEventTf.setText(e.getTitre());
        dateEvent.setValue(e.getDate().toLocalDate());
    }

    @FXML
    private void Retour(ActionEvent event) {
                            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/fasty/gui/MenuEvent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Statistique(ActionEvent event) {
        HashMap<String, Double> data = se.StatistiqueParTitle();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }

    @FXML
    private void DeleteEvenement(ActionEvent event) {
        int Id;
        Id=EventTab.getSelectionModel().getSelectedItem().getId_evenement();
        try {
            se.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
