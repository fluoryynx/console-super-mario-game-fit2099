package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.JumpAction;
import game.Jumpable;
import game.Status;

public class Wall extends Ground implements Jumpable {

	private static final int JUMP_RATE = 80; // 80% chance to jump successfully

	private static final int FALL_DAMAGE = 20;

	private static final String JUMP_TYPE = "Wall";

	public Wall() {
		super('#');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.CAN_JUMP) || actor.hasCapability(Status.POWER_STAR) ;
	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public int getJumpRate() { return JUMP_RATE; }

	@Override
	public String getJumpableType() { return JUMP_TYPE; }

	@Override
	public int getFallDamage() { return FALL_DAMAGE; }

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		if (!location.containsAnActor() && !actor.hasCapability(Status.POWER_STAR)) {
			return new ActionList(new JumpAction(location, direction, this));
		}
		return new ActionList();
	}
}
