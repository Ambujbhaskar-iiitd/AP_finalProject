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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Game{

    @FXML
    private static Label lbl1;
    @FXML
    private static Label lbl2;

    @FXML
    private Button btn1;


    private Player player1;
    private Player player2;
    private static int boardHeight=830;
    private static int boardWidth=665;
    private Group TileGroup = new Group();

    public Game(){

    }

    public void makeGame(Stage currentStage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("game.fxml"));
        root.getChildren().addAll(TileGroup);
        Scene scene = new Scene(root,boardHeight,boardWidth);
        scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
//        displayName(player1, player2);
        System.out.println(lbl1.getText());
        System.out.println(lbl2.getText());
        makeBoard(root);
        currentStage.setScene(scene);
        currentStage.setHeight(boardHeight);
        currentStage.setWidth(boardWidth);
        currentStage.centerOnScreen();
        currentStage.show();
    }

    public Label getLbl1() {
        return lbl1;
    }

    public Label getLbl2() {
        return lbl2;
    }

    public void displayName(Player p1, Player p2){
        lbl1.setText(p1.getName());
        lbl2.setText(p2.getName());
    }
    public void makeBoard(Pane root){
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

//        Label p1label = new Label(player1.getName());
//        Label p2label = new Label(player2.getName());
//
//        p1label.setTranslateX(89);
//        p1label.setTranslateY(720);
//
//        p2label.setTranslateX(391);
//        p2label.setTranslateY(720);
//
//        root.getChildren().add(p1label);
//        root.getChildren().add(p2label);





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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
