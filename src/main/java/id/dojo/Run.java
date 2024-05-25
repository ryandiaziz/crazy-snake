package id.dojo;

public class Run {
    public static void main(String[] args) {
        int row = 30;
        int col = 20;

        Game game = Game.getBuilder().createBoard(row,col).createWalls().build();
        game.render();
    }
}