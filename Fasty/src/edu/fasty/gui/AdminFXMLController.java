package edu.fasty.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminFXMLController {

    @FXML
    private Button btnUser;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnCotegories;
    @FXML
    private Button bRatingCategory;
    @FXML
    private Button bRating;
    @FXML
    private Button bScore;
    @FXML
    private Tab btnAfficheUser;
    @FXML
    private AnchorPane AffichageUser;
    @FXML
    private Tab btnAddUser;
    @FXML
    private AnchorPane AjoutUser;
    @FXML
    private Tab btnModifUser;
    @FXML
    private AnchorPane ModifUser;
    @FXML
    private Tab btnSuppUser;
    @FXML
    private AnchorPane SuppUser;

    @FXML
    private void OnUserClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource())
    }

    @FXML
    private void Onmouseexited(MouseEvent event) {
    }

    @FXML
    private void Onmouseentred(MouseEvent event) {
    }

    @FXML
    private void OnProductsClicked(ActionEvent event) {
    }

    @FXML
    private void OnCategoriesClicked(ActionEvent event) {
    }

    @FXML
    private void OnRatingCategoryClicked(ActionEvent event) {
    }

    @FXML
    private void OnRatingClicked(ActionEvent event) {
    }

    @FXML
    private void OnScoreClicked(ActionEvent event) {
    }

    @FXML
    private void OnViewUserClicked(Event event) {
    }

    @FXML
    private void OnAddUserClicked(Event event) {
    }

    @FXML
    private void OnUpdateUserClicked(Event event) {
    }

    @FXML
    private void OnDeleteUserClicked(Event event) {
    }


}
