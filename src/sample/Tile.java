package sample;

import javafx.scene.shape.Rectangle;

public class Tile {
    private final int num;
    private boolean hasSnakeMouth;
    private boolean hasLadderBase;
    Rectangle rectangle;
    Tile(int number, Rectangle rect){
        num = number;
        rectangle = rect;
    }
    Tile(int number, boolean snakemouth, boolean ladderbase, Rectangle rect){
        num = number;
        hasSnakeMouth = snakemouth;
        hasLadderBase = ladderbase;
        rectangle = rect;
    }

    public boolean isHasSnakeMouth() {
        return hasSnakeMouth;
    }

    public void setHasSnakeMouth(boolean hasSnakeMouth) {
        this.hasSnakeMouth = hasSnakeMouth;
    }

    public boolean isHasLadderBase() {
        return hasLadderBase;
    }

    public void setHasLadderBase(boolean hasLadderBase) {
        this.hasLadderBase = hasLadderBase;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
