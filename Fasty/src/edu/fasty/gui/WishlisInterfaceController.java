/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/**
 * FXML Controller class
 *
 * @author skand
 */
public class WishlisInterfaceController implements Initializable {
    
    
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
for (int i = 1; i <= 9; i++) {
        ImageView imageView = new ImageView(im);
        Label label = new Label("Image " + i);
        
        // Set the position for the ImageView and Label
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        label.setLayoutX(x);
        label.setLayoutY(y+100);
        x=x+500;
        if(x == 1500){y=y+200; x=0;}

        nodes.addAll(imageView, label);
    }

    anchorPane.getChildren().addAll(nodes);
    
   
     double[] result = getLastComponentPosition(anchorPane);
     
        System.out.println(result[0]+","+result[1]);
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
    
}
