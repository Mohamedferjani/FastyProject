/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import static edu.fasty.gui.AjouterQuestionController.repeat;
import edu.fasty.services.ServiceForum;
import edu.fasty.services.ServiceQuestion;
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
public class ModifierQuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea questiontype;
        private Preferences prefs = Preferences.userNodeForPackage(AffichageMesQuestionsController.class);
        private Preferences prefsForum = Preferences.userNodeForPackage(AffichageForumController.class);
    String apiKey = "a513a570ffbcab2c2655247c118785a4";
    String text;
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   


    @FXML
    void CancelClicked(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
        questiontype.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
    }

    @FXML
    void modifierquestion(ActionEvent event) throws IOException, ParserConfigurationException, SAXException, URISyntaxException {
 if(questiontype.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR ");
                alert.setHeaderText("Invalid Question !");
                alert.show();
        }else if(questiontype.getText().matches("[0-9]+")){
        Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR ");
                alert.setHeaderText("Question cannot be a number !");
                alert.show();
        }else{
         Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
         alertType.setTitle("CONFIRMATION !");
         alertType.setHeaderText("Are you sure you want to update this question ?");
          Optional<ButtonType> result  = alertType.showAndWait();
                        
 if(result.get() == ButtonType.OK){
 ServiceForum sf = new ServiceForum();
  ServiceQuestion sq= new ServiceQuestion();
 Forum f = sf.getForumById(Integer.parseInt(prefsForum.get("idforum", "default")));
 
     //WebPurify API For Filter bad Words !!
            
            String encodedText = URLEncoder.encode(questiontype.getText(), StandardCharsets.UTF_8.toString());
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
         text = questiontype.getText();
        text = text.replaceAll(badWord, repeat("*", badWord.length()));
    }
 // while integrating , modify 15 with current userid that you got from other pages
 
 Question q=  new Question(Integer.parseInt(prefs.get("idquestion", "default")),f,text,15);
 sq.modifierQuestion(q);
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
        questiontype.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
}else{
         System.out.println("The text does not contain any bad word");

    // while integrating , modify 15 with current userid that you got from other pages
    
Question q=  new Question(Integer.parseInt(prefs.get("idquestion", "default")),f,questiontype.getText(),15);
 sq.modifierQuestion(q);
 FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMesQuestions.fxml"));
     try {
          Parent root = loader.load();
        questiontype.getScene().setRoot(root);
     } catch (IOException e) {
         System.err.println("Error: "+e.getMessage());
     }
}
 }
        }
    }
    public void setContenuID(String message){
    this.questiontype.setText(message);
    }
    
}
