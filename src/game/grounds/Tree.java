package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.JumpAction;
import game.Jumpable;
import game.Status;

import java.util.Random;

public abstract class Tree extends Ground implements Jumpable {

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

    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_JUMP) || actor.hasCapability(Status.POWER_STAR) ;
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
        if (!location.containsAnActor() && !actor.hasCapability(Status.POWER_STAR)) {
            return new ActionList(new JumpAction(location, direction, this));
        }
        return new ActionList();
    }

    public void convertToDirt(Location currentLocation){
        Dirt dirt = new Dirt();
        currentLocation.setGround(dirt);
    }
}
