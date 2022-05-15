package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.waters.Water;

import java.util.ArrayList;
import java.util.List;

public class Bottle extends Item {

    private static List<Water> content = new ArrayList<>();
    private static Bottle instance;
    private static final String ITEM_NAME="bottle";
    private static final char ITEM_CHARACTER='b';
    private static final boolean ITEM_PORTABLE=false;

    public Bottle() {
        super(ITEM_NAME,ITEM_CHARACTER, ITEM_PORTABLE);
        content = new ArrayList<>();
        this.addCapability(Status.HAS_BOTTLE);
    }

    public static Bottle getInstance(){
        if(instance == null){
            instance = new Bottle();
        }
        return instance;
    }

    public void addContent(Water water) {
        this.content.add(water);
    }

    public void minusContent(Actor actor) {
        Water drankWater=content.get(content.size()-1);
        drankWater.updateStatus(actor);
        this.content.remove(drankWater);
    }

    public List<Water> getContent() {
        return this.content;
    }

    public Water getLast(){
        return this.content.get(content.size()-1);
    }

    @Override
    public String toString() {
        return "Bottle "+ this.content + "";
    }
}

