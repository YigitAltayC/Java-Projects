package project.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import project.models.Book;
import project.services.BooksService;
import project.util.Utils;

public class AddBookController {


    private BooksService service;
    private List<Book> bookList;

    @FXML
    private ImageView bookListImage;

    @FXML
    private Button addButton;

    @FXML
    private Button appDetailsButton;

    @FXML
    private ImageView bookCoverImageView;

    @FXML
    private Button bookListButton;

    @FXML
    private TextArea bookNameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ImageView infoImage;

    @FXML
    private TextArea pageCountField;

    @FXML
    private DatePicker releaseDatePicker;

    @FXML
    private Button resetButton;

    @FXML
    private TextArea urlField;

    @FXML
    private TextArea writerNameField;

    @FXML
    void onBookListButtonClicked(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Books.fxml"));
            Parent root = loader.load();
            BooksController controller = loader.getController();

            controller.setService(service);

            Stage activeStage = (Stage) bookListImage.getScene().getWindow();
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
    void onAddButtonClicked(ActionEvent event) {


        String bookName = bookNameField.getText();
        String writer = writerNameField.getText();
        int pageCount = Integer.parseInt(pageCountField.getText());

        LocalDate localDate = releaseDatePicker.getValue();
        System.out.println("Current System Date is :- \n" + localDate);


        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("\nDefault System Zone is :- \n" + zoneId);

        Date date = Date.from(localDate.atStartOfDay(zoneId).toInstant());


        // 4. convert java.util.Date to java.util.Calendar
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.setTime(date);

        String url = urlField.getText();
        String description = descriptionField.getText();

        Book book = new Book(
                bookName, writer, pageCount, releaseDate, url, description
        );

        if(service.contains(book)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Error Has Occured");
            alert.setContentText(bookName + " with writer, page count and cover image already exists in library.");
            alert.showAndWait();
            return;
        }


        service.addBook(
                bookName, writer, pageCount, releaseDate, url, description
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Book Added Successfully.");
        alert.showAndWait();

    }

    @FXML
    void onDetailsButtonClicked(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AppDetails.fxml"));
            Parent root = loader.load();
            AppDetailsController controller = loader.getController();

            controller.setService(service);

            Stage activeStage = (Stage) bookListImage.getScene().getWindow();
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
    void onResetButtonClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert bookListImage != null : "fx:id=\"addBookImage\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert appDetailsButton != null : "fx:id=\"appDetailsButton\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert bookCoverImageView != null : "fx:id=\"bookCoverImageView\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert bookListButton != null : "fx:id=\"bookListButton\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert bookNameField != null : "fx:id=\"bookNameField\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert infoImage != null : "fx:id=\"infoImage\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert pageCountField != null : "fx:id=\"pageCountField\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert releaseDatePicker != null : "fx:id=\"releaseDatePicker\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert urlField != null : "fx:id=\"urlField\" was not injected: check your FXML file 'AddBook.fxml'.";
        assert writerNameField != null : "fx:id=\"writerNameField\" was not injected: check your FXML file 'AddBook.fxml'.";


        bookListImage.setImage(
                Utils.getWritableImage("https://cdn-icons-png.flaticon.com/512/8074/8074804.png")
        );

        infoImage.setImage(
                Utils.getWritableImage("https://static-00.iconduck.com/assets.00/info-icon-512x512-yqsopuso.png")
        );

        urlField.textProperty().addListener((observable, oldValue, newValue) -> {

            WritableImage img =  Utils.getWritableImage(newValue);

            if(img == null){
                bookCoverImageView.setImage(null);
            } else {
                bookCoverImageView.setImage(img);
            }
        });

        descriptionField.setWrapText(true);
    }

    public void setService(BooksService service)
    {
        this.service = service;
        bookList = service.getBooks();
    }

}
