package edu.fasty.gui;

import edu.fasty.entities.Bid;
import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceBid;
import edu.fasty.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Date;
import java.util.prefs.Preferences;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class MakeBidController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_ajouterBid;
    @FXML
    private TableView<Bid> BidTab;
    @FXML
    private TableColumn<Bid, Integer> IDBidTab;
    @FXML
    private TableColumn<Bid, Integer> IDUserTab;
    @FXML
    private TableColumn<Bid, Integer> StartingPriceTab;
    @FXML
    private Button Retour;
    @FXML
    private TextField TFSearch;
    @FXML
    private TextField StartingPricetf;

    /**
     * Initializes the controller class.
     */
    ServiceBid sb =new ServiceBid();
    int id;
    Bid b;
    ObservableList<Bid> data=FXCollections.observableArrayList();
    @FXML
    private Label bidwinner;
        private Preferences prefs = Preferences.userNodeForPackage(LoginFXMLController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        refreshlist();
      //  fillcomboUser();
        try {
            
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void EditBid(ActionEvent event) throws SQLException {
        System.err.println(StartingPricetf.getText());
        String erreurs="";
        if(StartingPricetf.getText().trim().isEmpty()){
            erreurs+="price vide\n";
        }
        
        else {
           
                if(Integer.parseInt(StartingPricetf.getText())<BidTab.getSelectionModel().getSelectedItem().getStarting_price()){
                    erreurs+="price must be greater\n";
                }
            
            
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Bid");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
            IServiceUser su = new IServiceUser();
            User u = su.getOneById(prefs.getInt("iduser", 0));
            int id_lastbidder=3;
                    Date now = new Date();
                        LocalDate now2 = LocalDate.now();
            LocalDate myObj = LocalDate.now();
                         // System.out.print(java.sql.Date.valueOf(now2)

//                                                     
        //Date sqldate=java.sql.Date.valueOf(now2).
        //System.out.print(sb.getDateNow());
        Bid b = new Bid(Integer.parseInt(StartingPricetf.getText()),
                                                 u,
                                                 id_lastbidder,
myObj
        
        );
       
       // Update bid winner label
       int id_bid = BidTab.getSelectionModel().getSelectedItem().getId_bid();
       //System.out.println(id_winner);
      
       // Date sqldate = sb.getLastTimeDataModifiedbyBidId(id_winner);
            java.util.Date datetype = new java.util.Date(sb.getLastTimeDataModifiedbyBidId(id_bid).getTime());

               // Duration dur = Duration.between(currentDate, lastmodifier);
               
              
               


for (int i = 0; i < 70; i += 10) {
             long difference_In_Time
                = now.getTime() - datetype.getTime();
            long difference_In_Seconds
                = (difference_In_Time
                   / 1000)
                  % 60;  
            //System.out.println(difference_In_Seconds);
if(difference_In_Seconds<40){
    bidwinner.setText("bid is still running");
}else {
bidwinner.setText(sb.getBidWinner(id_bid));
}
                   // Execute code every time the variable moves by 10
    System.out.println("Variable moved by 10. Current value: " + difference_In_Seconds);
}





//Duration duration = Duration.between(lastModifierloca, currentDate);


       //System.out.print(bidwinner);
       

        sb.modifierFront(id, b);
        refreshlist(); }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Bid b=BidTab.getSelectionModel().getSelectedItem();
        id=b.getId_bid();
        StartingPricetf.setText(Integer.toString(b.getStarting_price()));
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
            Logger.getLogger(EventHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   private void bidlogic(){
   
   
   
   }
}