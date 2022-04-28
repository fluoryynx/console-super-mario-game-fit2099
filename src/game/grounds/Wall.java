package game.grounds;

import game.Destroyable;

public class Wall extends HighGround {

	private static final int JUMP_RATE = 80; // 80% chance to jump successfully

	private static final int FALL_DAMAGE = 20;

	private static final String GROUND_TYPE = "Wall";

	private static final char WALL_CHAR = '#';

	private static final boolean BLOCK_THROWN_OBJECT = true;

	public Wall() {
		super(WALL_CHAR,JUMP_RATE,FALL_DAMAGE,GROUND_TYPE);
	}

	@Override
	public boolean blocksThrownObjects() {
		return BLOCK_THROWN_OBJECT;
	}
}
