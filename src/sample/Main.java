package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage menuStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(root, 400, 450);
        String css = "styleMenu.css";
        scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
        Image logo = new Image("logo.png");

        menuStage.getIcons().add(logo);
        menuStage.setTitle("Snake & Ladder Game");
        menuStage.setScene(scene);
        menuStage.setResizable(false);
        menuStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
