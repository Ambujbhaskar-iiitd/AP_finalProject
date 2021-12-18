package sample;

import javafx.scene.image.Image;

public class Player {
    private final String name;
    private Image token;
    private Tile tile;
    private final String color;

    public Player(String color, String name) {
        this.color = color;
        this.name = name;
        if (color == "BLUE"){
            token = new Image("blueToken.png");
        }
        else{
            token = new Image("redToken.png");
        }
    }
}
