package game.grounds.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.TakeWaterAction;
import game.waters.Water;

/**
 * This class is an abstract class extended from the Ground class to provide blueprint for fountains
 * such as PowerFountain and HealthFountain classes.
 *
 * @author Lim Fluoryynx
 */

public abstract class Fountain extends Ground {

    /**
     * amount of water in the fountain
     */
    private int content;

    /**
     * name of the fountain
     */
    private String name;

    /**
     * current turn in the game
     */
    private int currentTurn;

    /**
     * value of turn when fountain content = 0
     */
    private int turnWhenWaterRunOut;

    /**
     * maximum amount of water fountain can have
     */
    private static final int MAX_CONTENT=10;

    /**
     * fountain's initial turn
     */
    private static final int INITIAL_TURN=0;

    /**
     * number of turns required for fountain to replenish it's content back to 10 from 0
     */
    private static final int REPLENISH_TURN=5;

    /**
     * to indicate fountain is empty
     */
    private static final int EMPTY_INDICATOR=-1;

    /**
     * constructor
     * @param displayChar - display character of fountain
     * @param name - name of fountain
     */
    public Fountain(char displayChar,String name) {
        super(displayChar);
        this.name=name;
        this.content=MAX_CONTENT;
        this.currentTurn =INITIAL_TURN;
        this.turnWhenWaterRunOut =INITIAL_TURN;
        this.addCapability(Status.IS_FOUNTAIN);
    }

    /**
     * enable player to take water from it when player stands on it ( if player has bottle)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && actor.hasCapability(Status.HAS_BOTTLE) && !this.isEmpty()) {
            return new ActionList(new TakeWaterAction(this.getWater(),this));
        }
        return new ActionList();
    }

    /**
     * to keep track of turns in the game
     * deduct one slot of water from the fountain when refilled by player or drank by enemies
     * store the value of current turn once content of the fountain becomes 0
     * reset the fountain content back to maximum (10) when the difference between current turn and turn where water run out is 5
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        this.currentTurn++;

        if (this.hasCapability(Status.DRANK_BY_ENEMY)){
            this.minusContent();
            this.removeCapability(Status.DRANK_BY_ENEMY);
        }

        if (this.content == 0){
            this.addCapability(Status.IS_EMPTY);
            this.turnWhenWaterRunOut =this.currentTurn;
            this.content=EMPTY_INDICATOR;
        }

        if (this.content==EMPTY_INDICATOR && (this.currentTurn - this.turnWhenWaterRunOut ==REPLENISH_TURN)){
            this.turnWhenWaterRunOut =INITIAL_TURN;
            this.setContent(MAX_CONTENT);
            this.removeCapability(Status.IS_EMPTY);
        }

    }

    /**
     * deduct one water slot from the fountain
     */
    public void minusContent(){
        this.content-=1;
    }

    /**
     *
     * @return water of the fountain
     */
    public abstract Water getWater();

    /**
     * return the content of water in the fountain
     * @return content
     */
    public int getContent() {
        return this.content;
    }

    /**
     * to set the content of the fountain
     * @param newContent the number of water for the fountain
     */
    public void setContent(int newContent){
        this.content=newContent;
    }

    /**
     * return true if fountain content is <=0
     * @return true if fountain content is less than or equal to 0
     */
    public boolean isEmpty(){
        return this.content<=0;
    }

    /**
     * return a string including the fountain name and remaining content
     * @return a string . for example " Health fountain (10/10)"
     */
    @Override
    public String toString(){
        return this.name + " (" + this.content + "/10)";
    }
}
