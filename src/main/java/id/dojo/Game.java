package id.dojo;

// Class untuk mengontrol jalannya game, mengatur board, snake dll

import id.dojo.enums.Direction;
import id.dojo.models.Point;
import id.dojo.things.*;

import java.io.IOException;
import java.util.List;

public class Game {
    private final Board board;
    private List<Wall> walls;
    private Snake snake;
    private Fruit fruit;
    private int speed;

    public Game(Builder builder){
        this.board = builder.board;
        this.walls = builder.walls;
        this.snake = builder.snake;
        this.speed = builder.speed;
        this.fruit = builder.fruit;
    }

    public Board getBoard() {
        return board;
    }

    public void render() throws IOException, InterruptedException {
        boolean isRunning = true;
        while (true){
            board.displayBoard();
            snake.snakeMovement(board,"forward");

            List<Thing> things = board.getBoard().get(snake.getHead().getX()).get(snake.getHead().getY()).getThing();
            System.out.println("Head X : " + snake.getHead().getX());
            System.out.println("Head Y : " + snake.getHead().getY());
            System.out.println("Posisi Buah : " + fruit.getPosition().getX() + ", " + fruit.getPosition().getY());
            System.out.println("Ukuran ular : " + (snake.getBody().size() + 1));

            if (things.size() > 1){
                for (Thing thing : things){
                    if (thing instanceof Wall){
                        System.out.println("NABRAK");
                        isRunning = false;
                        break;
                    } else if (thing instanceof Fruit) {
                        System.out.println("BUAH");
                        snake.setBody();
                        fruit.putNewFruit(board, snake);
                        System.out.println(snake.getBody().size());
                    }
                }
            }

            if (!isRunning) break;

            Thread.sleep(250);

            new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
        }

    }

    public boolean isHitWall(){
        int row = board.getRow();
        int col = board.getCol();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row -1 || j ==0 || j == col -1){
                    if (snake.getHead().getX() == i && snake.getHead().getY() == j)
                        return true;
                }
            }
        }

        return false;
    }

    public boolean checkCurrentCell(){
        List<Thing> things = board.getBoard().get(snake.getHead().getX()).get(snake.getHead().getY()).getThing();

        if (things != null){
            if (things.size() > 1){
                for (Thing thing : things){
                    if (thing instanceof Wall){
                        System.out.println("NABRAK");
                        return true;
                    } else if (thing instanceof Fruit) {
                        System.out.println("BUAH");
                        snake.setBody();
                        fruit.putNewFruit(board, snake);
                        return false;
                    }
                }
            }
        }

        return false;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        Board board;
        List<Wall> walls;
        Snake snake;
        Fruit fruit;
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
                        board.putObject(new Point(i,j),new Wall("wall", " * "));
                    }

                }
            }
            return this;
        }

        public Builder createSnake(Snake snake){
            this.snake = snake;
            return this;
        }

        public Builder creatFruit(Fruit fruit){
            this.fruit = fruit;
            return this;
        }

        // Dipakai untuk membuat objek ular dan fruit
        public Builder generatePopulation(){
            board.putObject(snake.getHead(), snake);
            board.putObject(fruit.getPosition(), fruit);
            return this;
        }

        public Game build(){
            return new Game(this);
        }

    }
}
