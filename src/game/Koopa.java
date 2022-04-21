package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Koopa extends Enemy {
    private boolean isDefeated = false;
    private static final String KOOPA_NAME = "Koopa";
    private static final char KOOPA_CHAR = 'K';
    private static final char SHELL_CHAR = 'D';
    private static final int HIT_POINT = 100;
    private static final int HIT_RATE = 50;
    private static final int DAMAGE = 30;
    private static final String HIT_VERB = "punch";

    /**
     * Constructor.
     */
    public Koopa() {
        super(KOOPA_NAME,KOOPA_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE);
        this.addCapability(Status.HIDE_IN_SHELL);
    }

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
        ActionList actions = new ActionList();
        actions = super.allowableActions(otherActor, direction, map);
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && isDefeated && otherActor.hasCapability(Status.HAVE_WRENCH)){
            actions.add(new AttackShellAction(this,direction));
        }
        return actions;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            setDefeated(true);
            this.setDisplayChar(SHELL_CHAR);
            behaviours.remove(FIRST_PRIORITY);
            behaviours.remove(SECOND_PRIORITY);
            behaviours.remove(THIRD_PRIORITY);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
