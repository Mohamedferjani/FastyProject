/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

/**
 *
 * @author FERJANI
 */
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WebPurifyExample {
    public static void main(String[] args) throws IOException, URISyntaxException, SAXException, ParserConfigurationException {
        // Replace with your WebPurify API key
        String apiKey = "a513a570ffbcab2c2655247c118785a4";

        // Replace with the text you want to check
        String text = "why are you so bitch?";

        // Encode the text for use in the URL
        
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

        // Build the API request URL
        String apiUrl = String.format("https://api1.webpurify.com/services/rest/?method=webpurify.live.return&api_key=%s&text=%s&replacedefault=true", apiKey, encodedText);

        // Send the API request
        HttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(apiUrl));

        // Parse the response
        HttpEntity entity = response.getEntity();
        String responseText = EntityUtils.toString(entity);
        System.out.println(responseText);
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
        text = text.replaceAll(badWord, repeat("*", badWord.length()));
    }

// NodeList censoredList = doc.getElementsByTagName("expletive");
//    String censoredText = censoredList.item(0).getTextContent();
//
//    // Get the bad words
//    NodeList badWordList = doc.getElementsByTagName("word");
//    List<String> badWords = new ArrayList<>();
//    for (int i = 0; i < badWordList.getLength(); i++) {
//        badWords.add(badWordList.item(i).getTextContent());
//    }
//
//    System.out.println("The text contains a bad word(s): " + badWords);
    System.out.println("Censored text: " + text);
} else {
    System.out.println("The text does not contain any bad word");
}
    }
    public static String repeat(String str, int n) {
    return new String(new char[n]).replace("\0", str);
}
}


