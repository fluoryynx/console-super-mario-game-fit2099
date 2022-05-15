package game.actors.enemies.koopas;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackShellAction;
import game.actors.enemies.Enemy;

import java.util.List;

public abstract class GeneralKoopa extends Enemy {

    /**
     * State of Koopa
     */
    private boolean isDefeated = false;

    /**
     * Character of Koopa's shell
     */
    private static final char SHELL_CHAR = 'D';

    /**
     * HitRate of Koopa
     */
    private static final int HIT_RATE = 50;

    /**
     * Damage of Koopa
     */
    private static final int DAMAGE = 30;

    /**
     * Hit verb of Koopa
     */
    private static final String HIT_VERB = "punch";

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit point
     */
    public GeneralKoopa(String name, char displayChar, int hitPoints, int monologueIndexLowerBound, int monologueIndexUpperBound) {
        super(name, displayChar, hitPoints, DAMAGE, HIT_VERB, HIT_RATE,monologueIndexLowerBound,monologueIndexUpperBound);
        this.addCapability(Status.HIDE_IN_SHELL);
    }

    /**
     * Make Koopa can be attacked by Player.
     * When Koopa is not conscious(defeated), it will hide in shell and only the actor with capability HOSTILE_TO_ENEMY && HAVE_WRENCH
     * is allowed smash its shell.
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
        // only the player with wrench can smash its shell when Koopa is defeated
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && isDefeated && otherActor.hasCapability(Status.HAVE_WRENCH)){
            actions.add(new AttackShellAction(this,direction));
        }
        return actions;
    }

    /**
     * Setter of defeated
     * @param defeated the boolean to assign isDefeated
     */
    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    /**
     * Select and return an action to perform on the current turn.
     * If Koopa is not conscious anymore, it is defeated.
     * At this time, it will turn into shell states and all the behaviors will be removed.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location currentLocation = map.locationOf(this);
        List<Item> items = currentLocation.getItems();
        int fireCounter = 0;
        for (Item item: items){
            if (item.hasCapability(Status.FIRE_DAMAGE)){
                fireCounter += 1;
            }
        }
        if (fireCounter>0){
            this.hurt(20*fireCounter);
        }
        if(!this.isConscious()){ // 20
            setDefeated(true);
            this.setDisplayChar(SHELL_CHAR);
            behaviours.remove(FIRST_PRIORITY);
            behaviours.remove(SECOND_PRIORITY);
            behaviours.remove(THIRD_PRIORITY);
            return new DoNothingAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
