package P3.go;

import java.util.ArrayList;

import P3.base.Board;
import P3.base.Game;
import P3.base.Piece;
import P3.base.Player;

/**
 * Class {@link GoGame} is a mutable class to store a go game.
 *
 * @author cycleke
 */
public class GoGame extends Game {
	public static final int ROW = 19;
	public static final int COLUMN = 19;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(game) = (players, actions)
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * No two pieces on the same position, and the pieces should be in the board.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private or const.
	 */

	/**
	 * {@link GoGame} Constructor
	 *
	 * @param player1 the name of the first player
	 * @param player2 the name of the second player
	 */
	public GoGame(String player1, String player2) {
		board = new Board(ROW, COLUMN);
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		player1Actions = new ArrayList<>();
		player2Actions = new ArrayList<>();
		checkRep();
	}

	@Override
	public void initNewGame() {
		player1.clearPieces();
		player2.clearPieces();
		player1Actions.clear();
		player2Actions.clear();
	}

	@Override
	public void printBoard() {
		char tuiBoard[][] = {
				"19 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"18 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"17 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"16 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"15 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"14 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"13 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"12 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"11 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"10 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"09 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"08 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"07 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"06 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"05 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"04 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"03 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"02 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"01 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]".toCharArray(),
				"    a   b   c   d   e   f   g   h   i   j   k   l   m   n   o   p   q   r   s".toCharArray(), };

		for (Piece piece : player1.pieces()) {
			int x = 4 * piece.getPosition().getX() + 4;
			int y = 18 - piece.getPosition().getY();
			tuiBoard[y][x] = 'O';
		}
		for (Piece piece : player2.pieces()) {
			int x = 4 * piece.getPosition().getX() + 4;
			int y = 18 - piece.getPosition().getY();
			tuiBoard[y][x] = 'X';
		}
		for (char[] line : tuiBoard) {
			System.out.println(String.copyValueOf(line));
		}
	}

}
