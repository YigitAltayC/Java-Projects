package project.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

        setBookDetails();
    }

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        bookIndex++;
        if(bookIndex >= bookList.size())
            bookIndex = bookList.size() - 1;

        setBookDetails();
    }

    @FXML
    void onPrevButtonPressed(ActionEvent event) {
        bookIndex--;
        if(bookIndex < 0)
            bookIndex = 0;

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
    }

}
