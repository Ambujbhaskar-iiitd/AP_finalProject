package sample;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Player {
    private final String name;
    private Image token;
    private ImageView pToken;
    private Tile tile;
    private final String color;
    private Pane root;
    private boolean locked=true;

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
        this.pToken = new ImageView();

        this.pToken.setImage(this.getToken());
        if (this.color.equals("BLUE")){
            this.pToken.setFitHeight(36);
        }else {
            this.pToken.setFitHeight(35);
        }
        this.pToken.setFitWidth(20);

        this.pToken.setTranslateY(waitingTile.getPlayerY(this));
        this.pToken.setTranslateX(waitingTile.getPlayerX(this));

        root.getChildren().add(this.pToken);
        this.pToken.toFront();
    }

    public boolean isLocked() {
        return locked;
    }

    public void toggleLocked() {
        if (!this.locked){
            this.locked = true;
        }
        else{
            this.locked = false;
        }
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
    public void run(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (moveBy==1 && player.getTile().getNum()==0 && player.isLocked()) player.toggleLocked();

        if (!player.isLocked()){
            int source = player.getTile().getNum();

            int dest;
            if (player.getTile().getNum()+moveBy>100)
                return;
            else dest=player.getTile().getNum()+moveBy;

            System.out.println("dest: "+ dest);
            int current=source;
            TranslateTransition translate;


            while (current!=dest){
                translate = new TranslateTransition();
                translate.setNode(player.getTokenFrame());
                System.out.println("CURRENTLY AT:"+current);

                if (current%10==0){
//                    player.getTokenFrame().setTranslateY(player.getTokenFrame().getTranslateY()-60);
//                    translate.setFromY(player.getTokenFrame().getTranslateY());
//                    translate.setToY(player.getTokenFrame().getTranslateY()-60);
                    translate.setByY(-60);

                }
                else{
                    if ((current/10)%2==0){
//                        player.getTokenFrame().setTranslateX(player.getTokenFrame().getTranslateX()+60);
//                        translate.setFromX(player.getTokenFrame().getTranslateX());
//                        translate.setToX(player.getTokenFrame().getTranslateX()+60);
                        translate.setByX(60);
                    }
                    else {
//                        player.getTokenFrame().setTranslateX(player.getTokenFrame().getTranslateX()-60);
//                        translate.setFromX(player.getTokenFrame().getTranslateX());
//                        translate.setToX(player.getTokenFrame().getTranslateX()-60);
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
            }
            player.setTile(Controller.TileArray.get(dest));
//           if (this.tile.getNum()==100)
//               System.out.println("WINNERS "+this.getName());
        }
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

