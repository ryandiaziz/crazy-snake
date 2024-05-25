package id.dojo;

// Class untuk mengontrol jalannya game, mengatur board, snake dll

import id.dojo.things.Board;
import id.dojo.things.Snake;
import id.dojo.things.Wall;

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

    public void render(){
        board.displayBoard();
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

            return this;
        }

        public Game build(){
            return new Game(this);
        }
    }
}
