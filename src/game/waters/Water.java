package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * represents water in the fountain
 *
 * @author Lim Fluoryynx
 */
public abstract class Water {


    protected Actor consumer;
    private String name;

    /**
     * constructor
     * @param name - name of the water
     */
    public Water(String name) {
        this.name=name;
    }

    /**
     *  update actor's status upon consumption of water
     * @param actor
     */
    public void updateStatus(Actor actor){
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
