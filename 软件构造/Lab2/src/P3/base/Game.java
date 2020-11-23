package P3.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

/**
 * Class {@link Game} is a mutable class to store the whole game.
 *
 * @author cycleke
 */
public abstract class Game {

	protected Board board;
	protected Player player1, player2;
	protected List<List<Action>> player1Actions, player2Actions;

	protected void checkRep() {
		int row = board.getRow(), column = board.getColumn();
		Set<Position> set = new HashSet<>();
		for (Piece piece : player1.pieces()) {
			assert piece.getPosition().getY() < row;
			assert piece.getPosition().getX() < column;
			assert set.add(piece.getPosition());
		}
		for (Piece piece : player2.pieces()) {
			assert piece.getPosition().getY() < row;
			assert piece.getPosition().getX() < column;
			assert set.add(piece.getPosition());
		}
	}

	/**
	 * Initialize the {@link Game}.
	 *
	 */
	public abstract void initNewGame();

	/**
	 * Add the actions of a player in one turn.
	 *
	 * @param player  the player of the actions
	 * @param actions the actions
	 */
	public boolean addActions(List<String> players, List<Action> actions) {
		if (players.size() != actions.size())
			return false;
		for (String name : players) {
			if (name.equals(player1.getName()) || name.equals(player2.getName()))
				continue;
			return false;
		}
		Iterator<String> playerIterator = players.iterator();
		Iterator<Action> actionIterator = actions.iterator();
		List<Action> player1Actions = new LinkedList<>();
		List<Action> player2Actions = new LinkedList<>();
		while (playerIterator.hasNext()) {
			Action action = actionIterator.next();
			if (playerIterator.next().equals(player1.getName())) {
				action.doAction(player1);
				player1Actions.add(action);
			} else {
				action.doAction(player2);
				player2Actions.add(action);
			}
		}
		this.player1Actions.add(player1Actions);
		this.player2Actions.add(player2Actions);
		checkRep();
		return true;
	}

	/**
	 * Return the state of a position.
	 *
	 * @param position the position to query
	 */
	public Piece getStateOfPosition(Position position) {
		Piece piece = player1.searchOnPosition(position);
		if (piece != null)
			return piece;
		piece = player2.searchOnPosition(position);
		if (piece != null)
			return piece;
		return null;
	}

	/**
	 * Return the player of a position.
	 *
	 * @param position the position to query
	 */
	public String getPlayerOfPosition(Position position) {
		Piece piece = player1.searchOnPosition(position);
		if (piece != null)
			return player1.getName();
		piece = player2.searchOnPosition(position);
		if (piece != null)
			return player2.getName();
		return null;
	}

	/**
	 * Print the boar in TUI.
	 */
	public abstract void printBoard();

	/**
	 * Get current status of the game.
	 */
	public List<Player> getGameState() {
		List<Player> result = new ArrayList<>();
		result.add(new Player(player1));
		result.add(new Player(player2));
		return result;
	}

	/**
	 * Get the history actions of player1.
	 */
	public List<List<Action>> getPlayer1History() {
		return new ArrayList<>(player1Actions);
	}

	/**
	 * Get the history actions of player2.
	 */
	public List<List<Action>> getPlayer2History() {
		return new ArrayList<>(player2Actions);
	}

	public boolean readFromFile(File recordFile) {
		player1.clearPieces();
		player2.clearPieces();

		try {
			InputStream inputStream = new FileInputStream(recordFile);
			Yaml yaml = new Yaml();
			Map<String, Object> players = yaml.load(inputStream);
			if (!players.containsKey("player1") || !players.containsKey("player2"))
				return false;
			Map<String, Object> player1Map = cast(players.get("player1"));
			if (player1Map.containsKey("name")) {
				player1.setName(cast(player1Map.get("name")));
			}
			List<Map<String, Object>> player1Piece = cast(player1Map.get("pieces"));
			for (Map<String, Object> pieceMap : player1Piece) {
				int x = cast(pieceMap.get("x"));
				int y = cast(pieceMap.get("y"));
				Piece piece = new Piece(cast(pieceMap.get("name")), new Position(x, y));
				player1.addPiece(piece);
			}
			Map<String, Object> player2Map = cast(players.get("player2"));
			if (player2Map.containsKey("name")) {
				player2.setName(cast(player2Map.get("name")));
			}
			List<Map<String, Object>> player2Piece = cast(player2Map.get("pieces"));
			for (Map<String, Object> pieceMap : player2Piece) {
				int x = cast(pieceMap.get("x"));
				int y = cast(pieceMap.get("y"));
				Piece piece = new Piece(cast(pieceMap.get("name")), new Position(x, y));
				player2.addPiece(piece);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@SuppressWarnings("unchecked")
	private static <T> T cast(Object obj) {
		return (T) obj;
	}
}
