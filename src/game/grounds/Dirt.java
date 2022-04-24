package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private static final char DIRT_CHAR = '.';

	public Dirt() {
		super(DIRT_CHAR);
		this.addCapability(Status.IS_FERTILE);
	}
}
