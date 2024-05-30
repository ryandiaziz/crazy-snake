package id.dojo.things;

import id.dojo.Game;
import id.dojo.models.Point;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Snake extends Thing implements AnimalBehavior{
    // Besar ular termaksud dengan kepalanya
    private Point head;
    private int size;
    private List<Point> body;

    public Snake(Builder builder) {
        super(builder.getName(), builder.getAppearance());
        this.head = builder.getPosition();
        this.size = builder.getSize();

        body = new ArrayList<>();
    }

    public void generateBody(){
        for (int i = 1; i <= size - 1; i++) {
            body.add(new Point(head.getX(),head.getY() - i));
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

    @Override
    public void stepForward(Board board) {
        int xBefore = head.getX();
        int yBefore = head.getY();

        board.putObject(head, null);
        for (Point bodPos : body){
            board.putObject(bodPos, null);
        }

        setHead(checkFoward());

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
    public Point checkFoward() {
        int xPos = head.getX();
        int yPos = head.getY();

        if (head.getX() - 1 == body.getFirst().getX()){
            xPos++;
        } else if (head.getY() - 1 == body.getFirst().getY()){
            yPos++;
        }else if (head.getY() + 1 == body.getFirst().getY()){
            yPos--;
        }else if (head.getX() + 1 == body.getFirst().getX()){
            xPos--;
        }

        return new Point(xPos, yPos);
    }

    @Override
    public void moveLeft(Board board) {
        int xBefore = head.getX();
        int yBefore = head.getY();

        board.putObject(head, null);
        for (Point bodPos : body){
            board.putObject(bodPos, null);
        }

        setHead(checkLeft());

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
//        board.putObject(head, null);
//
//        for (Point bodPos : body){
//            board.putObject(bodPos, null);
//        }
//
//        head.setX(head.getX() - 1);
//        board.putObject(head, this);
//
//        for (Point bodPos : body){
//            if (bodPos.getY() == (head.getY())){
//                bodPos.setX(bodPos.getX() - 1);
//                board.putObject(bodPos,this);
//                continue;
//            }
//            bodPos.setY(bodPos.getY()+1);
//            board.putObject(bodPos, this);
//        }
    }

    @Override
    public Point checkLeft() {
        int xPos = head.getX();
        int yPos = head.getY();

        if (head.getX() - 1 == body.getFirst().getX()){
            yPos++;
        } else if (head.getY() - 1 == body.getFirst().getY()){
            xPos--;
        }else if (head.getY() + 1 == body.getFirst().getY()){
            xPos++;
        }else if (head.getX() + 1 == body.getFirst().getX()){
            yPos--;
        }

        return new Point(xPos, yPos);
    }

    @Override
    public void moveRight(Board board) {

    }

    @Override
    public Point checkRight() {
        return null;
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
