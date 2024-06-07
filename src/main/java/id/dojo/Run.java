package id.dojo;

import id.dojo.enums.Direction;
import id.dojo.things.Snake;

import java.io.IOException;

public class Run {
    static Snake snake;
    static Game game;

    static {
        System.loadLibrary("native");

        Thread runControl = new Thread(new Runnable() {
            @Override
            public void run() {
                controls();
            }
        });
        runControl.start();
    }

    public static native void controls();

    public static void controlUp(){
        if (snake.getDirecton() == Direction.RIGHT){
            snake.snakeMovement(game.getBoard(),"left");
        }else if (snake.getDirecton() == Direction.LEFT){
            snake.snakeMovement(game.getBoard(),"right");
        }
    }

    public static void controlDown(){
        if (snake.getDirecton() == Direction.RIGHT){
            snake.snakeMovement(game.getBoard(),"right");
        }else if (snake.getDirecton() == Direction.LEFT){
            snake.snakeMovement(game.getBoard(),"left");
        }
    }

    public static void controlLeft(){
        if (snake.getDirecton() == Direction.UP){
            snake.snakeMovement(game.getBoard(),"left");
        }else if (snake.getDirecton() == Direction.DOWN){
            snake.snakeMovement(game.getBoard(),"right");
        }
    }

    public static void controlRight(){
        if (snake.getDirecton() == Direction.UP){
            snake.snakeMovement(game.getBoard(),"right");
        }else if (snake.getDirecton() == Direction.DOWN){
            snake.snakeMovement(game.getBoard(),"left");
        }
    }

    public static void main(String[] args) {
//        controls();

        int row = 30;
        int col = 20;
        int posX = 8;
        int posY = 8;

        snake = Snake.getBuilder()
                .setName("Anaconda")
                .setAppearance(" o ")
                .setPosition(posX,posY)
                .setSize(7)
                .build();

        snake.generateBody();

        game = Game.getBuilder()
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