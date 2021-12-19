package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Game {

    @FXML
    private Label myLabel;


    private static int boardHeight=830;
    private static int boardWidth=665;
    private Group TileGroup = new Group();

    public Game(){

    }

    public Game(Stage currentStage) throws IOException {
        myLabel.setText("GAMING!!!!!!!!!");
        Pane root = FXMLLoader.load(getClass().getResource("game.fxml"));
        root.getChildren().addAll(TileGroup);
        makeBoard();
        Scene scene = new Scene(root,boardHeight,boardWidth);
//        scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
        currentStage.setScene(scene);
        currentStage.setHeight(boardHeight);
        currentStage.setWidth(boardWidth);
        currentStage.centerOnScreen();
        currentStage.show();
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
        ImageView overlay = new ImageView();
        Image boardImg = new Image("Board.png");
        overlay.setFitWidth(600);
        overlay.setFitHeight(600);
        overlay.setTranslateX(0.4*tileSize);
        overlay.setTranslateY(tileSize);
        overlay.setImage(boardImg);
        TileGroup.getChildren().add(overlay);
        }
    }
}
