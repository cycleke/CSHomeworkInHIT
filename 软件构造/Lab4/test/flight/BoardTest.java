package flight;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import flight.Board.IteratorType;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * This class {@link BoardTest} tests {@link Board}.
 *
 * @author cycleke
 */
public class BoardTest {
  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  private Board simpleBoard() {
    FlightEntry entry1 = new FlightEntry("AA018", Timeslot.parse("2020-01-16 22:40", "2020-01-17 03:51"),
      CommonLocation.of("Hong Kong", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Shanghai", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry2 = new FlightEntry("AA03", Timeslot.parse("2020-01-06 17:19", "2020-01-06 18:47"),
      CommonLocation.of("Nanning", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Dalian", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry3 = new FlightEntry("AA03", Timeslot.parse("2020-01-13 17:19", "2020-01-13 18:47"),
      CommonLocation.of("Nanning", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Dalian", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry4 = new FlightEntry("AA87", Timeslot.parse("2020-02-18 00:30", "2020-02-18 04:08"),
      CommonLocation.of("Dalian", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Changsha", FlightEntry.LOCATION_SHAREABLE));
    Plane plane1 = new Plane("B6967", "A340", 332, 23.7);
    Plane plane2 = new Plane("B1554", "B787", 428, 26.1);
    try {
      entry1.setResource(plane1);
      entry1.allocated();
      entry2.setResource(plane2);
      entry2.allocated();
      entry2.run();
      entry2.end();
      entry4.cancel();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return new Board(Arrays.asList(entry1, entry2, entry3, entry4));
  }

  /**
   * Tests {@link Board#Board()} and {@link Board#Board()}
   */
  @Test
  public void testConstructor() {
    Board board = new Board();
    assertNotNull(board);
    assertNotNull(simpleBoard());
  }

  /**
   * Tests {@link Board#setIteratorType(IteratorType)}
   */
  @Test
  public void testSetIteratorType() {
    Board board = simpleBoard();
    assert board != null;
    board.setIteratorType(IteratorType.ARRIVAL);
    board.setIteratorType(IteratorType.ARRIVAL);
  }

  /**
   * Tests {@link Board#setSortKeyLocation(base.Location)}
   */
  @Test
  public void testSetSortKeyLocation() {
    Board board = simpleBoard();
    assert board != null;
    board.setSortKeyLocation(CommonLocation.of("Dalian", FlightEntry.LOCATION_SHAREABLE));
  }

  /**
   * Tests {@link Board#addEntry(FlightPlanningEntry)}
   */
  @Test
  public void testAddEntry() throws IllegalStateTransitionException {
    Board board = simpleBoard();
    FlightEntry entry1 = new FlightEntry("AA018", Timeslot.parse("2020-01-16 22:40", "2020-01-17 03:51"),
      CommonLocation.of("Hong Kong", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Shanghai", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry2 = new FlightEntry("AA018", Timeslot.parse("2020-01-17 22:40", "2020-01-18 03:51"),
      CommonLocation.of("Hong Kong", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Shanghai", FlightEntry.LOCATION_SHAREABLE));
    Plane plane1 = new Plane("B6967", "A340", 332, 23.7);
    entry1.setResource(plane1);
    entry1.allocated();
    entry2.setResource(plane1);
    entry2.allocated();
    assert board != null;
    assertFalse(board.addEntry(entry1));
    assertTrue(board.addEntry(entry2));
  }

  /**
   * Tests {@link Board#removeEntry(FlightPlanningEntry)}
   */
  @Test
  public void testRemoveEntry() {
    Board board = simpleBoard();
    assert board != null;
    board.removeEntry(board.getEntries().get(0));
  }

  /**
   * Tests {@link Board#iterator()}
   */
  @Test
  public void testIterator() {
    Board board = simpleBoard();
    assert board != null;
    assertNotNull(board.iterator());
  }

  /**
   * Tests {@link Board#visualize()}
   */
  @Test
  public void testVisualize() {
    Board board = simpleBoard();
    assert board != null;
    board.setSortKeyLocation(CommonLocation.of("Dalian", FlightEntry.LOCATION_SHAREABLE));
    board.visualize();
  }
}
