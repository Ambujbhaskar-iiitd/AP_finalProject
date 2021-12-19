package sample;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    private final int tileSize;
    private final int num;
    private Snake snake;
    private Ladder ladder;
    Tile(int TileSize, int number){
        tileSize = TileSize;
        num = number;
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

}
