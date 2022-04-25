package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Resettable;
import game.Status;
import game.items.Wallet;
import game.actions.ResetAction;
import game.managers.BuffManager;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private boolean oneReset = false;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.BUY);
		this.addCapability(Status.SPEAK);
		this.registerInstance();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		display.println("Mario " + this.printHp() + " at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
		display.println("Wallet balance: $" + Wallet.getInstance().getBalance());
		if (this.hasCapability(Status.INVINCIBLE)){
			display.println("Mario is INVINCIBLE!");
		}
		BuffManager.getInstance().run(map.locationOf(this));

		if (!oneReset){
			ResetAction resetAction = new ResetAction();
			actions.add(resetAction);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override
	public void resetInstance() {
		// Reset player status
		for (Status status: Status.values()){
			if (this.hasCapability(status) && !status.equals(Status.HOSTILE_TO_ENEMY) && !status.equals(Status.SPEAK) && !status.equals(Status.BUY)){
				this.removeCapability(status);
			}
		}

		// Heal the player to maximum
		this.heal(this.getMaxHp());

		this.setOneReset(true);
	}

	public void setOneReset(boolean oneReset) {
		this.oneReset = oneReset;
	}
}
