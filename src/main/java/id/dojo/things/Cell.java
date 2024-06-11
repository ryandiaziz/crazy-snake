package id.dojo.things;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Thing{
    private final int row;
    private final int col;
    private List<Thing> thing;

    public Cell(String name, String appearance, int row, int col) {
        super(name, appearance);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<Thing> getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        if (this.thing == null){
            List<Thing> newThing = new ArrayList<>();
            newThing.add(thing);
            this.thing = newThing;
        }else {
            this.thing.add(thing);
        }
    }

    public void removeThing(){
        this.thing = null;
    }

    public void displayCell(){
        if(thing != null){
            System.out.print(thing.getFirst().getAppearance());
        }else{
            System.out.print("   ");
        }
    }
}
