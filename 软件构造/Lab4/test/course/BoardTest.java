package course;

import base.CommonLocation;
import base.Timeslot;
import org.junit.Test;

import java.util.Arrays;
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
    CourseEntry entry1 = new CourseEntry("CS101", Timeslot.parse("2020-05-02 07:45", "2020-05-02 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry2 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry3 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 10:00", "2020-05-01 11:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    Teacher teacher1 = new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
    Teacher teacher2 = new Teacher("110101199003078849", "FF", "female", "Prof.");

    try {
      entry1.setResource(teacher1);
      entry1.allocated();
      entry2.setResource(teacher2);
      entry2.allocated();
      entry2.run();
      entry2.end();
      entry3.cancel();
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
   * Tests {@link Board#setSortKeyLocation(base.Location)}
   */
  @Test
  public void testSetSortKeyLocation() {
    Board board = simpleBoard();
    assert board != null;
    board.setSortKeyLocation(CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
  }

  /**
   * Tests {@link Board#addEntry(CoursePlanningEntry)}
   */
  @Test
  public void testAddEntry() {
    Board board = simpleBoard();
    CourseEntry entry1 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry2 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心35", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry3 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 10:00", "2020-05-01 11:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));

    assert board != null;
    assertFalse(board.addEntry(entry1));
    assertTrue(board.addEntry(entry2));
    assertFalse(board.addEntry(entry3));
  }

  /**
   * Tests {@link Board#removeEntry(CoursePlanningEntry)}
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
    board.setSortKeyLocation(CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    board.visualize();
  }
}
