package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents bare dirt Currently, it is the only fertile ground in this game It is a class that
 * extends Ground
 *
 * @author Kuah Jia Chen
 */
public class Dirt extends Ground {

	/**
	 * Character to display for dirt on the map
	 */
	private static final char DIRT_CHAR = '.';

	/**
	 * Constructor of Dirt class. Additionally, since Dirt is a fertile ground, therefore the capability
	 * IS_FERTILE is added to indicate it is a fertile ground
	 */
	public Dirt() {
		super(DIRT_CHAR);
		this.addCapability(Status.IS_FERTILE);
	}
}
