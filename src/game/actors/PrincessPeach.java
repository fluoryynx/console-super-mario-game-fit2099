package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Speakable;
import game.Status;
import game.actions.SpeakAction;
import game.actions.VictoryAction;

public class PrincessPeach extends Actor implements Speakable {
    /**
     * Name of Princess Peach
     */
    private static final String ACTOR_NAME = "Princess Peach";

    /**
     * Character of Princess Peach
     */
    private static final char DISPLAY_CHAR = 'P';

    /**
     * HitPoint of Princess Peach
     */
    private static final int HIT_POINT = 0;

    private int currentTurn;

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super(ACTOR_NAME, DISPLAY_CHAR, HIT_POINT);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " + generateMonologue(0,2));
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HAVE_KEY)){
            actions.add(new VictoryAction());
        }
        return actions;
    }
}
