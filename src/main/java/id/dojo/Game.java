package id.dojo;

// Class untuk mengontrol jalannya game, mengatur board, snake dll

import id.dojo.models.Point;
import id.dojo.things.Board;
import id.dojo.things.Cell;
import id.dojo.things.Snake;
import id.dojo.things.Wall;

import java.io.IOException;
import java.util.List;

public class Game {
    private final Board board;
    private List<Wall> walls;
    private Snake snake;
    private int speed;

    public Game(Builder builder){
        this.board = builder.board;
        this.walls = builder.walls;
        this.snake = builder.snake;
        this.speed = builder.speed;
    }

    public void render() throws IOException, InterruptedException {
        while (true){
            board.displayBoard();
            snakeMovement();

            Thread.sleep(80);

            new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
        }

    }

    public void snakeMovement(){
        Point foward = snake.checkFoward();
        Cell cell = board.getBoard().get(foward.getX()).get(foward.getY());

        if (cell.getThing() == null){
            snake.stepForward(board);
        }else {
            snake.moveLeft(board);
        }
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        Board board;
        List<Wall> walls;
        Snake snake;
        int speed;

        public Builder createBoard(int row, int col){
            board = new Board("Board", "" , row, col);
            return this;
        }

        public Builder createWalls(){
            // Method untuk membuat dinding area game
            int row = board.getRow();
            int col = board.getCol();

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i == 0 || i == row -1 || j ==0 || j == col -1){
                        board.putObject(new Point(i,j),new Wall("Wall", " * "));
                    }

                }
            }
            return this;
        }

        public Builder createSnake(Snake snake){
            this.snake = snake;
            return this;
        }

        // Dipakai untuk membuat objek ular dan fruit
        public Builder generatePopulation(){
            board.putObject(snake.getHead(), snake);
            return this;
        }

        public Game build(){
            return new Game(this);
        }

    }
}
