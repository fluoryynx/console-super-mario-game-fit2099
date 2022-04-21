package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Koopa;
import game.Status;

import java.util.Random;

public class Mature extends Tree{

    private final Random rand = new Random();
    private static final char MATURE_CHAR = 'T';
    private static final int MATURE_JUMP_RATE = 70;
    private static final int MATURE_FALL_DAMAGE = 30;
    private static final String MATURE_TYPE = "Mature tree";
    private static final int SPAWN_KOOPA_RATE = 15;
    private int everyFiveTurnPointer = 0;
    private static final int WITHER_RATE = 20;

    public Mature() {
        super(MATURE_CHAR, MATURE_JUMP_RATE, MATURE_FALL_DAMAGE, MATURE_TYPE);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        everyFiveTurnPointer++;

        // 15 % to spawn koopa if there is no actor at this location
        if ((rand.nextInt(100) <= SPAWN_KOOPA_RATE) && !location.containsAnActor()){
            spawnKoopa(location);
        }

        if (everyFiveTurnPointer == 5){
            int noFertileAvailable = numberOfFertile(location);
            if (noFertileAvailable > 0){
                // it means there is at least one fertile in the surrounding of this tree
                // therefore, we will call the following method to grow a sprout
                changeOneFertileToSprout(location);
            }
            // reset the pointer
            everyFiveTurnPointer = 0;
        }

        if ((rand.nextInt(100) <= WITHER_RATE)){
            changeToDirt(location);
        }
    }

    public void spawnKoopa(Location currentLocation){
        Koopa koopa = new Koopa();
        // currentLocation.addActor(koopa);
    }

    public int numberOfFertile(Location currentLocation){
        int pointer = 0;
        for (Exit exit: currentLocation.getExits()){
            if (exit.getDestination().getGround().hasCapability(Status.IS_FERTILE)){
                pointer++;
            }
        }
        return pointer;
    }

    public void changeOneFertileToSprout(Location currentLocation){
        boolean changedToSprout = false;
        while (!changedToSprout){
            int amountOfExit = currentLocation.getExits().size();
            int index = rand.nextInt(amountOfExit);
            Exit exit = currentLocation.getExits().get(index);
            Ground currentGround = exit.getDestination().getGround();
            if (currentGround.hasCapability(Status.IS_FERTILE)){
                exit.getDestination().setGround(new Sprout());
                changedToSprout = true;
            }
        }
    }

    public void changeToDirt(Location currentLocation){
        Dirt dirt = new Dirt();
        currentLocation.setGround(dirt);
    }
}
