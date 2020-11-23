package P3.go;

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
 * GoGameTest tests {@link GoGame}
 *
 * @author cycleke
 */
public class GoGameTest {
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
	 * Test for {@link GoGame#initNewGame()}
	 */
	@Test
	public void testInitNewGame() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
	}

	/**
	 * Test for {@link GoGame#printBoard()}
	 */
	@Test
	public void testPrintBoard() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		game.printBoard();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("19 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("18 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("17 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("16 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("15 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("14 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("13 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("12 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("11 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("10 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("09 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("08 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("07 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("06 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("05 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("04 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("03 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("02 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("01 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("    a   b   c   d   e   f   g   h   i   j   k   l   m   n   o   p   q   r   s\n");
		assertEquals(strBuilder.toString(), outContent.toString());
	}

	/**
	 * Test for {@link GoGame#addActions(List, List)}
	 */
	@Test
	public void testAddActions_1() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "candy", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 2)), });
		assertFalse(game.addActions(players1, new ArrayList<>()));
		assertFalse(game.addActions(players1, actions1));

		players1.set(2, "alice");
		assertTrue(game.addActions(players1, actions1));

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);
		game.printBoard();

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("19 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("18 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("17 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("16 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("15 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("14 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("13 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("12 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("11 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("10 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("09 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("08 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("07 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("06 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("05 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("04 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("03 [O] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("02 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("01 [O] [X] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("    a   b   c   d   e   f   g   h   i   j   k   l   m   n   o   p   q   r   s\n");
		assertEquals(strBuilder.toString(), outContent.toString());
	}

	@Test(expected = AssertionError.class)
	public void testAddActions_2() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 0)), new PutAction(Position.valueOf(0, 2)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);
	}

	@Test(expected = AssertionError.class)
	public void testAddActions_3() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 0)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);
	}

	@Test(expected = AssertionError.class)
	public void testAddActions_4() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 100)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);
	}

	/**
	 * Test for {@link GoGame#getStateOfPosition(Position)}
	 */
	@Test
	public void testGetStateOfPosition() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 2)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);

		assertNull(game.getStateOfPosition(Position.valueOf(1, 1)));

		Piece piece = game.getGameState().get(0).searchOnPosition(0, 0);
		assertEquals(piece, game.getStateOfPosition(Position.valueOf(0, 0)));

		piece = game.getGameState().get(1).searchOnPosition(1, 0);
		assertEquals(piece, game.getStateOfPosition(Position.valueOf(1, 0)));
	}

	/**
	 * Test for {@link GoGame#getPlayerOfPosition(Position)}
	 */
	@Test
	public void testGetPlayerOfPosition() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 2)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);

		assertNull(game.getPlayerOfPosition(Position.valueOf(1, 1)));

		assertEquals("alice", game.getPlayerOfPosition(Position.valueOf(0, 0)));
		assertEquals("bob", game.getPlayerOfPosition(Position.valueOf(1, 0)));
	}

	/**
	 * Test for {@link GoGame#getPlayer1History()}
	 */
	@Test
	public void testGetPlayer1History() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 2)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);

		List<List<Action>> history = game.getPlayer1History();
		assertEquals(2, history.size());
		assertEquals(
				Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)), new PutAction(Position.valueOf(0, 2)), }),
				history.get(0));
		assertEquals(Collections.EMPTY_LIST, history.get(1));
	}

	/**
	 * Test for {@link GoGame#getPlayer2History()}
	 */
	@Test
	public void testGetPlayer2History() {
		Game game = new GoGame("alice", "bob");
		game.initNewGame();
		List<String> players1 = Arrays.asList(new String[] { "alice", "bob", "alice", });
		List<Action> actions1 = Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 0)),
				new PutAction(Position.valueOf(0, 1)), new PutAction(Position.valueOf(0, 2)), });
		game.addActions(players1, actions1);

		List<String> players2 = Arrays.asList(new String[] { "bob", "bob", });
		List<Action> actions2 = Arrays
				.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)), });
		game.addActions(players2, actions2);

		List<List<Action>> history = game.getPlayer2History();
		assertEquals(2, history.size());
		assertEquals(Arrays.asList(new Action[] { new PutAction(Position.valueOf(0, 1)), }), history.get(0));
		assertEquals(
				Arrays.asList(new Action[] { new PutAction(Position.valueOf(1, 0)), new RemoveAction(Position.valueOf(0, 1)) }),
				history.get(1));
	}

	/**
	 * Test for {@link GoGame#readFromFile(File)}
	 */
	@Test
	public void testReadFromFile_1() {
		Game game = new GoGame("player1", "player2");
		assertTrue(game.readFromFile(new File("resource/P3/go/test.yaml")));
		game.printBoard();

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("19 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("18 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("17 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("16 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("15 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("14 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("13 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("12 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("11 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("10 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("09 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("08 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("07 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("06 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("05 [ ] [ ] [O] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("04 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("03 [O] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("02 [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("01 [O] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n");
		strBuilder.append("    a   b   c   d   e   f   g   h   i   j   k   l   m   n   o   p   q   r   s\n");

		assertEquals(strBuilder.toString(), outContent.toString());
		assertEquals("alice", game.getGameState().get(0).getName());
		assertEquals("bob", game.getGameState().get(1).getName());
	}

	@Test
	public void testReadFromFile_2() {
		Game game = new GoGame("player1", "player2");
		assertFalse(game.readFromFile(new File("resource/P3/go/not_exist.yaml")));
	}
}
