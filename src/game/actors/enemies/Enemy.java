package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Speakable;
import game.Status;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviours.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Enemy is an abstract class represents the enemies' base of this game.
 * It is a class that extends from the Actor.
 * Enemy will attack and follow player automatically, when no player is beside, they will wander around.
 * There are five types of enemies in this game, which is Goomba, PiranhaPlant, Bowser, Flying Koopa and Koopa.
 * Besides, all enemies also speak every even turn automatically.
 *
 * @author Kuah Jia Chen, Huang GuoYueYang
 */
public abstract class Enemy extends Actor implements Resettable, Speakable {

    /**
     * Hashmap to store behaviors.
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();  // list of behaviours store in hashmap

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the first priority
     */
    protected static final int FIRST_PRIORITY = 1; // key of hashmap attack

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the second priority
     */
    protected static final int SECOND_PRIORITY = 2; // key of hashmap drink water

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the third priority
     */
    protected static final int THIRD_PRIORITY = 3; // key of hashmap follow

    /**
     * A constant integer that will be used as a key in the hash map to indicate which
     * behaviour is the forth priority
     */
    protected static final int FOURTH_PRIORITY = 4; // key of hashmap wander around

    /**
     * Damage done by this enemy instance
     */
    protected int damage;  // deduct in hit point of enemy

    /**
     * Attack verb
     */
    private String verb; // Attack verb

    /**
     * The hit rate of this enemy instance
     */
    private int hitRate; // Possibility of enemy hit actor


    /**
     * Extra damage gained
     */
    protected static final int EXTRA_DAMAGE=15;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Current turn of this game
     */
    protected int currentTurn;

    /**
     * Lower boundary of monologue index
     */
    protected int monologueIndexLowerBound;

    /**
     * Upper boundary of monologue index
     */
    protected int monologueIndexUpperBound;

    /**
     * Constructor.
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit point
     * @param damage      the deduction in hit point of enemy
     * @param verb        the attack verb of enemy
     * @param hitRate     the possibility of enemy hit actor
     * @param monologueIndexLowerBound   the upper bound of monologue index
     * @param monologueIndexUpperBound   the lower bound of monologue index
     */
    public Enemy(String name, char displayChar, int hitPoints, int damage, String verb, int hitRate, int monologueIndexLowerBound, int monologueIndexUpperBound) {
        super(name, displayChar, hitPoints);
        this.damage = damage;
        this.behaviours.put(FOURTH_PRIORITY,new WanderBehaviour());
        this.verb = verb;
        this.addCapability(Status.IS_ENEMY);
        this.hitRate = hitRate;
        this.registerInstance(); // append to the array list in ResetManager
        this.monologueIndexLowerBound = monologueIndexLowerBound;
        this.monologueIndexUpperBound = monologueIndexUpperBound;
    }

    /**
     * In each turn of this game, the current turn counter will increase by 1.
     * When the current turn is divisible by 2, Enemies will speak automatically.
     * The monologue belongs to corresponding Enemies will be print by using index.
     * For each exits in this game, if the destination is not null and the actor around the enemy has capability HOSTILE_TO_ENEMY,
     * the enemy will follow the actor in third priority.
     * If the actor is around enemy, enemy will attack actor in first priority.
     * If the fountain is around enemy, enemy will drink water in second priority.
     * Other than that, enemy will wander around in forth priority then return do nothing action
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " +
                    generateMonologue(monologueIndexLowerBound,monologueIndexUpperBound));
        }

        if (this.hasCapability(Status.POWER_WATER)) {
            this.damage+=EXTRA_DAMAGE;
            this.removeCapability(Status.POWER_WATER);
        }
      //  display.println("enemy damage: " + this.damage+ "");

        // when the enemy has this capability, it means the reset action is selected by the user
        // hence it will be removed from the map
        if (this.hasCapability(Status.RESET_CALLED)){
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

        // If under successful hit rate, enemy will attack actor in first priority
        if(rand.nextInt(100) <= this.hitRate){
            this.behaviours.put(FIRST_PRIORITY,new AttackBehaviour());
        }


        this.behaviours.put(SECOND_PRIORITY,new DrinkWaterBehaviour());

        // if the fountain is empty, then remove DrinkWaterBehaviour
        if (map.locationOf(this).getGround().hasCapability(Status.IS_EMPTY)){
            this.behaviours.remove(SECOND_PRIORITY);
        }

//        for(Behaviour behaviour : behaviours.values()) {
//            System.out.println(behaviour);
//        }

        // If the action list of enemy is null, enemy do nothing
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     * Enemy is allowed to be attack by the actor with capability HOSTILE_TO_ENEMY.
     * If actor has capability FIRE_ATTACK, enemy is allowed to be attacked by fire attack action.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.FIRE_ATTACK) && this.isConscious()){
            actions.add(new FireAttackAction(this,direction));
        }
        else if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious()) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Creates and returns an intrinsic weapon.
     * Actor attack other actors with more interesting descriptions and/or different damage.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage,verb);
    }

    /**
     * Add RESET_CALLED to its capability set
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_CALLED);
    }
}
