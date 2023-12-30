package project.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project.services.BooksService;
import project.util.Utils;

public class AppDetailsController {

    private BooksService service;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookButton;

    @FXML
    private ImageView bookListImage;

    @FXML
    private Button bookListButton;

    @FXML
    private ImageView addBookImage;

    @FXML
    void onAddBookClicked(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddBook.fxml"));
            Parent root = loader.load();
            AddBookController controller = loader.getController();

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
    void initialize() {
        assert addBookButton != null : "fx:id=\"addBookButton\" was not injected: check your FXML file 'AppDetails.fxml'.";
        assert bookListImage != null : "fx:id=\"addBookImage\" was not injected: check your FXML file 'AppDetails.fxml'.";
        assert bookListButton != null : "fx:id=\"bookListButton\" was not injected: check your FXML file 'AppDetails.fxml'.";
        assert addBookImage != null : "fx:id=\"infoImage\" was not injected: check your FXML file 'AppDetails.fxml'.";

        addBookImage.setImage(
                Utils.getWritableImage("https://cdn-icons-png.flaticon.com/512/8074/8074804.png")
        );

        bookListImage.setImage(
                Utils.getWritableImage("https://cdn-icons-png.flaticon.com/512/8074/8074804.png")
        );

    }

    public void setService(BooksService service) {
        this.service = service;
    }
}
