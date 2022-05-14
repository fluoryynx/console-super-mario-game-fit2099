package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackShellAction;
import game.actors.Enemy;
/**
 * Koopa class is a class represents the enemies in this game. It is a class that extends from the Enemy class.
 * Koopa can move around in the game map but cannot enter floor.
 * Once koopa is engaged in a fight (the Player attacks the enemy or the enemy attacks player
 * -- when the player stands in the enemy's surroundings), it will follow the Player.
 * It causes 30 damages to player with 50% hit rate and koopa has the same behaviors with goomba.
 * When koopa is not conscious(means it is defeated), it will hide inside its shell, and its
 * character will change from ’K’ to ‘D’.
 * Player cannot attack it anymore, and all the behaviors will removed from koopa(attack/follow/wander).
 *
 * @author Huang GuoYueYang
 */
public class Koopa extends GeneralKoopa {
    /**
     * Name of Koopa
     */
    private static final String KOOPA_NAME = "Koopa";

    /**
     * Character of Koopa
     */
    private static final char KOOPA_CHAR = 'K';

    /**
     * HitPoint of Koopa
     */
    private static final int HIT_POINT = 5;

    /**
     * Constructor.
     */
    public Koopa() {
        super(KOOPA_NAME,KOOPA_CHAR,HIT_POINT,14,15);
    }

}
