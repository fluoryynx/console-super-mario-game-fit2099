package game.grounds.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.TakeWaterAction;
import game.waters.Water;

public abstract class Fountain extends Ground {
    private int content; // amount of water
    private String name;
    private int currentTurn;
    private int turnWhenWaterRunOut;
    private static final int MAX_CONTENT=10;
    private static final int INITIAL_TURN=0;
    private static final int REPLENISH_TURN=5;
    private static final int EMPTY_INDICATOR=-1;

    public Fountain(char displayChar,String name) {
        super(displayChar);
        this.name=name;
        this.content=MAX_CONTENT;
        this.currentTurn =INITIAL_TURN;
        this.turnWhenWaterRunOut =INITIAL_TURN;
        this.addCapability(Status.IS_FOUNTAIN);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && actor.hasCapability(Status.HAS_BOTTLE) && !this.isEmpty()) {
            return new ActionList(new TakeWaterAction(this.getWater(),this));
        }
        return new ActionList();
    }

    @Override
    public void tick(Location location) {
        this.currentTurn++;

        // print amount of water left
       // System.out.println(this.name + " content: " + this.content);

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

    public void minusContent(){
        this.content-=1;
    }

    public abstract Water getWater();

    public int getContent() {
        return this.content;
    }

    public void setContent(int newContent){
        this.content=newContent;
    }

    public boolean isEmpty(){
        return this.content<=0;
    }

    @Override
    public String toString(){
        return this.name + " (" + this.content + "/10)";
    }
}
