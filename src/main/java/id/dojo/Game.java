package id.dojo;

// Class untuk mengontrol jalannya game, mengatur board, snake dll

import id.dojo.enums.Direction;
import id.dojo.models.Point;
import id.dojo.things.Board;
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

    public Board getBoard() {
        return board;
    }

    public void render() throws IOException, InterruptedException {
        while (true){
            board.displayBoard();
            snake.snakeMovement(board,"forward");

            Thread.sleep(200);

            new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
        }

    }

    private Boolean isEmpty(Point point){
        return board.getBoard().get(point.getX()).get(point.getY()).getThing() == null;
    }

    public void movement(){
//        Direction direction = snake.getRandomDirection();
//        Point forward = snake.checkDirection("forward");
//        Point left = snake.checkDirection("left");
//        Point right = snake.checkDirection("right");
//
//        if (isEmpty(forward) && direction == Direction.FORWARD){
//            snake.snakeMovement(board, "forward");
//        }else if(isEmpty(left) && direction == Direction.LEFT){
//            snake.snakeMovement(board, "left");
//        } else if (isEmpty(right) && direction == Direction.RIGHT) {
//            snake.snakeMovement(board, "right");
//        }
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
