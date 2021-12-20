package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Player player1;
    private Player player2;
    private static int boardHeight=825;
    private static int boardWidth=665;
    private static int tileSize=60;
    private Group TileGroup = new Group();
    private Pane GameRoot;

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

    public void exitGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void exitApp(){
        Platform.exit();
    }

    public void playGame(ActionEvent e) throws IOException {

        GameRoot = FXMLLoader.load(getClass().getResource("game.fxml"));
        GameRoot.getChildren().addAll(TileGroup);
        // Reading Player names from text fields
        String name1 = p1name.getText();
        String name2 = p2name.getText();

        Tile waitingTile = createWaitingTile();
        makeBoard();

        player1 = new Player("BLUE", name1, waitingTile);
        player2 = new Player("RED", name2, waitingTile);

        showTokens(player1, player2, waitingTile);
        showPlayerNames(player1, player2);

        Stage GameStage = new Stage();
        Image logo = new Image("logo.png");
        GameStage.getIcons().add(logo);
        GameStage.setTitle("Snake & Ladder");

        scene = new Scene(GameRoot);
        scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());
        GameStage.setScene(scene);
        GameStage.setHeight(boardHeight);
        GameStage.setWidth(boardWidth);
        GameStage.centerOnScreen();
        GameStage.show();
    }

    public void makeBoard(){

        int counter;
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
        addBoardImg();
    }

    public Tile createWaitingTile(){
        Tile waitingArea = new Tile(tileSize,0,Color.color((float)154/255, (float)50/255 ,(float)50/255));
        waitingArea.setTranslateX((0+0.4)*tileSize);
        waitingArea.setTranslateY((10+1)*tileSize);
        TileGroup.getChildren().add(waitingArea);
        return waitingArea;
    }

    public ImageView addBoardImg(){
        ImageView overlay = new ImageView();
        Image boardImg = new Image("Board.png");
        overlay.setFitWidth(624);
        overlay.setFitHeight(600);
        overlay.setTranslateX(0);
        overlay.setTranslateY(tileSize);
        overlay.setImage(boardImg);
        TileGroup.getChildren().add(overlay);
        return overlay;
    }

    public void showTokens(Player player1, Player player2, Tile waitingTile){
        ImageView p1Token = new ImageView();
        ImageView p2Token = new ImageView();

        p1Token.setImage(player1.getToken());
        p1Token.setFitHeight(36);
        p1Token.setFitWidth(20);
        p1Token.setTranslateY(waitingTile.getPlayerY(player1));
        p1Token.setTranslateX(waitingTile.getPlayerX(player1));

        p2Token.setImage(player2.getToken());
        p2Token.setFitHeight(35);
        p2Token.setFitWidth(20);
        p2Token.setTranslateY(waitingTile.getPlayerY(player2));
        p2Token.setTranslateX(waitingTile.getPlayerY(player2));

        TileGroup.getChildren().add(p1Token);
        TileGroup.getChildren().add(p2Token);

        p1Token.toFront();
        p2Token.toFront();
    }

    public void showPlayerNames(Player player1, Player player2){
        Label p1label = new Label(player1.getName());
        Label p2label = new Label(player2.getName());

        p1label.setFont(Font.font("Dejavu Sans Bold", 24.0));
        p2label.setFont(Font.font("Dejavu Sans Bold", 24.0));
        p1label.setTextFill(Color.WHITE);
        p2label.setTextFill(Color.WHITE);
        p1label.setTextAlignment(TextAlignment.RIGHT);
        p2label.setTextAlignment(TextAlignment.LEFT);

        p1label.setTranslateX(89);
        p1label.setTranslateY(720);

        p2label.setTranslateX(391);
        p2label.setTranslateY(720);

        GameRoot.getChildren().add(p1label);
        GameRoot.getChildren().add(p2label);
    }

}
