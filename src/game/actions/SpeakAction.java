package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Toad;

/**
 * SpeakAction class is a class that allows the actor to have a conversation with toad (friendly NPC) to
 * get some useful information
 * It is extends from its parent class Action.
 *
 * @author Huang GuoYueYang
 */
public class SpeakAction extends Action {

    /**
     * The Actor that is to be speak.
     */
    private Toad toad;

    /**
     * Constructor.
     * @param toad the Actor to speak
     */
    public SpeakAction(Toad toad) {
        this.toad = toad;
    }

    /**
     * Perform the Action.
     * This method is used to have conversation with toad.
     * When actor has/doesn't have certain capability(HAVE_WRENCH/INVINCIBLE), actor can have different conversation with toad.
     * Without HAVE_WRENCH && INVINCIBLE, toad speak any sentence from the default 4 sentences.
     * With HAVE_WRENCH && INVINCIBLE, toad speak any sentence from the default 4 sentences except the sentence about wrench and power star.
     * With HAVE_WRENCH, toad speak any sentence from the default 4 sentences except the sentence about wrench.
     * With INVINCIBLE, toad speak any sentence from the default 4 sentences except the sentence about power star.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.hasCapability(Status.INVINCIBLE)&&(!actor.hasCapability(Status.HAVE_WRENCH))){
            return "Toad said: " + toad.giveRandomTalk();
        }else if(actor.hasCapability(Status.INVINCIBLE)&&(actor.hasCapability(Status.HAVE_WRENCH))){
            return "Toad said: " + toad.noTalkPowerStarAndWrench();
        }
        else if (actor.hasCapability(Status.INVINCIBLE)){
            return "Toad said: " + toad.noTalkPowerStar();
        }
        else if (actor.hasCapability(Status.HAVE_WRENCH)){
            return "Toad said: " + toad.noTalkWrench();
        }
        return null;
    }

    /**
     * Returns a descriptive string: Actor speaks to toad
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to toad";
    }
}
