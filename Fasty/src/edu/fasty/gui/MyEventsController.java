/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.fasty.entities.Event;
import edu.fasty.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;

/**
 * FXML Controller class
 *
 * @author IHEB
 */
public class MyEventsController implements Initializable {

    @FXML
    private ListView<String> txtarea1;
    @FXML
    private ListView<String> txtarea2;
    @FXML
    private ListView<String> txtarea3;
    @FXML
    private ListView<String> txtarea4;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //int user_id = 1;
        List<String> titres = new ArrayList<>();
        List<String> Descripition = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> type=new ArrayList<>();

        ServiceEvent se = new ServiceEvent();
        List<Event> myEvents = se.getMyEvents(1);
        myEvents.forEach((Event event) -> {
            titres.add(event.getTitre());
            Descripition.add(event.getDescription());
            date.add(""+event.getDate());
            if(event.isIsAuction()==true){
            type.add("bid");
            } else {
            type.add("package deal");
            }
            
        });
        //System.out.print(titres);
        
        txtarea1.getItems().addAll(titres);
        txtarea2.getItems().addAll(Descripition);
        txtarea3.getItems().addAll(date);
        txtarea4.getItems().addAll(type);


      //  txtarea1.setItems(titres);
         
}
    @FXML
    private void sortListbydate(ActionEvent event) {
        txtarea1.getItems().clear();
        txtarea2.getItems().clear();
        txtarea3.getItems().clear();
        txtarea4.getItems().clear();

        List<String> titres = new ArrayList<>();
        List<String> Descripition = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> type = new ArrayList<>();
        ServiceEvent se = new ServiceEvent();
        List<Event> myEvents = se.getMyEvents(1);
        List<Event> sortedList = myEvents.stream()
                .sorted(Event.dateDescendingComparator())
                .collect(Collectors.toList());

        sortedList.forEach((Event event1) -> {

            titres.add(event1.getTitre());
            Descripition.add(event1.getDescription());
            date.add("" + event1.getDate());
            if (event1.isIsAuction() == true) {
                type.add("bid");
            } else {
                type.add("package deal");
            }
        });
        txtarea1.getItems().addAll(titres);
        txtarea2.getItems().addAll(Descripition);
        txtarea3.getItems().addAll(date);
        txtarea4.getItems().addAll(type);
    }


}