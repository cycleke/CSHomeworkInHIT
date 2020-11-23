package P3.chess;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import P3.base.Board;
import P3.base.Game;
import P3.base.Piece;
import P3.base.Player;

/**
 * Class {@link ChessGame} is a mutable class to store a chess game.
 *
 * @author cycleke
 */
public class ChessGame extends Game {
	public static final int ROW = 8;
	public static final int COLUMN = 8;

	private static final Map<String, String> pieceMap = new HashMap<>();
	static {
		pieceMap.put("King", "k");
		pieceMap.put("Queen", "q");
		pieceMap.put("Rook", "r");
		pieceMap.put("Bishop", "b");
		pieceMap.put("Knight", "n");
		pieceMap.put("Pawn", "p");
	};

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
	 * {@link ChessGame} Constructor
	 *
	 * @param player1 the name of the first player
	 * @param player2 the name of the second player
	 */
	public ChessGame(String player1, String player2) {
		board = new Board(ROW, COLUMN);
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		player1Actions = new ArrayList<>();
		player2Actions = new ArrayList<>();
		checkRep();
	}

	@Override
	public void initNewGame() {
		if (!readFromFile(new File("resource/P3/chess/init.yaml"))) {
			throw new RuntimeException("Can't init the new game.\nCheck the file resource/P3/chess/init.yaml");
		}
		player1Actions.clear();
		player2Actions.clear();
	}

	@Override
	public void printBoard() {
		char tuiBoard[][] = { "|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"|   | a | b | c | d | e | f | g | h |   |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 8 |   |   |   |   |   |   |   |   | 8 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 7 |   |   |   |   |   |   |   |   | 7 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 6 |   |   |   |   |   |   |   |   | 6 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 5 |   |   |   |   |   |   |   |   | 5 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 4 |   |   |   |   |   |   |   |   | 4 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 3 |   |   |   |   |   |   |   |   | 3 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 2 |   |   |   |   |   |   |   |   | 2 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"| 1 |   |   |   |   |   |   |   |   | 1 |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(),
				"|   | a | b | c | d | e | f | g | h |   |".toCharArray(),
				"|---+---+---+---+---+---+---+---+---+---|".toCharArray(), };
		for (Piece piece : player1.pieces()) {
			int x = 4 * piece.getPosition().getX() + 6; // the x coordinate
			int y = -2 * piece.getPosition().getY() + 17; // the y coordinate
			tuiBoard[y][x] = pieceMap.get(piece.getName()).charAt(0);
		}
		for (Piece piece : player2.pieces()) {
			int x = 4 * piece.getPosition().getX() + 6; // the x coordinate
			int y = -2 * piece.getPosition().getY() + 17; // the y coordinate
			tuiBoard[y][x] = pieceMap.get(piece.getName()).toUpperCase().charAt(0);
		}
		for (char[] line : tuiBoard) {
			System.out.println(String.copyValueOf(line));
		}
	}

}
