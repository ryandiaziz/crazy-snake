package id.dojo.things;

import id.dojo.enums.Direction;
import id.dojo.models.Point;

import javax.swing.*;

public interface AnimalBehavior {
    void snakeMovement(Board board, String direction);

    Point checkDirection(String direction);

    Direction getRandomDirection();
}
