package sample;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    private final int num;
    private Snake snake;
    private Ladder ladder;
    Tile(int number){
        num = number;
    }
    Tile(int number, Snake snake, Ladder ladder){
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
}
