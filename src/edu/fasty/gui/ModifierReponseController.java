/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import edu.fasty.entities.User;
import static edu.fasty.gui.AjouterQuestionController.repeat;
import edu.fasty.services.IServiceUser;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
import edu.fasty.services.ServiceReponse;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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
public class ModifierReponseController implements Initializable {
    
    @FXML
    private TextArea AnswerID;
    private Preferences prefs = Preferences.userNodeForPackage(AffichageReponsesController.class);
    private Preferences prefsForum = Preferences.userNodeForPackage(AffichageForumController.class);
    private Preferences prefs1 = Preferences.userNodeForPackage(AffichageQuestionsController.class);
    private Preferences prefsUser = Preferences.userNodeForPackage(LoginFXMLController.class);
    String apiKey = "a513a570ffbcab2c2655247c118785a4";
    String text;

    /**
     * Initializes the controller class.
     */
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void modifierReponse(ActionEvent event) throws UnsupportedEncodingException, IOException, ParserConfigurationException, SAXException {
        
if(AnswerID.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR ");
                alert.setHeaderText("Réponse invalide !");
                alert.show();
        }else if(AnswerID.getText().matches("[0-9]+")){
        Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR ");
                alert.setHeaderText("La réponse ne peut pas être un nombre !");
                alert.show();
        }else{
         Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
         alertType.setTitle("CONFIRMATION !");
         alertType.setHeaderText("Voulez-vous vraiment mettre à jour votre réponse ?");
          Optional<ButtonType> result  = alertType.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 ServiceForum sf = new ServiceForum();
 ServiceQuestion sq = new ServiceQuestion();
  ServiceReponse sr = new ServiceReponse();
 Forum f = sf.getForumById(Integer.parseInt(prefsForum.get("idforum", "default")));
 Question q= sq.getQuestionById(Integer.parseInt(prefs1.get("idquestion", "default")));
     //WebPurify API For Filter bad Words !!
            
            String encodedText = URLEncoder.encode(AnswerID.getText(), StandardCharsets.UTF_8.toString());
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
         text = AnswerID.getText();
        text = text.replaceAll(badWord, repeat("*", badWord.length()));
    }
 // while integrating , modify 15 with current userid that you got from other pages
 IServiceUser su = new IServiceUser(); 
User u = su.getOneById(prefsUser.getInt("iduser", 0));
 Reponse r = new Reponse(Integer.parseInt(prefs.get("idreponse", "default")),f,text,q,u);
 sr.modifierReponse(r);
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        AnswerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
}else{
         System.out.println("The text does not contain any bad word");

    // while integrating , modify 15 with current userid that you got from other pages
     IServiceUser su = new IServiceUser(); 
User u = su.getOneById(prefsUser.getInt("iduser", 0));
 Reponse r = new Reponse(Integer.parseInt(prefs.get("idreponse", "default")),f,AnswerID.getText(),q,u);
 sr.modifierReponse(r);
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        AnswerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
}
 }
        }
    }
    
    
    @FXML
    void CancelClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponses.fxml"));
     try {
          Parent root = loader.load();
        AnswerID.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }
    public void setContenuID(String message){
    this.AnswerID.setText(message);
    }
    
}
