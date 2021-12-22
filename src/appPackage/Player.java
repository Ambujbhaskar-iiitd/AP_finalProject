package appPackage;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Player {
    private final String name;
    private Image token;
    private ImageView pToken;
    private Tile tile;
    private final String color;
    private Pane root;
    private boolean locked=true;
    private boolean onLadderTile = false;
    private boolean onSnakeTile = false;
    private static final AudioClip audioLadder = new AudioClip(new File("src/animeWow.mp3").toURI().toString());
    private static final AudioClip audioSnake = new AudioClip(new File("src/bruh.mp3").toURI().toString());
    private static final AudioClip audioWin = new AudioClip(new File("src/winSound.mp3").toURI().toString());
    private static Label p1label;
    private static Label p2label;

    public Player(String color, String name, Tile tile, Pane root) {
        this.root = root;
        this.tile = tile;
        this.color = color;
        this.name = name;

        if (color.equals("BLUE")){
            token = new Image("blueToken.png");
        }
        else{
            token = new Image("redToken.png");
        }
        showToken(tile);
    }

    public static void showPlayerNames(Player player1, Player player2, Pane root){
        p1label = new Label(player1.getName());
        p2label = new Label(player2.getName());

        p1label.setId("player1LBL");
        p2label.setId("player2LBL");

        p1label.setFont(Font.font("Dejavu Sans Bold", 24.0));
        p2label.setFont(Font.font("Dejavu Sans Bold", 24.0));
        p1label.setTextFill(Color.BLUE);
        p2label.setTextFill(Color.WHITE);

        p1label.setTranslateX(89);
        p1label.setTranslateY(730);

        p2label.setTranslateX(461);
        p2label.setTranslateY(730);

        root.getChildren().add(p1label);
        root.getChildren().add(p2label);
    }

    public void showToken(Tile waitingTile){
        this.pToken = new ImageView();

        this.pToken.setImage(this.getToken());
        if (this.color.equals("BLUE")){
            this.pToken.setFitHeight(36);
            this.pToken.setFitWidth(27);
        }else {
            this.pToken.setFitHeight(35);
            this.pToken.setFitWidth(25);
        }

        this.pToken.setTranslateY(waitingTile.getPlayerY(this));
        this.pToken.setTranslateX(waitingTile.getPlayerX(this));

        root.getChildren().add(this.pToken);
        this.pToken.toFront();
    }

    public static Label getP2label() {
        return p2label;
    }

    public static void setP2label(Label p2label) {
        Player.p2label = p2label;
    }

    public static Label getP1label() {
        return p1label;
    }

    public static void setP1label(Label p1label) {
        Player.p1label = p1label;
    }

    public boolean isOnLadderTile() {
        return onLadderTile;
    }

    public void setOnLadderTile(boolean onLadderTile) {
        this.onLadderTile = onLadderTile;
    }

    public boolean isOnSnakeTile() {
        return onSnakeTile;
    }

    public void setOnSnakeTile(boolean onSnakeTile) {
        this.onSnakeTile = onSnakeTile;
    }

    public boolean isLocked() {
        return locked;
    }

    public void toggleLocked() {
        this.locked = !this.locked;
    }

    public Image getToken() {
        return token;
    }

    public ImageView getTokenFrame () {
        return pToken;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public static AudioClip getAudioladder () {return audioLadder;}

    public static AudioClip getAudioSnake () {return audioSnake;}

    public static AudioClip getAudioWin() {
        return audioWin;
    }

    // Player Movement and Animations
    public void MovePlayer(int moveBy){
        Thread movementThread = new Thread(new movement(this, moveBy));
        movementThread.start();
    }
}

class movement implements Runnable{
    private Player player;
    private int moveBy;
    movement(Player player, int moveBy){
        this.player = player;
        this.moveBy = moveBy;
    }
    @Override
    public void run() {

        try {
            Controller.getArrowFrame().setVisible(false);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (moveBy != 1 && player.getTile().getNum() == 0 && player.isLocked()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (moveBy == 1 && player.getTile().getNum() == 0 && player.isLocked()) {
            player.toggleLocked();
        }

        if (!player.isLocked()) {
            int source = player.getTile().getNum();
            int dest;

            if (player.getTile().getNum() + moveBy > 100)
                return;

            dest = player.getTile().getNum() + moveBy;

            System.out.println("dest "+player.getName()+ " : " + dest);
            int current = source;
            TranslateTransition translate;
            System.out.println("CURRENTLY "+player.getName()+ " AT:" + current);
            while (current != dest) {
                translate = new TranslateTransition();
                translate.setNode(player.getTokenFrame());

                if (current % 10 == 0) {
                    translate.setByY(-60);

                } else {
                    if ((current / 10) % 2 == 0) {
                        translate.setByX(60);
                    } else {
                        translate.setByX(-60);
                    }
                }
                translate.play();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                current++;
                System.out.println("CURRENTLY "+player.getName()+ " AT:" + current);
            }

            player.setTile(Tile.TileArray.get(dest));

            if (player.getTile().getSnake() != null) {
                player.setOnSnakeTile(true);
                dest = player.getTile().getSnake().getTail();

                translate = new TranslateTransition();
                translate.setNode(player.getTokenFrame());
                translate.setFromX(player.getTokenFrame().getTranslateX());
                translate.setFromY(player.getTokenFrame().getTranslateY());
                translate.setToX(Tile.getTile(dest).getPlayerX(player));
                translate.setToY(Tile.getTile(dest).getPlayerY(player));
                translate.setDuration(Duration.seconds(2));
                translate.play();
                player.getAudioSnake().play();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (player.getTile().getLadder() != null) {
                player.setOnLadderTile(true);
                dest = player.getTile().getLadder().getTop();

                translate = new TranslateTransition();
                translate.setNode(player.getTokenFrame());
                translate.setFromX(player.getTokenFrame().getTranslateX());
                translate.setFromY(player.getTokenFrame().getTranslateY());
                translate.setToX(Tile.getTile(dest).getPlayerX(player));
                translate.setToY(Tile.getTile(dest).getPlayerY(player));
                translate.setDuration(Duration.seconds(2));
                translate.play();
                player.getAudioladder().play();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            player.setTile(Tile.TileArray.get(dest));

            if (player.getTile().getNum() == 100) {
                System.out.println("WINNER is = " + player.getName());
                try {
                    Pane root = FXMLLoader.load(getClass().getResource("winScreen.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("styleWin.css").toExternalForm());

                    Platform.runLater(() -> {

                        showLabel(root);
                        Stage winStage = new Stage();
                        winStage.getIcons().add(new Image("logo.png"));
                        winStage.setTitle(player.getName()+ " Won !!!");
                        winStage.setScene(scene);
                        winStage.centerOnScreen();
                        winStage.setResizable(false);
                        winStage.show();
                        player.getAudioWin().setCycleCount(AudioClip.INDEFINITE);
                        player.getAudioWin().play();
                        Controller.getGameStage().close();
                            }
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Controller.getArrowFrame().setVisible(true);
        labelColorToggle();
    }

    public void labelColorToggle(){
        if (Player.getP1label().getTextFill().equals(Color.BLUE)){
            Player.getP1label().setTextFill(Color.WHITE);
            Player.getP2label().setTextFill(Color.RED);
        }
        else {
            Player.getP2label().setTextFill(Color.WHITE);
            Player.getP1label().setTextFill(Color.BLUE);
        }
    }

    public void showLabel(Pane root){
        Label winLbl = new Label();
        winLbl.setTranslateX(49);
        winLbl.setTranslateY(372);
        winLbl.setPrefWidth(303);
        winLbl.setPrefHeight(35);
        winLbl.setTextAlignment(TextAlignment.CENTER);
        winLbl.setText(player.getName()+" Won!!!");
        root.getChildren().add(winLbl);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMoveBy() {
        return moveBy;
    }

    public void setMoveBy(int moveBy) {
        this.moveBy = moveBy;
    }
}

