package project.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import project.models.Book;
import project.services.BooksService;
import project.util.Utils;

public class BooksController {


    private BooksService service = new BooksService();
    private List<Book> bookList = service.getBooks();
    private int bookIndex = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookButton;

    @FXML
    private ImageView addBookImage;

    @FXML
    private Button appDetailsButton;

    @FXML
    private ImageView infoImage;


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
    private Label descriptionLabel;

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


        /**
         * https://cdn-icons-png.flaticon.com/512/2702/2702103.png
         * Library URL
         */
        addBookImage.setImage(
                Utils.getWritableImage("https://cdn-icons-png.flaticon.com/512/2702/2702154.png")
        );

        infoImage.setImage(
                Utils.getWritableImage("https://static-00.iconduck.com/assets.00/info-icon-512x512-yqsopuso.png")
        );

        bookNameLabel.setWrapText(true);
        bookNameLabel.setTextAlignment(TextAlignment.JUSTIFY);

        descriptionLabel.setWrapText(true);
        descriptionLabel.setTextAlignment(TextAlignment.JUSTIFY);

        setBookDetails();
    }

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        bookIndex++;
        if(bookIndex >= bookList.size()){
            bookIndex = bookList.size() - 1;
            return;
        }

        setBookDetails();
    }

    @FXML
    void onPrevButtonPressed(ActionEvent event) {
        bookIndex--;
        if(bookIndex < 0){
            bookIndex = 0;
            return;
        }


        setBookDetails();
    }

    @FXML
    void onSearchButtonPressed(ActionEvent event) {

        int i;
        for (i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getBookName().toLowerCase().contains(
                    searchBookField.getText().toLowerCase()
            )) {
                bookIndex = i;
                break;
            }
        }

        if(i == bookList.size()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Error Has Occured");
            alert.setContentText("Book not found in database with input: " + searchBookField.getText());
            alert.showAndWait();
            return;
        }

        searchBookField.clear();
        setBookDetails();
    }

    private void setBookDetails(){

        orderCountLabel.setText(String.valueOf(bookIndex));

        bookCoverImageView.setImage(
                bookList.get(bookIndex).getCoverImage().getImage()
        );

        bookNameLabel.setText(
                bookList.get(bookIndex).getBookName()
        );

        writerLabel.setText(
                bookList.get(bookIndex).getWriter()
        );

        pageCountLabel.setText(
                bookList.get(bookIndex).getPageCount() + " pages"
        );

        releaseDateLabel.setText(
                "First published in " +
                Utils.calendarToString(
                        bookList.get(bookIndex).getReleaseDate()
                )
        );

        descriptionLabel.setText(
                bookList.get(bookIndex).getDescription()
        );

    }

    @FXML
    void onAddBookClicked(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddBook.fxml"));
            Parent root = loader.load();
            AddBookController controller = loader.getController();

            controller.setService(service);

            Stage activeStage = (Stage) addBookImage.getScene().getWindow();
            activeStage.close();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onDetailsButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AppDetails.fxml"));
            Parent root = loader.load();
            AppDetailsController controller = loader.getController();

            controller.setService(service);

            Stage activeStage = (Stage) descriptionLabel.getScene().getWindow();
            activeStage.close();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setService(BooksService service)
    {
        this.service = service;
        bookList = service.getBooks();
    }

}
