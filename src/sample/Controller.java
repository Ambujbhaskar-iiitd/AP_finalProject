package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button newGameBtn;
    @FXML
    private Button exitBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void startNewGame(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NewGameOptions.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styleNGoptions.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void backToMainMenu(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void playGame(ActionEvent e) throws IOException {
        Game game = new Game((Stage) ((Node)e.getSource()).getScene().getWindow());
    }
    public void exitApp(){
        Platform.exit();
    }

}
