package game.grounds.highgrounds;

/**
 * A class that represents the wall in this game. It extends HighGround as it is jumpable by the Player.
 * The success rate to jump on the wall is 80%, and its fall damage will be 20. Besides, wall can block
 * thrown objects.
 *
 * @author Kuah Jia Chen
 */
public class Wall extends HighGround {

	/**
	 * Success rate to jump on this wall
	 */
	private static final int JUMP_RATE = 80; // 80% chance to jump successfully

	/**
	 * Fall damage done to the actor when the jump is not successful
	 */
	private static final int FALL_DAMAGE = 20;

	/**
	 * The type of this high ground
	 */
	private static final String GROUND_TYPE = "Wall";

	/**
	 * Character to display for wall on the map
	 */
	private static final char WALL_CHAR = '#';

	/**
	 * A boolean indicating whether wall can block thrown object
	 */
	private static final boolean BLOCK_THROWN_OBJECT = true;

	/**
	 * Constructor
	 */
	public Wall() {
		super(WALL_CHAR,JUMP_RATE,FALL_DAMAGE,GROUND_TYPE);
	}

	/**
	 * Return a boolean indicating whether a wall can block thrown objects
	 * @return True if wall can block thrown objects, else false
	 */
	@Override
	public boolean blocksThrownObjects() {
		return BLOCK_THROWN_OBJECT;
	}
}
