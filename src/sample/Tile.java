package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Tile extends Rectangle{

    static ArrayList<Tile> TileArray = new ArrayList<>(101);

    private final int tileSize;
    private final int num;
    private Snake snake;
    private Ladder ladder;
    private final double p1Xoffset = 11.0;
    private final double pYoffset = 27.0;
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

    public static Tile getTile(int tileNum){
        for (Tile t: TileArray){
            if (t.getNum()==tileNum){
                return t;
            }
        }
        System.out.println("Tile of number "+tileNum+" not present.");
        return null;
    }

    public static void addSnake(Snake snake){
        Tile tile = getTile(snake.getHead());

        tile.setSnake(snake);
    }

    public static void addLadder(Ladder ladder){
        Tile tile = getTile(ladder.getBase());

        tile.setLadder(ladder);
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

    public void show(){
        System.out.print(num+" ");
    }


    public double getPlayerY(Player player){
        return this.getTranslateY()+ pYoffset;
    }

    public int getNum() {
        return num;
    }

}
