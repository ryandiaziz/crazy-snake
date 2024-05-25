package id.dojo.things;

import id.dojo.Game;
import id.dojo.models.Point;

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
        // Implementasi
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
        board.putObject(head, null);

        for (Point bodPos : body){
            board.putObject(bodPos, null);
        }

        int yPos = head.getY();
        head.setY(yPos+1);
        board.putObject(head, this);

        for (Point bodPos : body){
            int bodPosY = bodPos.getY();
            bodPos.setY(bodPosY+1);
            board.putObject(bodPos,this);
        }


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
