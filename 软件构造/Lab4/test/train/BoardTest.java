package train;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import org.junit.Test;
import train.Board.IteratorType;
import train.TrainCabin.TrainCabinType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    TrainEntry entry1 = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    TrainEntry entry2 = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-25 07:01", "2020-02-25 08:00"),
        Timeslot.parse("2020-02-25 08:03", "2020-02-25 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    TrainEntry entry3 = new TrainEntry("G2020", Collections.singletonList(Timeslot.parse("2020-02-28 08:05", "2020-02-28 10:34")),
      Arrays.asList(CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE)));
    List<TrainCabin> train1 = Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020));

    try {
      entry1.setResources(train1);
      entry1.allocated();
      entry2.setResources(train1);
      entry2.allocated();
      entry2.run();
      entry3.setResources(train1);
      entry3.allocated();
      entry3.run();
      entry3.end();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return new Board(Arrays.asList(entry1, entry2, entry3));
  }

  /**
   * Tests {@link Board#Board()} and {@link Board#Board(List)}
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
    board.setSortKeyLocation(CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE));
  }

  /**
   * Tests {@link Board#addEntry(TrainPlanningEntry)}
   */
  @Test
  public void testAddEntry() throws IllegalStateTransitionException {
    Board board = simpleBoard();
    TrainEntry entry1 = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    TrainEntry entry2 = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-25 07:01", "2020-02-25 08:00"),
        Timeslot.parse("2020-02-25 08:03", "2020-02-25 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    List<TrainCabin> train1 = Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020));

    entry2.setResources(train1);
    entry2.allocated();
    assert board != null;
    assertTrue(board.addEntry(entry1));
    assertFalse(board.addEntry(entry2));
  }

  /**
   * Tests {@link Board#removeEntry(TrainPlanningEntry)}
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
    board.setSortKeyLocation(CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE));
    board.visualize();
  }
}
