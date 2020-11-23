package course;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.PlanningEntry;
import base.Timeslot;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class {@link CourseEntryOthersTest} tests the other functions of
 * {@link CourseEntry}.
 *
 * @author cycleke
 */
public class CourseEntryOthersTest {
  public CoursePlanningEntry singleton() {
    return new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
  }

  /**
   * Tests {@link CourseEntry#CourseEntry(String, Timeslot, base.Location)}
   */
  @Test
  public void testConstructor() {
    CoursePlanningEntry entry = singleton();
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }

  /**
   * Tests {@link CourseEntry#allocated()}
   */
  @Test
  public void testAllocated() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = singleton();
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    entry.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  /**
   * Tests {@link CourseEntry#run()}
   */
  @Test
  public void testRun() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = singleton();
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    entry.allocated();
    entry.run();
    assertTrue(entry.getState() instanceof RunningState);
  }

  /**
   * Tests {@link CourseEntry#end()}
   */
  @Test
  public void testEnd() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = singleton();
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    entry.allocated();
    entry.run();
    entry.end();
    assertTrue(entry.getState() instanceof EndedState);
  }

  /*
   * Tests {@link CourseEntry#cancel()}
   *
   * <p> Testing strategy </p>
   *
   * Partition the inputs as follows: <q>path to cancelled state: WAITING ->
   * CANCELLED, WAITING -> ALLOCATED -> CANCELLED <\q>
   */

  /**
   * Covers:
   * <q>path: WAITING -> CANCELLED<\q>
   */
  @Test
  public void testCancel_1() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = singleton();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> CANCELLED<\q>
   */
  @Test
  public void testCancel_2() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = singleton();
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    entry.allocated();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> RUNNING -> CANCELLED<\q>
   */
  @Test(expected = IllegalStateTransitionException.class)
  public void testCancel_3() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = singleton();
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    entry.allocated();
    entry.run();
    entry.cancel();
  }

  /**
   * Tests {@link CourseEntry#getCurrentTimeslot()}
   */
  @Test
  public void testGetCurrentTimeslot() {
    CoursePlanningEntry entry = singleton();
    assertEquals(Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"), entry.getCurrentTimeslot());
  }

  /**
   * Tests {@link CourseEntry#getCurrentLocation()}
   */
  @Test
  public void testGetCurrentLocation() {
    CoursePlanningEntry entry = singleton();
    assertEquals(CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE), entry.getCurrentLocation());
  }

  /**
   * Tests {@link CourseEntry#conflictLocationWith(PlanningEntry)}
   */
  @Test
  public void testConflictLocationWith() {
    CourseEntry entry1 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry2 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心35", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry3 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 10:00", "2020-05-01 11:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry4 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:00", "2020-05-01 09:30"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));

    assertFalse(entry1.conflictLocationWith(null));
    assertTrue(entry1.conflictLocationWith(entry1));
    assertFalse(entry1.conflictLocationWith(entry2));
    assertFalse(entry1.conflictLocationWith(entry3));
    assertTrue(entry1.conflictLocationWith(entry4));
  }

  /**
   * Tests {@link CourseEntry#conflictResourceWith(PlanningEntry)}
   */
  @Test
  public void testConflictResourceWith() {
    CourseEntry entry1 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry2 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心35", CourseEntry.LOCATION_SHAREABLE));
    CourseEntry entry3 = new CourseEntry("CS101", Timeslot.parse("2020-05-01 10:00", "2020-05-01 11:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    Teacher teacher1 = new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
    Teacher teacher2 = new Teacher("110101199003078849", "FF", "female", "Prof.");

    assertFalse(entry1.conflictResourceWith(null));
    assertFalse(entry1.conflictResourceWith(entry2));

    entry1.setResource(teacher1);
    entry2.setResource(teacher1);
    assertTrue(entry1.conflictResourceWith(entry2));
    entry2.setResource(null);
    assertFalse(entry1.conflictResourceWith(entry2));
    entry2.setResource(teacher2);
    assertFalse(entry1.conflictResourceWith(entry2));

    entry3.setResource(teacher1);
    assertFalse(entry1.conflictResourceWith(entry3));
  }

}
