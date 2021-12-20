package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private Button newGameBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TextField p1name;
    @FXML
    private TextField p2name;
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Player player1;
    private Player player2;
    private static int boardHeight=830;
    private static int boardWidth=665;
    private Group TileGroup = new Group();



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
        player1 = new Player("BLUE", name1);
        player2 = new Player("RED", name2);


        Pane root = FXMLLoader.load(getClass().getResource("game.fxml"));
        makeBoard();
        root.getChildren().addAll(TileGroup);
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.setHeight(boardHeight);
        stage.setWidth(boardWidth);
        stage.centerOnScreen();
        stage.show();


    }

    public void makeBoard(){
        int tileSize=60;
        int counter=-1;
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                Color col;

                if(i%2!=0){
                    counter = 100 - 10*i + (j+1) -10;
                }
                else{
                    counter = 100 - 10*i - j;
                }

                if (counter%2==0){
                    col= Color.PINK;
                }
                else col=Color.WHITE;

                Tile tile = new Tile(tileSize,counter,col);
                tile.setTranslateX((j+0.4)*tileSize);
                tile.setTranslateY((i+1)*tileSize);
                TileGroup.getChildren().add(tile);
            }
        }

        Tile waitingArea = new Tile(tileSize,0,Color.color((float)154/255, (float)50/255 ,(float)50/255));
        waitingArea.setTranslateX((0+0.4)*tileSize);
        waitingArea.setTranslateY((10+1)*tileSize);
        TileGroup.getChildren().add(waitingArea);

        ImageView p1Token = new ImageView();
        ImageView p2Token = new ImageView();

        p1Token.setImage(player1.getToken());
        p1Token.setFitHeight(36);
        p1Token.setFitWidth(20);
        p1Token.setTranslateY(630);
        p1Token.setTranslateX(35);

        p2Token.setImage(player2.getToken());
        p2Token.setFitHeight(35);
        p2Token.setFitWidth(20);
        p2Token.setTranslateY(630);
        p2Token.setTranslateX(55);

        ImageView overlay = new ImageView();
        Image boardImg = new Image("Board.png");
        overlay.setFitWidth(624);
        overlay.setFitHeight(600);
        overlay.setTranslateX(0);
        overlay.setTranslateY(tileSize);
        overlay.setImage(boardImg);

        TileGroup.getChildren().add(p1Token);
        TileGroup.getChildren().add(p2Token);

        TileGroup.getChildren().add(overlay);
        p1Token.toFront();
        p2Token.toFront();

    }

    public void setLabel(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 400, 450);
        scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());
        stage.setWidth(400);
        stage.setHeight(450);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void exitApp(){
        Platform.exit();
    }

}
