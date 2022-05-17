package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.waters.Water;

import java.util.ArrayList;
import java.util.List;

/**
 * represents magical bottle in the game that can contains unlimited water
 *  permanent item that cannot be dropped or picked up.
 *   This bottle can be filled with water, and player can drink/consume the water inside the bottle.
 *   Each water will give a unique effect depending on its original source
 *
 *   @Author Lim Fluoryynx
 */
public class Bottle extends Item {

    /**
     * content in the bottle
     */
    private static List<Water> content = new ArrayList<>();

    /**
     * instance of the Bottle class
     */
    private static Bottle instance;

    /**
     * name of bottle
     */
    private static final String ITEM_NAME="bottle";

    /**
     * display character of bottle
     */
    private static final char ITEM_CHARACTER='b';

    /**
     * bottle is not portable
     */
    private static final boolean ITEM_PORTABLE=false;

    /**
     * constructor
     */
    public Bottle() {
        super(ITEM_NAME,ITEM_CHARACTER, ITEM_PORTABLE);
        content = new ArrayList<>();
        this.addCapability(Status.HAS_BOTTLE);
    }

    /**
     * Get the singleton instance of bottle
     * @return Bottle singleton instance
     */
    public static Bottle getInstance(){
        if(instance == null){
            instance = new Bottle();
        }
        return instance;
    }

    /**
     * push water into the list of content
     * @param water
     */
    public void addContent(Water water) {
        this.content.add(water);
    }

    /**
     * pop water from list of content
     * update status of player according to type of water to give player effects
     * @param actor
     */
    public void minusContent(Actor actor) {
        Water drankWater=content.get(content.size()-1);
        drankWater.updateStatus(actor);
        this.content.remove(drankWater);
    }

    /**
     * return list of content of the bottle
     * @return content
     */
    public List<Water> getContent() {
        return this.content;
    }

    /**
     * get the last water from the list of content
     * @return
     */
    public Water getLast(){
        return this.content.get(content.size()-1);
    }

    /**
     *
     * @return a String . for example: " Bottle [Healing water, Power water]"
     */
    @Override
    public String toString() {
        return "Bottle "+ this.content + "";
    }
}

