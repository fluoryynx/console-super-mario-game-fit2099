package game.actors.enemies.koopas;

import game.Status;

/**
 * FlyingKoopa class is a class represents one kind of GeneralKoopa of this game. It is a class that extends from the GeneralKoopa class.
 * Flying Koopa F has pretty much similar characteristics as the original Koopa, except it has a bigger health bar (150 hit points).
 * It starts with 30 damage and 50% hit rate.
 * Furthermore, it can walk (fly) over the trees and walls when it wanders around (incl. other high grounds).
 * It also applies when Flying Koopa follows Mario. Like Koopa, it will go to a dormant state whenever it is unconscious until
 * Mario brings a wrench to crack the shell (killing it)! Cracking its shell drops a Super Mushroom.
 * The mature tree has a 50:50 chance of spawning either normal Koopa or Flying Koopa (after a successful 15% spawn rate).
 * Besides, FlyingKoopa also speak every even turn automatically.
 *
 * @author Huang GuoYueYang
 */
public class FlyingKoopa extends GeneralKoopa {
    /**
     * Name of FlyingKoopa
     */
    private static final String FLYINGKOOPA_NAME = "FlyingKoopa";

    /**
     * Character of FlyingKoopa
     */
    private static final char FLYINGKOOPA_CHAR = 'F';

    /**
     * HitPoint of FlyingKoopa
     */
    private static final int HIT_POINT = 150;

    /**
     * The lower bound index for the monologue of FlyingKoopa
     */
    private static final int MONOLOGUE_INDEX_LOWER_BOUND = 14;

    /**
     * The upper bound index for the monologue of FlyingKoopa
     */
    private static final int MONOLOGUE_INDEX_UPPER_BOUND = 16;

    /**
     * Constructor.
     * The monologue belongs to FlyingKoopa will be print by using index.
     */
    public FlyingKoopa() {
        super(FLYINGKOOPA_NAME,FLYINGKOOPA_CHAR,HIT_POINT,MONOLOGUE_INDEX_LOWER_BOUND,MONOLOGUE_INDEX_UPPER_BOUND);
        this.addCapability(Status.CAN_FLY);
    }
}
