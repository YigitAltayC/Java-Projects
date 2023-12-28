package project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BooksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bookCoverImageView;

    @FXML
    private Label bookNameLabel;

    @FXML
    private Button nextBookButton;

    @FXML
    private Label orderCountLabel;

    @FXML
    private Label pageCountLabel;

    @FXML
    private Button prevBookButton;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private TextField searchBookField;

    @FXML
    private Button searchButton;

    @FXML
    private Label writerLabel;

    @FXML
    void initialize() {
        assert bookCoverImageView != null : "fx:id=\"bookCoverImageView\" was not injected: check your FXML file 'Books.fxml'.";
        assert bookNameLabel != null : "fx:id=\"bookNameLabel\" was not injected: check your FXML file 'Books.fxml'.";
        assert nextBookButton != null : "fx:id=\"nextBookButton\" was not injected: check your FXML file 'Books.fxml'.";
        assert orderCountLabel != null : "fx:id=\"orderCountLabel\" was not injected: check your FXML file 'Books.fxml'.";
        assert pageCountLabel != null : "fx:id=\"pageCountLabel\" was not injected: check your FXML file 'Books.fxml'.";
        assert prevBookButton != null : "fx:id=\"prevBookButton\" was not injected: check your FXML file 'Books.fxml'.";
        assert releaseDateLabel != null : "fx:id=\"releaseDateLabel\" was not injected: check your FXML file 'Books.fxml'.";
        assert searchBookField != null : "fx:id=\"searchBookField\" was not injected: check your FXML file 'Books.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'Books.fxml'.";
        assert writerLabel != null : "fx:id=\"writerLabel\" was not injected: check your FXML file 'Books.fxml'.";

    }

}
