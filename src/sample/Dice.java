package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class Dice extends Button {
    private int number;
    private final Image dice1 = new Image("dice1.png");
    private final Image dice2 = new Image("dice2.png");
    private final Image dice3 = new Image("dice3.png");
    private final Image dice4 = new Image("dice4.png");
    private final Image dice5 = new Image("dice5.png");
    private final Image dice6 = new Image("dice6.png");
    private final Image transition = new Image("diceRollFinal.gif");
    private Rectangle diceBackground;
    private Image leftPanel;
    private Image rightPanel;
    private ImageView diceFrame = new ImageView(dice1);

    Dice(){
        diceFrame.setFitHeight(80);
        diceFrame.setFitWidth(80);
        diceFrame.setPreserveRatio(true);
        diceFrame.setImage(dice2);
        this.setGraphic(diceFrame);
    }
    public int roll() throws InterruptedException {
        // This is incomplete currently, roll should incorporate everything, including
        // roll animation and initial final state change of the dice imageView.
        this.setNumber(ThreadLocalRandom.current().nextInt(1, 6 + 1));
        System.out.println("Dicenum: "+this.getNumber());

        DiceRoll rollTask = new DiceRoll(this);
        Thread diceAnimationThread = new Thread(rollTask);
        diceAnimationThread.start();

        return number;
    }

    public Image getTransition() {
        return transition;
    }

    public Image getDice1() {
        return dice1;
    }

    public Image getDice2() {
        return dice2;
    }

    public Image getDice3() {
        return dice3;
    }

    public Image getDice4() {
        return dice4;
    }

    public Image getDice5() {
        return dice5;
    }

    public Image getDice6() {
        return dice6;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ImageView getDiceFrame() {
        return diceFrame;
    }

    public void setDiceFrame(ImageView diceFrame) {
        this.diceFrame = diceFrame;
    }
}

class DiceRoll implements Runnable{
    private Dice dice;

    DiceRoll(Dice dice){
        this.dice = dice;
    }

    public int getNum(){
        return dice.getNumber();
    }
    @Override
    public void run(){

        ImageView diceFrame = dice.getDiceFrame();
        Image transition = dice.getTransition();
        int number = dice.getNumber();

        diceFrame.setImage(transition);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (number){
            case 1:
                diceFrame.setImage(dice.getDice1());
                break;
            case 2:
                diceFrame.setImage(dice.getDice2());
                break;
            case 3:
                diceFrame.setImage(dice.getDice3());
                break;
            case 4:
                diceFrame.setImage(dice.getDice4());
                break;
            case 5:
                diceFrame.setImage(dice.getDice5());
                break;
            case 6:
                diceFrame.setImage(dice.getDice6());
                break;
        }
    }
}