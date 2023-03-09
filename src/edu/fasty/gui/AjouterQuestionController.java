/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.User;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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
import javafx.scene.control.Alert.AlertType;
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
public class AjouterQuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextArea question;

    @FXML
    private Button addbtn;

    @FXML
    private Button cancelbtn;
    
    private String idforum;
    String text;
    
    private Preferences prefs = Preferences.userNodeForPackage(AffichageForumController.class);
    private Preferences prefsUser = Preferences.userNodeForPackage(LoginFXMLController.class);

     
     String apiKey = "a513a570ffbcab2c2655247c118785a4";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addbtn.setStyle("-fx-background-color: #4CAF50;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;;-fx-cursor: hand;");
        cancelbtn.setStyle(" -fx-background-color: #e74c3c;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-width: 1;-fx-border-color: #c0392b;-fx-text-fill: white;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 8 16;");
    } 
    
    @FXML
    void ajouterClicked(ActionEvent event) throws UnsupportedEncodingException, IOException, SAXException, ParserConfigurationException, URISyntaxException {
        if(question.getText().isEmpty()){
         Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Erreur");
                    alertType.setHeaderText("Entrez une question valide !");
                    alertType.show();
        }else if(question.getText().matches("[0-9]+")){
            Alert alertType=new Alert(AlertType.ERROR);
                alertType.setTitle("Erreur");
                    alertType.setHeaderText("La question ne peut pas être un nombre !");
                    alertType.show();
        }else {
            ServiceQuestion sq= new ServiceQuestion();
            ServiceForum sf = new ServiceForum();
            Forum f = sf.getForumById(Integer.parseInt(prefs.get("idforum", "default")));
            
            //WebPurify API For Filter bad Words !!
            
            String encodedText = URLEncoder.encode(question.getText(), StandardCharsets.UTF_8.toString());
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

// Check if there is a bad word
if (found > 0) {
    System.out.println("The text contains a bad word");
    NodeList badWordList = doc.getElementsByTagName("expletive");
    for (int i = 0; i < badWordList.getLength(); i++) {
        Node badWordNode =  badWordList.item(i);
        String badWord = badWordNode.getTextContent();
         text = question.getText();
        text = text.replaceAll(badWord, repeat("*", badWord.length()));
    }    
    IServiceUser su = new IServiceUser();
    User u = su.getOneById(prefsUser.getInt("iduser", 0));
          Question q= new Question(f,text,u);
                
           sq.ajouterQuestion(q);
               Alert ok=new Alert(AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Question crée avec succès !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
          AffichageMesQuestionsController amqc = loader.getController();
          amqc.SetForumID(idforum);
        question.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
 }  
else if(result.get() == ButtonType.CANCEL){
    ok.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
}
    }else{
    // while integrating , modify 15 with current userid that you got from other pages
     IServiceUser su = new IServiceUser();
    User u = su.getOneById(prefsUser.getInt("iduser", 0));
Question q= new Question(f,question.getText(),u);
                
           sq.ajouterQuestion(q);
               Alert ok=new Alert(AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Question crée avec succès !");
              
              Optional<ButtonType> result  = ok.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
          AffichageMesQuestionsController amqc = loader.getController();
          amqc.SetForumID(idforum);
        question.getScene().setRoot(root);
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
    void annulerClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
          AffichageMesQuestionsController amqc = loader.getController();
          amqc.SetForumID(idforum);
        question.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }

    }
  public void SetForumID(String message){
    this.idforum = message;
    } 
      public static String repeat(String str, int n) {
    return new String(new char[n]).replace("\0", str);
}
    
}
