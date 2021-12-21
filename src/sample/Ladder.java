package sample;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;

public class Ladder {
    private final int base;
    private final int top;
    Ladder(int base, int top){
        this.base = base;
        this.top = top;
    }

    public int getBase() {
        return base;
    }

    public int getTop() {
        return top;
    }
}
