package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    private final int tileSize;
    private final int num;
    private Snake snake;
    private Ladder ladder;
    private final double p1Xoffset = 11.0;
    private final double pYoffset = 30.0;
    private final double p2Xoffset = 31.0;

    Tile(int TileSize, int number,Color color){
        tileSize = TileSize;
        num = number;
        setFill(color);
        setWidth(TileSize);
        setHeight(TileSize);
    }
    Tile(int TileSize, int number, Snake snake, Ladder ladder){
        tileSize = TileSize;
        num = number;
        this.snake = snake;
        this.ladder = ladder;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    public int getTileSize() {
        return tileSize;
    }

    public double getPlayerX(Player player){
        if (player.getColor().equals("BLUE")){
            // player1
            return  (this.getTranslateX() + p1Xoffset);
        }
        else{
            // player2
            return  (this.getTranslateX() + p2Xoffset);
        }
    }

    public int getNum() {
        return num;
    }

    public double getPlayerY(Player player){
        return this.getTranslateY()+ pYoffset;
    }

}
