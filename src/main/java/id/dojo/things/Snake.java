package id.dojo.things;

import id.dojo.enums.Direction;
import id.dojo.models.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends Thing implements AnimalBehavior{
    // Besar ular termaksud dengan kepalanya
    private Point head;
    private int size;
    private List<Point> body;
    private Direction directon = Direction.RIGHT;

    public Snake(Builder builder) {
        super(builder.getName(), builder.getAppearance());
        this.head = builder.getPosition();
        this.size = builder.getSize();

        body = new ArrayList<>();
    }

    public void generateBody(){
        for (int i = 1; i <= size - 1; i++) {
            body.add(new Point(head.getX(), head.getY()+1));
        }
    }

    public Point getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Direction getDirecton() {
        return directon;
    }

    public void setDirecton(Direction directon) {
        this.directon = directon;
    }

    @Override
    public void snakeMovement(Board board, String direction) {
        int xBefore = head.getX();
        int yBefore = head.getY();

        board.putObject(head, null);
        for (Point bodPos : body){
            board.putObject(bodPos, null);
        }

        setHead(checkDirection(direction));

        List<Point> newBody = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            if (i == 0){
                newBody.add(new Point(xBefore, yBefore));
                continue;
            }
            newBody.add(new Point(body.get(i-1).getX(),body.get(i-1).getY()));
        }

        body.removeAll(body);
        body.addAll(newBody);

        board.putObject(head, this);
        for (Point bodPos : body){
            board.putObject(bodPos,this);
        }
    }

    @Override
    public Point checkDirection(String direction) {
        int xPos = head.getX();
        int yPos = head.getY();

        if (head.getX() - 1 == body.getFirst().getX()){
            switch (direction.toLowerCase()){
                case "forward":
                    xPos++;
                    this.directon = Direction.DOWN;
                    break;
                case "left":
                    yPos++;
                    this.directon = Direction.RIGHT;
                    break;
                case "right":
                    yPos--;
                    this.directon = Direction.LEFT;
                    break;
            }
        } else if (head.getY() - 1 == body.getFirst().getY()){
            switch (direction.toLowerCase()){
                case "forward":
                    yPos++;
                    this.directon = Direction.RIGHT;
                    break;
                case "left":
                    xPos--;
                    this.directon = Direction.UP;
                    break;
                case "right":
                    xPos++;
                    this.directon = Direction.DOWN;
                    break;
            }
        }else if (head.getY() + 1 == body.getFirst().getY()){
            switch (direction.toLowerCase()){
                case "forward":
                    yPos--;
                    this.directon = Direction.LEFT;
                    break;
                case "left":
                    xPos++;
                    this.directon = Direction.DOWN;
                    break;
                case "right":
                    xPos--;
                    this.directon = Direction.UP;
                    break;
            }
        }else if (head.getX() + 1 == body.getFirst().getX()){
            switch (direction.toLowerCase()){
                case "forward":
                    xPos--;
                    this.directon = Direction.UP;
                    break;
                case "left":
                    yPos--;
                    this.directon = Direction.LEFT;
                    break;
                case "right":
                    yPos++;
                    this.directon = Direction.RIGHT;
                    break;
            }
        }

        return new Point(xPos, yPos);
    }

    public Direction getRandomDirection() {
        Random random = new Random();
        Direction[] directions = Direction.values();
        int randomIndex = random.nextInt(directions.length);
        return directions[randomIndex];
    }

    public static Snake.Builder getBuilder(){
        return new Snake.Builder();
    }

    public static class Builder {
        private int size, posX, posY;
        private String name;
        private String appearance;

        public Builder setSize(int size){
            this.size = size;
            return this;
        }

        public int getSize(){
            return size;
        }

        public Builder setName(String name){
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

        public Point getPosition() {
            return new Point(posX,posY);
        }

        public Builder setPosition(int x, int y){
            posX = x;
            posY = y;

            return this;
        }

        public Snake build(){
            return new Snake(this);
        }
    }
}
