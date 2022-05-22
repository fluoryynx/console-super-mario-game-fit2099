package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * It is an abstract class that functions as a base class for subclasses (e.g. HealingWater and PowerWater).
 *
 * @author Lim Fluoryynx
 */
public abstract class Water {

    /**
     * the consumer that consumed the water
     */
    protected Actor consumer;

    /**
     * the name of the water
     */
    private String name;

    /**
     * constructor
     * @param name - name of the water
     */
    public Water(String name) {
        this.name=name;
    }

    /**
     * update actor's status upon consumption of water
     * @param actor the actor that consumed the water
     * @throws IllegalArgumentException if the actor is null
     */
    public void updateStatus(Actor actor){
        if (actor == null){
            throw new IllegalArgumentException("The input parameter (i.e., actor) cannot be null");
        }

        this.consumer=actor;
    }

    /**
     * returns the name of water in string format
     * @return name of water in string format
     */
    @Override
    public String toString() {
        return this.name;
    }
}
