package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private int number;
    private Image dice1;
    private Image dice2;
    private Image dice3;
    private Image dice4;
    private Image dice5;
    private Image dice6;
    private Rectangle diceBackground;
    private Image leftPanel;
    private Image rightPanel;

    public int roll(){
        // This is incomplete currently, roll should incorporate everything, including
        // roll animation and initial final state change of the dice imageView.
        number = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        System.out.println(number);
        return number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
