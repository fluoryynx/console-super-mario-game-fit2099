package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Application;
import game.Status;
import game.behaviours.*;
import game.items.Key;
import java.util.List;

/**
 * Bowser class is a class represents one kind of the enemies in this game.
 * It is a class that extends from the Enemy class.
 * Bowser will stand still, waiting for Mario on the second map. Once Mario stands next to him, Bowser will attack and
 * follow Mario! He will attack whenever possible. Whenever Bowser attacks, a fire will be dropped on the ground that
 * lasts for three turns. That fire will deal 20 damage per turn to anyone.
 * When the Bowser is killed, it will drop a key to unlock Princess Peach's handcuffs.
 * It punches player with 50% hit rate and causes 80 damages.
 * Resetting the game (r command) will move Bowser back to the original position, heal it to maximum, and it will
 * stand there until Mario is within Bowser's attack range.
 * Besides, Bowser also speak every even turn automatically.
 *
 * @author Huang GuoYueYang
 */
public class Bowser extends Enemy {
    /**
     * Name of Bowser
     */
    private static final String BOWSER_NAME = "Bowser";

    /**
     * Character of Bowser
     */
    private static final char BOWSER_CHAR = 'B';

    /**
     * HitPoint of Bowser
     */
    private static final int HIT_POINT = 500;

    /**
     * HitRate of Bowser
     */
    private static final int HIT_RATE = 50;

    /**
     * Damage of Bowser
     */
    private static final int DAMAGE = 80;

    /**
     * Hit verb of Bowser
     */
    private static final String HIT_VERB = "punch";

    /**
     * Default x coordinate of Bowser
     */
    private static final int X_COORDINATE = 3;

    /**
     * Default y coordinate of Bowser
     */
    private static final int Y_COORDINATE = 4;

    /**
     * The lower bound index for the monologue of Bowser
     */
    private static final int MONOLOGUE_INDEX_LOWER_BOUND = 7;

    /**
     * The upper bound index for the monologue of Bowser
     */
    private static final int MONOLOGUE_INDEX_UPPER_BOUND = 10;


    /**
     * Constructor.
     * The monologue belongs to Bowser will be print by using index.
     */
    public Bowser() {
        super(BOWSER_NAME,BOWSER_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE,MONOLOGUE_INDEX_LOWER_BOUND,MONOLOGUE_INDEX_UPPER_BOUND);
        this.behaviours.remove(FOURTH_PRIORITY);
        this.addCapability(Status.DROP_KEY);
    }

    /**
     * In each turn of this game, the current turn counter will increase by 1.
     * When the current turn is divisible by 2, Bowser will speak automatically.
     * The monologue belongs to Bowser will be print by using index.
     * When Bower drinks power water, the damage of it will increase by 15.
     * Once Bowser is dead, a Key will drop.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Generate monologue of Bowser when even turn
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " +
                    generateMonologue(monologueIndexLowerBound,monologueIndexUpperBound));
        }


        if (this.hasCapability(Status.POWER_WATER)) {
            this.damage += EXTRA_DAMAGE;
            this.removeCapability(Status.POWER_WATER);
        }

        // get the damage deal by fire
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

        // When Bowser is killed, a Key will drop
        if (!this.isConscious()){
            currentLocation.addItem(new Key());
            map.removeActor(this);
            return new DoNothingAction();
        }

        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            // If the destination has an actor with capability HOSTILE_TO_ENEMY
            if (destination.getActor()!=null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                // Enemy follow the actor
                behaviours.put(THIRD_PRIORITY,new FollowBehaviour(destination.getActor()));
            }
        }
        // If the game is reset, follow behavior will remove first to make sure it stay at it own position
        if (this.hasCapability(Status.RESET_CALLED)){
            behaviours.remove(THIRD_PRIORITY);
        }

        this.removeCapability(Status.RESET_CALLED);

        // If under successful hit rate, enemy will attack actor in first priority
        if(rand.nextInt(100) <= HIT_RATE){
            this.behaviours.put(FIRST_PRIORITY,new FireAttackBehaviour());
        }

        this.behaviours.put(SECOND_PRIORITY,new DrinkWaterBehaviour());

        if (map.locationOf(this).getGround().hasCapability(Status.IS_EMPTY)){
            this.behaviours.remove(SECOND_PRIORITY);
        }

        // If the action list of enemy is null, enemy do nothing
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Reset this game and add RESET_CALLED to its capability set
     * When the game is reset, Bowser should go back to its original position and heal to maximum.
     */
    @Override
    public void resetInstance() {
        super.resetInstance();
        GameMap currentMap = Application.getSecondGameMap();
        Location targetLocation = currentMap.at(X_COORDINATE,Y_COORDINATE);
        boolean hasActor = targetLocation.containsAnActor();
        if (!hasActor){
            currentMap.moveActor(this, targetLocation);
        }
        // Heal the Bowser to maximum
        this.heal(this.getMaxHp());
    }
}