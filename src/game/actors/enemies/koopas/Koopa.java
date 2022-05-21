package game.actors.enemies.koopas;

/**
 * Koopa class is a class represents one kind of GeneralKoopa of this game. It is a class that extends from the GeneralKoopa class.
 * Koopa K starts with hit points 100, 30 damage and 50% hit rate.
 * It will go to a dormant state whenever it is unconscious until Mario brings a wrench to crack the shell (killing it)!
 * Cracking its shell drops a Super Mushroom.
 * The mature tree has a 50:50 chance of spawning either normal Koopa or Flying Koopa (after a successful 15% spawn rate).
 * Besides, Koopa also speak every even turn automatically.
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
    private static final int HIT_POINT = 100;

    /**
     * The lower bound index for the monologue of Koopa
     */
    private static final int MONOLOGUE_INDEX_LOWER_BOUND = 14;

    /**
     * The upper bound index for the monologue of Koopa
     */
    private static final int MONOLOGUE_INDEX_UPPER_BOUND = 15;

    /**
     * Constructor.
     * The monologue belongs to Koopa will be print by using index.
     */
    public Koopa() {
        super(KOOPA_NAME,KOOPA_CHAR,HIT_POINT,MONOLOGUE_INDEX_LOWER_BOUND,MONOLOGUE_INDEX_UPPER_BOUND);
    }

}
