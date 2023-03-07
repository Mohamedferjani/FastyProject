/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    
    
    private ObservableList<Node> nodes = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorPane ;
    Image im=new Image("file:C:\\Users\\skand\\OneDrive\\Documents\\NetBeansProjects\\Fasty\\src\\Icons\\AdminIcon.png");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double y =100;
        double x=0;
        // TODO
for (int i = 1; i <= 500; i++) {
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
    
}
