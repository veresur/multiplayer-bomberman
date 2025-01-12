package Utils.Settings;

import Utils.Enumerated.Input;
import Utils.Enumerated.Player;

/**
 * input setting representation of keybinds and which player it belongs to
 */
public class InputSetting implements Comparable<InputSetting> {
	/**
	 * input of the user
	 */
	public Input input;
	/**
	 * player which the input belongs to
	 */
	public Player player;


	public InputSetting(Input input, Player player) {
		this.input = input;
		this.player = player;
	}

	/**
	 * @param other the object to be compared.
	 * @return compare by player.toString() if equals compare by input.toString()
	 */
	@Override
	public int compareTo(InputSetting other) {

		// First, compare by player.toString()
		int playerComparison = this.player.toString().compareTo(other.player.toString());
		if (playerComparison != 0) {
			return playerComparison;
		} else {
			// If players are equal, compare by input.toString()
			return this.input.toString().compareTo(other.input.toString());
		}
	}

	/**
	 * @return string representing the input and the associated player
	 */
	@Override
	public String toString() {
		return this.player + " " + this.input;
	}
}
