package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Player {
    private final String name;
    private Image token;
    private Tile tile;
    private final String color;
    private Pane root;

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

        p2label.setTranslateX(460);
        p2label.setTranslateY(720);

        root.getChildren().add(p1label);
        root.getChildren().add(p2label);
    }

    public void showToken(Tile waitingTile){
        ImageView pToken = new ImageView();

        pToken.setImage(this.getToken());
        if (this.color.equals("BLUE")){
            pToken.setFitHeight(36);
        }else {
            pToken.setFitHeight(35);
        }
        pToken.setFitWidth(20);

        pToken.setTranslateY(waitingTile.getPlayerY(this));
        pToken.setTranslateX(waitingTile.getPlayerX(this));

        root.getChildren().add(pToken);
        pToken.toFront();
    }

    public Image getToken() {
        return token;
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
}
