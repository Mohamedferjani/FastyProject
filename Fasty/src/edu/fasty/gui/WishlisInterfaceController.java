/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javax.xml.transform.TransformerException;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;



/**
 * FXML Controller class
 *
 * @author skand
 */
public class WishlisInterfaceController implements Initializable {
    
    private void makeQr() throws IOException{
        String svgUrl = "https://example.com/path/to/your/svg.svg";
        try {
            URL url = new URL(svgUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WishlisInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(svgUrl, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", new File("qrcode.png"));
            System.out.println("Qr created");
        } catch (WriterException ex) {
            Logger.getLogger(WishlisInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }
    
    
    private  double[] getLastComponentPosition(AnchorPane anc){
        
        double greatestX = Double.MIN_VALUE;
        double greatestY = Double.MIN_VALUE;

        for (Node node : anc.getChildren()) {
            Bounds bounds = node.getBoundsInParent();
            double nodeX = bounds.getMinX() ;
            double nodeY = bounds.getMinY() ;
            if (nodeX  > greatestX) {
                greatestX = nodeX ;
            }
            if (nodeY  > greatestY) {
                greatestY = nodeY ;
            }
        }
        
        double[] result = new double[2];
        result[0] = greatestX;
        result[1] = greatestY;
        return result;

        
        
    }
    @FXML
    private Button  browseButton;
    @FXML
    private Button  bexport;
    @FXML
    private ImageView  imageview;
    
//    private void copyInMyProject (){
//        
//        /////////////////////////// /////////////////////////// ///////////////////////////
//        Stage stage = (Stage) browseButton.getScene().getWindow();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Select PNG File");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
//        File selectedFile = fileChooser.showOpenDialog(stage);
//        if (selectedFile != null) {
//            // Do something with the selected file
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//        }
//         /////////////////////////// /////////////////////////// /////////////////////////// ///////////////////////////
//
//        Path sourcePath = selectedFile.toPath();
//
//        String projectPath = System.getProperty("user.dir");
//        Path destinationPath = Paths.get(projectPath, "icons", selectedFile.getName());
//
//        try {
//            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("File copied successfully.");
//        } catch (IOException ex) {
//            System.out.println("Error copying file: " + ex.getMessage());
//        }
//        
//    }
    
    private BufferedImage BufferedImageARGBtoRGB(BufferedImage image) {
    BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = convertedImage.createGraphics();
    g2d.drawImage(image, 0, 0, null);
    g2d.dispose();
    return convertedImage;
}
    
    private void takesvg(AnchorPane anc)  {
        
    WritableImage snapshot = anc.snapshot(new SnapshotParameters(), null);
    File outputFile = new File("wishlist.svg");
    try {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Set the SVGCanvasSize to match the size of the AnchorPane
         Bounds bounds = anc.getLayoutBounds();
        Dimension canvasSize = new Dimension((int) bounds.getWidth(), (int) bounds.getHeight());
        svgGenerator.setSVGCanvasSize(canvasSize);

        // Draw the snapshot onto the SVGGraphics2D object
        svgGenerator.drawImage(SwingFXUtils.fromFXImage(snapshot, null), null, null);

        // Write the SVG file
        FileWriter out = new FileWriter(outputFile);
        svgGenerator.stream(out, true);
        out.flush();
        out.close();
        System.out.println("svg created");
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
    }

    
    
    private ObservableList<Node> nodes = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorPane ;
    Image im=new Image("file:C:\\Users\\skand\\OneDrive\\Documents\\NetBeansProjects\\Fasty\\src\\Icons\\AdminIcon.png");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double y =200;
        double x=0;
        // TODO
for (int i = 1; i <= 50; i++) {
        ImageView imageView = new ImageView(im);
        Label label = new Label("Image " + i);
        
        // Set the position for the ImageView and Label
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficheProduit.fxml"));
            Parent fxmlRoot = fxmlLoader.load();
            fxmlRoot.setLayoutX(x);
            fxmlRoot.setLayoutY(y);
            anchorPane.getChildren().add(fxmlRoot);
            } catch (IOException ex) {
            Logger.getLogger(WishlisInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        x=x+750;
        if(x == 2250){y=y+500; x=0;}

//        nodes.addAll(imageView, label);
    }

//    anchorPane.getChildren().addAll(nodes);
    
   
     double[] result = getLastComponentPosition(anchorPane);
     
        System.out.println(result[0]+","+result[1]);

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficheProduit.fxml"));
//            Parent fxmlRoot = fxmlLoader.load();
//            fxmlRoot.setLayoutX(0);
//            fxmlRoot.setLayoutY(200);
//            anchorPane.getChildren().add(fxmlRoot);
//            } catch (IOException ex) {
//            Logger.getLogger(WishlisInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

    }    
    
    
    @FXML
    private void OnBrowseClicked(ActionEvent event) throws IOException {
       Stage stage = (Stage) browseButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PNG File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Path sourcePath = selectedFile.toPath();
            String projectPath = System.getProperty("user.dir");
            System.out.println("Project path == "+projectPath);
            Path destinationPath = Paths.get(projectPath, "src\\Icons", selectedFile.getName());
            System.out.println("Destination path == "+destinationPath);
            try {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File copied successfully.");
                String newPathName="file:"+projectPath+"\\src\\Icons\\"+selectedFile.getName();
                Image im=new Image(newPathName);
                imageview.setImage(im);
                System.out.println("image seted");
            } catch (IOException ex) {
                System.out.println("Error copying file: " + ex.getMessage());
            }
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
       
                
    }
    
    
     @FXML
    private void OnExportClicked(ActionEvent event) throws IOException, TransformerException {
       takesvg(anchorPane);
       makeQr();
       
                
    }
    
}
