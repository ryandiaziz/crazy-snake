package id.dojo.things;

import id.dojo.models.Point;

import java.nio.file.attribute.PosixFileAttributes;

public class Fruit extends Thing{
    private Point position;

    public Fruit(Builder builder){
        super(builder.name, builder.appearance);
        position = builder.position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void putNewFruit(Board board, Snake snake){
        board.putObject(position, null);
        Point newPoint;

        while (true){
            boolean isCollapse = false;
            newPoint =  random(board);
            if (snake.getHead().getX() == newPoint.getX() && snake.getHead().getY() == newPoint.getY()) continue;
            for (Point pointBodySnake : snake.getBody()){
                if (pointBodySnake.getX() == newPoint.getX() && pointBodySnake.getY() == newPoint.getY()) {
                    isCollapse = true;
                }
            }
            if (isCollapse) continue;
            break;
        }
        setPosition(newPoint);
        board.putObject(newPoint, this);
    }

    public Point random(Board board){
        int min = 1;
        int max_row = board.getRow() - 1;
        int max_col = board.getCol() - 1;
        int x = (int) (Math.random() * ((max_row - min) + 1)) + min;
        int y = (int) (Math.random() * ((max_col - min) + 1)) + min;

        return new Point(x,y);
    }

    /* >>>> BUILDER CLASS <<<< */
    public static Fruit.Builder getBuilder(){
        return new Fruit.Builder();
    }

    public static class Builder {
        private Point position;
        private String name;
        private String appearance;

        public Builder setPosition(int row, int col){
            int min = 1;
            int max_row = row - 1;
            int max_col = col - 1;
            int x = (int) (Math.random() * ((max_row - min) + 1)) + min;
            int y = (int) (Math.random() * ((max_col - min) + 1)) + min;

            this.position = new Point(x,y);
            return this;
        }

        public Point getPosition() {
            return position;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setAppearance(String appearance){
            this.appearance = appearance;
            return this;
        }

        public String getAppearance() {
            return appearance;
        }

        public Fruit build(){
            return new Fruit(this);
        }
    }
}
