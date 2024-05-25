package id.dojo;

import id.dojo.things.Snake;

import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        int row = 30;
        int col = 20;
        int posX = 5;
        int posY = 5;

        Snake snake = Snake.getBuilder()
                .setName("Anaconda")
                .setAppearance(" o ")
                .setPosition(posX,posY)
                .setSize(3)
                .build();

        snake.generateBody();

        Game game = Game.getBuilder()
                .createBoard(row,col)
                .createWalls()
                .createSnake(snake)
                .generatePopulation()
                .build();

        try {
            game.render();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}