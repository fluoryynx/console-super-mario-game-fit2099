package game.actors;

import game.Status;

public class FlyingKoopa extends GeneralKoopa{
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
    private static final int HIT_POINT = 5;

    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super(FLYINGKOOPA_NAME,FLYINGKOOPA_CHAR,HIT_POINT,14,16);
        this.addCapability(Status.CAN_FLY);
    }
}
