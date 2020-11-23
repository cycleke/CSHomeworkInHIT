package P3.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import P3.base.Action;
import P3.base.Game;
import P3.base.Piece;
import P3.base.Position;

/**
 * ChessGameTest tests {@link ChessGame}
 *
 * @author cycleke
 */
public class ChessGameTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	/**
	 * Test for {@link ChessGame#initNewGame()}
	 */
	@Test
	public void testInitNewGame() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
	}

	/**
	 * Test for {@link ChessGame#printBoard()}
	 */
	@Test
	public void testPrintBoard() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		game.printBoard();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("|   | a | b | c | d | e | f | g | h |   |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 8 | R | N | B | Q | K | B | N | R | 8 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 7 | P | P | P | P | P | P | P | P | 7 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 6 |   |   |   |   |   |   |   |   | 6 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 5 |   |   |   |   |   |   |   |   | 5 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 4 |   |   |   |   |   |   |   |   | 4 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 3 |   |   |   |   |   |   |   |   | 3 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 2 | p | p | p | p | p | p | p | p | 2 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 1 | r | n | b | q | k | b | n | r | 1 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("|   | a | b | c | d | e | f | g | h |   |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		assertEquals(strBuilder.toString(), outContent.toString());
	}

	/**
	 * Test for {@link ChessGame#addActions(List, List)}
	 */
	@Test
	public void testAddActions_1() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		List<String> players = Arrays.asList(new String[] { "alice", "bob", "candy" });
		List<Action> actions = new ArrayList<>();
		assertFalse(game.addActions(players, actions));

		actions.add(new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)));
		actions.add(new RemoveAction(Position.valueOf(0, 6)));
		actions.add(new PromotionAction(Position.valueOf(0, 1)));
		assertFalse(game.addActions(players, actions));

		players.set(2, "alice");
		game.addActions(players, actions);
		game.printBoard();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("|   | a | b | c | d | e | f | g | h |   |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 8 | R | N | B | Q | K | B | N | R | 8 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 7 |   | P | P | P | P | P | P | P | 7 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 6 |   |   |   |   |   |   |   |   | 6 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 5 |   |   |   |   |   |   |   |   | 5 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 4 |   |   |   |   |   |   |   |   | 4 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 3 |   |   | n |   |   |   |   |   | 3 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 2 | q | p | p | p | p | p | p | p | 2 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 1 | r |   | b | q | k | b | n | r | 1 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("|   | a | b | c | d | e | f | g | h |   |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		assertEquals(strBuilder.toString(), outContent.toString());
	}

	@Test(expected = AssertionError.class)
	public void testAddActions_2() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		List<String> players = Arrays.asList(new String[] { "alice", "bob", });
		List<Action> actions = Arrays.asList(new Action[] { new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)),
				new MoveAction(Position.valueOf(0, 6), Position.valueOf(2, 2)), });
		game.addActions(players, actions);
	}

	@Test(expected = AssertionError.class)
	public void testAddActions_3() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		List<String> players = Arrays.asList(new String[] { "alice", "alice", });
		List<Action> actions = Arrays.asList(new Action[] { new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)),
				new MoveAction(Position.valueOf(2, 0), Position.valueOf(2, 2)), });
		game.addActions(players, actions);
	}

	@Test(expected = AssertionError.class)
	public void testAddActions_4() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		List<String> players = Arrays.asList(new String[] { "alice", "alice", });
		List<Action> actions = Arrays.asList(new Action[] { new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)),
				new MoveAction(Position.valueOf(2, 0), Position.valueOf(2, 100)), });
		game.addActions(players, actions);
	}

	/**
	 * Test for {@link ChessGame#getStateOfPosition(Position)}
	 */
	@Test
	public void testGetStateOfPosition() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		List<String> players = Arrays.asList(new String[] { "alice", "bob", "alice" });
		List<Action> actions = new ArrayList<>();

		actions.add(new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)));
		actions.add(new RemoveAction(Position.valueOf(0, 6)));
		actions.add(new PromotionAction(Position.valueOf(0, 1)));
		game.addActions(players, actions);

		assertNull(game.getStateOfPosition(Position.valueOf(1, 0)));

		Piece piece = game.getGameState().get(1).searchOnPosition(3, 6);
		assertEquals(piece, game.getStateOfPosition(Position.valueOf(3, 6)));
	}

		/**
	 * Test for {@link ChessGame#getPlayerOfPosition(Position)}
	 */
	@Test
	public void testGetPlayerOfPosiition() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();
		List<String> players = Arrays.asList(new String[] { "alice", "bob", "alice" });
		List<Action> actions = new ArrayList<>();

		actions.add(new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)));
		actions.add(new RemoveAction(Position.valueOf(0, 6)));
		actions.add(new PromotionAction(Position.valueOf(0, 1)));
		game.addActions(players, actions);

		assertNull(game.getPlayerOfPosition(Position.valueOf(1, 0)));

		assertEquals("bob", game.getPlayerOfPosition(Position.valueOf(3, 6)));
	}

	/**
	 * Test for {@link ChessGame#getPlayer1History()}
	 */
	@Test
	public void testGetPlayer1History() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();

		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice" });
		List<Action> actions1 = Arrays.asList(new Action[] { new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)),
				new RemoveAction(Position.valueOf(0, 6)), new PromotionAction(Position.valueOf(0, 1)) });
		game.addActions(players1, actions1);

		List<String> players2 = new ArrayList<>();
		List<Action> actions2 = new ArrayList<>();
		game.addActions(players2, actions2);

		List<String> players3 = Arrays.asList(new String[] { "bob", });
		List<Action> actions3 = Arrays.asList(new Action[] { new PromotionAction(Position.valueOf(1, 6)) });
		game.addActions(players3, actions3);

		List<List<Action>> history = game.getPlayer1History();
		assertEquals(Arrays.asList(new Action[] { new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)),
				new PromotionAction(Position.valueOf(0, 1)) }), history.get(0));
		assertEquals(Collections.EMPTY_LIST, history.get(1));
		assertEquals(Collections.EMPTY_LIST, history.get(2));
	}

	/**
	 * Test for {@link ChessGame#getPlayer2History()}
	 */
	@Test
	public void testGetPlayer2History() {
		Game game = new ChessGame("alice", "bob");
		game.initNewGame();

		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice" });
		List<Action> actions1 = Arrays.asList(new Action[] { new MoveAction(Position.valueOf(1, 0), Position.valueOf(2, 2)),
				new RemoveAction(Position.valueOf(0, 6)), new PromotionAction(Position.valueOf(0, 1)) });
		game.addActions(players1, actions1);

		List<String> players2 = new ArrayList<>();
		List<Action> actions2 = new ArrayList<>();
		game.addActions(players2, actions2);

		List<String> players3 = Arrays.asList(new String[] { "bob", });
		List<Action> actions3 = Arrays.asList(new Action[] { new PromotionAction(Position.valueOf(1, 6)) });
		game.addActions(players3, actions3);

		List<List<Action>> history = game.getPlayer2History();
		assertEquals(Arrays.asList(new Action[] { new RemoveAction(Position.valueOf(0, 6)) }), history.get(0));
		assertEquals(Collections.EMPTY_LIST, history.get(1));
		assertEquals(Arrays.asList(new Action[] { new PromotionAction(Position.valueOf(1, 6)) }), history.get(2));
	}

	/**
	 * Test for {@link ChessGame#readFromFile(File)}
	 */
	@Test
	public void testReadFromFile() {
		Game game = new ChessGame("player1", "player2");
		assertTrue(game.readFromFile(new File("resource/P3/chess/test.yaml")));
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("|   | a | b | c | d | e | f | g | h |   |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 8 | R | N | B | Q |   | B | N | R | 8 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 7 | P | P | P | P | P | P | P | P | 7 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 6 |   |   |   |   |   |   |   |   | 6 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 5 |   |   |   |   | K |   |   |   | 5 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 4 |   |   |   |   |   |   |   |   | 4 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 3 |   |   |   |   |   |   |   |   | 3 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 2 |   |   |   |   |   |   |   |   | 2 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("| 1 | r | n | b | q | k | b | n | r | 1 |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		strBuilder.append("|   | a | b | c | d | e | f | g | h |   |\n");
		strBuilder.append("|---+---+---+---+---+---+---+---+---+---|\n");
		game.printBoard();
		assertEquals(strBuilder.toString(), outContent.toString());
		assertEquals("alice", game.getGameState().get(0).getName());
		assertEquals("bob", game.getGameState().get(1).getName());
	}
}
