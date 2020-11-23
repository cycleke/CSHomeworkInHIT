package P3.base;

/**
 * Class {@link Action} is an immutable to describe a player's action.
 *
 * @author cycleke
 */
public interface Action {

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(action) = player -> player
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * true
	 */

	/**
	 * Do this action for the player.
	 *
	 * @param player
	 */
	void doAction(Player player);
}
