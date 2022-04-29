package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Resettable;
import game.Status;
import game.items.MagicalItem;
import game.managers.Wallet;
import game.actions.ResetAction;
import game.managers.BuffManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing the Player (i.e., the mario in the game map)
 * This is the actor that will be controlled by the user using the console.
 *
 * @author Kuah Jia Chen, Huang GuoYueYang
 */
public class Player extends Actor implements Resettable {

	/**
	 * A Menu instance
	 */
	private final Menu menu = new Menu();

	/**
	 * A boolean indicating whether the reset action is called
	 */
	private boolean oneReset = false;

	/**
	 * A hash set that store the statuses that are permanent to the player
	 * (i.e., should not be removed when reset action is called)
	 */
	private Set<Enum<?>> statusPermanent = new HashSet<>();

	/**
	 * An instance of BuffManager
	 */
	private BuffManager buffManager;

	/**
	 * Constructor.
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		// add capability that a player should have
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.BUY);
		this.addCapability(Status.SPEAK);
		this.registerInstance(); // append to the array list in ResetManager
		// add permanent status to statusPermanent
		statusPermanent.add(Status.HOSTILE_TO_ENEMY);
		statusPermanent.add(Status.BUY);
		statusPermanent.add(Status.SPEAK);
		// assign the instance of BuffManager to buffManager
		this.buffManager = BuffManager.getInstance();
	}

	/**
	 * run every turn of the player
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		// print useful information about the player in the console
		display.println("Mario " + this.printHp() + " at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
		display.println("Wallet balance: $" + Wallet.getInstance().getBalance());

		// print the following message if player is invincible
		if (this.hasCapability(Status.INVINCIBLE)){
			display.println("Mario is INVINCIBLE!");
		}

		// call the run method in BuffManager once in every turn
		buffManager.run(map.locationOf(this));

		// debug purpose
//		for (Enum<?> currentStatus: this.capabilitiesList()){
//			display.println(currentStatus+"");
//		}

		// if player has this capability, it means reset action is executed, hence this capability is not
		// need anymore, therefore we can remove it
		if (this.hasCapability(Status.RESET_CALLED)){
			this.removeCapability(Status.RESET_CALLED);
		}

		// when reset action has not been called, reset action will still be available to the user
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

	/**
	 * return display character of the player
	 * @return M if player has TALL capability; m if not
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Reset player status, heal the player to maximum and set oneReset to true indicating
	 * reset action has been executed
	 */
	@Override
	public void resetInstance() {

		// Reset player status
		for (Enum<?> currentStatus: this.capabilitiesList()){
			// remove status that are not permanent to the user (i.e., other than HOSTILE_TO_ENEMY,
			// BUY and SPEAK
			if (!statusPermanent.contains(currentStatus)){
				this.removeCapability(currentStatus);
			}
		}

		this.addCapability(Status.RESET_CALLED);

		// Heal the player to maximum
		this.heal(this.getMaxHp());

		// set oneReset to true
		this.setOneReset(true);
	}

	/**
	 * to indicate whether player presses the reset hotkey
	 * @param oneReset
	 */
	public void setOneReset(boolean oneReset) {
		this.oneReset = oneReset;
	}
}
