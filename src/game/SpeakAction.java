package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SpeakAction extends Action {
    /**
     * The Actor that is to be speak.
     */
    protected Toad toad;

    /**
     * Constructor.
     *
     * @param toad the Actor to speak
     */
    public SpeakAction(Toad toad) {
        this.toad = toad;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.hasCapability(Status.INVINCIBLE)&&(!actor.hasCapability(Status.HAVE_WRENCH))){
            return "Toad said: " + toad.giveRandomTalk();
        }
        else if (actor.hasCapability(Status.INVINCIBLE)){
            return "Toad said: " + toad.noTalkPowerStar();
        }
        else if (actor.hasCapability(Status.HAVE_WRENCH)){
            return "Toad said: " + toad.noTalkWrench();
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to toad";
    }
}
