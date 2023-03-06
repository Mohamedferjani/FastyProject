/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import static edu.fasty.gui.AjouterQuestionController.repeat;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
import edu.fasty.services.ServiceReponse;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author FERJANI
 */
public class AjouterReponseController implements Initializable {

    @FXML
    private TextArea answerID;
    @FXML
    private Button addbtn;
    @FXML
    private Button cancelbtn;

        private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
        private Preferences prefs1 = Preferences.userNodeForPackage(AffichageQuestionsController.class);

final ServiceForum sf= new ServiceForum();
final ServiceQuestion sq = new ServiceQuestion();
final ServiceReponse sr = new ServiceReponse();
    String apiKey = "a513a570ffbcab2c2655247c118785a4";
    String text;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      addbtn.setStyle("-fx-background-color: #4CAF50;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;;-fx-cursor: hand;");
      cancelbtn.setStyle(" -fx-background-color: #e74c3c;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-border-color: #c0392b;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;");
    
    }    

    @FXML
    private void ajouterClicked(ActionEvent event) throws IOException, ParserConfigurationException, SAXException, URISyntaxException {
        if(answerID.getText().isEmpty()){
         Alert alertType=new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid Response !");
                    alertType.show();
        }else if(answerID.getText().matches("[0-9]+")){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                    alertType.setHeaderText("Answer cannot be a number !");
                    alertType.show();
        }else {
            Forum f = sf.getForumById(Integer.parseInt(prefs.get("idforum", "default")));
          Question q = sq.getQuestionById(Integer.parseInt(prefs.get("idquestion", "default")));
           //WebPurify API For Filter bad Words !!
            
            String encodedText = URLEncoder.encode(answerID.getText(), StandardCharsets.UTF_8.toString());
            String apiUrl = String.format("https://api1.webpurify.com/services/rest/?method=webpurify.live.return&api_key=%s&text=%s&replacedefault=true", apiKey, encodedText);
            // Send the API request
          HttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(apiUrl));
           // Parse the response
        HttpEntity entity = response.getEntity();
        String responseText = EntityUtils.toString(entity);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = (Document) builder.parse(new InputSource(new StringReader(responseText)));
          
      // Get the value of the 'found' tag
NodeList nodeList = doc.getElementsByTagName("found");
int found = Integer.parseInt(nodeList.item(0).getTextContent());
 if(found>0){ 
     System.out.println("The text contains a bad word");
    NodeList badWordList = doc.getElementsByTagName("expletive");
    for (int i = 0; i < badWordList.getLength(); i++) {
        Node badWordNode =  badWordList.item(i);
        String badWord = badWordNode.getTextContent();
         text = answerID.getText();
        text = text.replaceAll(badWord, repeat("*", badWord.length()));
    }
          // Change 15 while integrating ...
          Reponse r = new Reponse(f,text,q,15);
           sr.ajouterReponse(r);
               Alert ok=new Alert(Alert.AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Response successfully created !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        answerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
 }  
else if(result.get() == ButtonType.CANCEL){
    ok.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
}
 }else{
      System.out.println("The text does not contain any bad word");
        // Change 15 while integrating ...
          Reponse r = new Reponse(f,answerID.getText(),q,15);
           sr.ajouterReponse(r);
               Alert ok=new Alert(Alert.AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Response successfully created !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        answerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
 }  
else if(result.get() == ButtonType.CANCEL){
    ok.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
}
 }
    }
    }

    @FXML
    private void annulerClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        answerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    
}
