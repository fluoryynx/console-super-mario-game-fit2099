package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.*;

import java.util.Random;

public abstract class Tree extends HighGround implements Destroyable, Resettable {

    private final Random rand = new Random();
    private int age;
    private static final int CONVERT_DIRT_RATE = 50;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar, int jumpRate, int fallDamage, String treeType) {
        super(displayChar, jumpRate, fallDamage, treeType);
        this.age = 0;
        this.registerInstance(); // add to resetManager
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;

        if (this.hasCapability(Status.RESET_CALLED)){
            if ((rand.nextInt(100) <= CONVERT_DIRT_RATE)){
                convertToDirt(location);
            }
        }

    }

    public int getAge() {
        return age;
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
