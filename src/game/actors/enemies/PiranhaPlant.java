package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;

public class PiranhaPlant extends Enemy {
    /**
     * Name of PiranhaPlant
     */
    private static final String PIRANHAPLANT_NAME = "PiranhaPlant";

    /**
     * Character of PiranhaPlant
     */
    private static final char PIRANHAPLANT_CHAR = 'Y';

    /**
     * HitPoint of PiranhaPlant
     */
    private static final int HIT_POINT = 150;

    /**
     * HitRate of PiranhaPlant
     */
    private static final int HIT_RATE = 50;

    /**
     * Damage of PiranhaPlant
     */
    private static final int DAMAGE = 90;

    /**
     * Hit verb of PiranhaPlant
     */
    private static final String HIT_VERB = "chomps";

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super(PIRANHAPLANT_NAME,PIRANHAPLANT_CHAR,HIT_POINT,DAMAGE,HIT_VERB,HIT_RATE,17,18);
        this.behaviours.remove(FOURTH_PRIORITY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        currentTurn ++;
        if (timeToSpeak(currentTurn)){
            display.println(this + " : " +
                    generateMonologue(monologueIndexLowerBound,monologueIndexUpperBound));
        }
        this.behaviours.put(FIRST_PRIORITY,new AttackBehaviour());
        // If the action list of enemy is null, enemy do nothing
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
