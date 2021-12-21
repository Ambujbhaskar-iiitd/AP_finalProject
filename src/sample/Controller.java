package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.Arrays;

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
    private Button RollBtn;
    @FXML
    private Label DiceLbl;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Player player1;
    private static Player player2;
    private static int boardHeight = 825;
    private static int boardWidth = 663;
    private static int tileSize = 60;
    private Group TileGroup = new Group();
    private Pane GameRoot;
    private static boolean player1turn = true;

    private static Dice gameDice = new Dice();

    public void playGame(ActionEvent e) throws IOException {

        GameRoot = FXMLLoader.load(getClass().getResource("game.fxml"));
        GameRoot.getChildren().addAll(TileGroup);
        // Reading Player names from text fields
        String name1 = p1name.getText();
        String name2 = p2name.getText();

        // Tile Creation
        makeBoard();
        Tile waitingTile = createWaitingTile();

        for (Tile t: Tile.TileArray) {
            System.out.println("tileno.: "+t.getNum());
        }

        Tile.addSnake(new Snake(99, 59));
        Tile.addSnake(new Snake(93, 69));
        Tile.addSnake(new Snake(75, 17));
        Tile.addSnake(new Snake(23, 5));
        Tile.addSnake(new Snake(32, 6));

        Tile.addLadder(new Ladder(24, 78));
        Tile.addLadder(new Ladder(65, 89));
        Tile.addLadder(new Ladder(15, 53));
        Tile.addLadder(new Ladder(10, 31));
        Tile.addLadder(new Ladder(39, 60));

        player1 = new Player("BLUE", name1, waitingTile, GameRoot);
        player2 = new Player("RED", name2, waitingTile, GameRoot);

        Player.showPlayerNames(player1, player2, GameRoot);

        makeDice();

        Stage GameStage = new Stage();
        Image logo = new Image("logo.png");
        GameStage.getIcons().add(logo);
        GameStage.setTitle("Snake & Ladder");


        scene = new Scene(GameRoot);
        scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
        GameStage.setScene(scene);
        GameStage.setHeight(boardHeight);
        GameStage.setWidth(boardWidth);
        GameStage.centerOnScreen();
        GameStage.show();
    }

    public void startNewGame(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NewGameOptions.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styleNGoptions.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void backToMainMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void exitGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void exitApp() {
        Platform.exit();
    }

    public void makeBoard() {

        //Initializing the TileArray Array List
        Tile temp = new Tile(60, -1, Color.BLACK);
        Tile.TileArray.add(temp);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                Tile.TileArray.add(temp);
        }

        int counter;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Color col;

                if (i % 2 != 0) {
                    counter = 100 - 10 * i + (j + 1) - 10;
                } else {
                    counter = 100 - 10 * i - j;
                }

                if (counter % 2 == 0) {
                    col = Color.PINK;
                } else col = Color.WHITE;

                Tile tile = new Tile(tileSize, counter, col);
                tile.setTranslateX((j + 0.4) * tileSize);
                tile.setTranslateY((i + 1) * tileSize);
                TileGroup.getChildren().add(tile);
//                tile.show();
                Tile.TileArray.set(counter, tile);
            }
//            System.out.println();
        }



        addBoardImg();
        addUpArrows();
    }

    public Tile createWaitingTile() {
        Tile waitingArea = new Tile(tileSize, 0, Color.color((float) 154 / 255, (float) 50 / 255, (float) 50 / 255));
        waitingArea.setTranslateX((0 + 0.4) * tileSize);
        waitingArea.setTranslateY((10 + 1) * tileSize);
        TileGroup.getChildren().add(waitingArea);
        Tile.TileArray.set(0, waitingArea);
        return waitingArea;
    }

    public ImageView addBoardImg() {
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

    public void addUpArrows() {
        ImageView frame = new ImageView();
        Image upArrowImg = new Image("up_arrow.png");
        frame.setFitHeight(50);
        frame.setFitWidth(60);
        frame.setTranslateX(25);
        frame.setTranslateY(650);
        frame.setImage(upArrowImg);
        GameRoot.getChildren().add(frame);
        frame.toFront();
    }

    public void makeDice(){
        gameDice.setTranslateX(277);
        gameDice.setTranslateY(687);
        gameDice.setPrefHeight(76);
        gameDice.setPrefWidth(76);

        gameDice.setId("DICE");

        gameDice.setOnAction(diceClick);
        GameRoot.getChildren().add(gameDice);
    }

    EventHandler<ActionEvent> diceClick = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                RollDice();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public void RollDice() throws IOException, InterruptedException {
        int roll = gameDice.roll();

        if (player1turn) {
            player1.MovePlayer(roll);
            player1turn = false;
        } else {
            player2.MovePlayer(roll);
            player1turn = true;
        }

        if (player1.getTile().getNum() == 100 || player2.getTile().getNum() == 100) {
            Thread.sleep(1000);
            stage.close();
        }

    }
}
