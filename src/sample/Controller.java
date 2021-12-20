package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button newGameBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TextField p1name;
    @FXML
    private TextField p2name;

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
        String name1, name2;
        name1 = p1name.getText();
        name2 = p2name.getText();
        Player player1 = new Player("BLUE", name1);
        Player player2 = new Player("RED", name2);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Pane root =loader.load();
        Game gameController = loader.getController();
        gameController.setPlayer1(player1);
        gameController.setPlayer2(player2);
//        gameController.displayName(player1, player2);
        gameController.makeGame((Stage) ((Node)e.getSource()).getScene().getWindow());

    }
    public void exitApp(){
        Platform.exit();
    }

}
