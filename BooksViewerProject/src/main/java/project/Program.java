package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.models.Book;
import project.util.OrderByBookComparator;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Program extends Application {

    public static void main(String[] args) {

        launch();

        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        launchMainScreen(primaryStage);
    }

    private void launchMainScreen(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Books.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
