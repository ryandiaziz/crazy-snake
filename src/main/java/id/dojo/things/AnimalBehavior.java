package id.dojo.things;

import id.dojo.models.Point;

import javax.swing.*;

public interface AnimalBehavior {
    void stepForward(Board board);

    Point checkFoward();

    void moveLeft(Board board);

    Point checkLeft();

    void moveRight(Board board);

    Point checkRight();
}
