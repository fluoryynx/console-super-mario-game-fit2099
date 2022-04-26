package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Enemy;

import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

    private static final String GOOMBA_NAME = "Goomba";
    private static final char GOOMBA_CHAR = 'g';
    private static final int HIT_POINT = 20;
    private static final int HIT_RATE = 50;
    private static final int SUICIDE_RATE = 10;
    private static final int DAMAGE = 10;
    private static final String HIT_VERB = "kick";

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor.
     */
    public Goomba() { super(GOOMBA_NAME,GOOMBA_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE); }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return super.allowableActions(otherActor,direction,map);
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (rand.nextInt(100)<=SUICIDE_RATE){
            map.removeActor(this);
            return new DoNothingAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }

}