package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

public abstract class Water {

    protected Actor consumer;
    private String name;

    public Water(String name) {
        this.name=name;
    }

    public void updateStatus(Actor actor){
        this.consumer=actor;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
