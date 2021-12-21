package sample;

import javafx.scene.image.Image;

public class Snake {
    private final int head;
    private final int tail;
    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}
