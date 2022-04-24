package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.actions.JumpAction;

import java.util.Random;

public abstract class Tree extends Ground implements Jumpable, Destroyable, Resettable {

    private final Random rand = new Random();
    private int age;
    private char displayChar;
    private int jumpRate;
    private int fallDamage;
    private String treeType;
    private static final int CONVERT_DIRT_RATE = 50;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar, int jumpRate, int fallDamage, String treeType) {
        super(displayChar);
        this.age = 0;
        this.displayChar = displayChar;
        this.jumpRate = jumpRate;
        this.fallDamage = fallDamage;
        this.treeType = treeType;
        this.registerInstance(); // add to resetManager
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;
        Actor actor = location.getActor();
        if (actor != null){
            if (actor.hasCapability(Status.INVINCIBLE)){
                breakToDirt(location);
                convertCoin(location);
            }
        }

        if (this.hasCapability(Status.RESET_CALLED)){
            if ((rand.nextInt(100) <= CONVERT_DIRT_RATE)){
                convertToDirt(location);
            }
        }

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_JUMP) || actor.hasCapability(Status.INVINCIBLE) ;
    }

    @Override
    public int getJumpRate() {
        return jumpRate;
    }

    @Override
    public int getFallDamage() {
        return fallDamage;
    }

    @Override
    public String getJumpableType() {
        return treeType;
    }

    public int getAge() {
        return age;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (!location.containsAnActor() && !actor.hasCapability(Status.INVINCIBLE)) {
            return new ActionList(new JumpAction(location, direction, this));
        }
        return new ActionList();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }

    public void convertToDirt(Location currentLocation){
        Dirt dirt = new Dirt();
        currentLocation.setGround(dirt);
    }
}
