package sample;

import javafx.scene.image.Image;

public class Player {
    private final String name;
    private Image token;
    private Tile tile;
    private final String color;

    public Player(String color, String name, Tile tile) {
        this.tile = tile;
        this.color = color;
        this.name = name;
        if (color.equals("BLUE")){
            token = new Image("blueToken.png");
        }
        else{
            token = new Image("redToken.png");
        }
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
