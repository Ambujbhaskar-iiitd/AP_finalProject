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

    public Image getToken() {
        return token;
    }

    public ImageView getTokenFrame () {return pToken;}

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

    //Animations
    public void MovePlayer(int moveBy){
        if (moveBy==1 && this.tile.getNum()==0 && locked) locked=false;

        if (!locked){
           int source = this.tile.getNum();

           int dest;
           if (this.tile.getNum()+moveBy>100)
           return;
           else dest=this.tile.getNum()+moveBy;

            System.out.println(dest);
           int current=source;
           TranslateTransition translate = new TranslateTransition();
           translate.setNode(this.pToken);
           while (current!=dest){
               System.out.println("CURRENTLY AT:"+current);
               if (current%10==0){
                      this.pToken.setTranslateY(this.pToken.getTranslateY()-60);
//                    translate.setFromY(this.pToken.getTranslateY());
//                    translate.setToY(this.pToken.getTranslateY()-60);
               }
               else{
                   if ((current/10)%2==0){
                       this.pToken.setTranslateX(this.pToken.getTranslateX()+60);
//                       translate.setFromX(this.pToken.getTranslateX());
//                       translate.setToX(this.pToken.getTranslateX()+60);
                   }
                   else {
                       this.pToken.setTranslateX(this.pToken.getTranslateX()-60);
//                       translate.setFromX(this.pToken.getTranslateX());
//                       translate.setToX(this.pToken.getTranslateX()-60);
                   }
               }
               translate.play();
               current++;
           }
           this.tile=Controller.TileArray.get(dest);
//           if (this.tile.getNum()==100)
//               System.out.println("WINNERS "+this.getName());
        }
    }
}

