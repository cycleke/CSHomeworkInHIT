package flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Location;
import base.PlanningEntry;
import base.Timeslot;

/**
 * This class {@link FlightEntryOthersTest} tests the other functions of {@link FlightEntry}.
 *
 * @author cycleke
 */
public class FlightEntryOthersTest {
  public FlightPlanningEntry singleton() {
    return new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
  }

  /**
   * Tests {@link FlightEntry#FlightEntry(String, Timeslot, base.Location, base.Location)}
   */
  @Test
  public void testConstructor() {
    FlightPlanningEntry entry = singleton();
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }

  /**
   * Tests {@link FlightEntry#allocated()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testAllocated() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    entry.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  /**
   * Tests {@link FlightEntry#run()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testRun() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    entry.allocated();
    entry.run();
    assertTrue(entry.getState() instanceof RunningState);
  }

  /**
   * Tests {@link FlightEntry#end()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testEnd() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    entry.allocated();
    entry.run();
    entry.end();
    assertTrue(entry.getState() instanceof EndedState);
  }

  /**
   * Tests {@link FlightEntry#cancel()}
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>path to cancelled state: WAITING -> CANCELLED, WAITING -> ALLOCATED -> CANCELLED <\q>
   */

  /**
   * Covers:
   * <q>path: WAITING -> CANCELLED<\q>
   */
  @Test
  public void testCancel_1() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> CANCELLED<\q>
   */
  @Test
  public void testCancel_2() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    entry.allocated();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> RUNNING -> CANCELLED<\q>
   *
   * @throws IllegalStateTransitionException
   */
  @Test(expected = IllegalStateTransitionException.class)
  public void testCancel_3() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    entry.allocated();
    entry.run();
    entry.cancel();
  }

  /**
   * Tests {@link FlightEntry#getCurrentTimeslot()}
   */
  @Test
  public void testGetCurrentTimeslot() {
    FlightPlanningEntry entry = singleton();
    assertEquals(Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"), entry.getCurrentTimeslot());
  }

  /**
   * Tests {@link FlightEntry#getCurrentLocation()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testGetCurrentLocation() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = singleton();
    Location startLocation = entry.getStartLocation();
    Location targetLocation = entry.getTargetLocation();
    assertEquals(startLocation, entry.getCurrentLocation());

    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    entry.allocated();
    assertEquals(startLocation, entry.getCurrentLocation());

    entry.run();
    assertEquals(startLocation, entry.getCurrentLocation());

    entry.end();
    assertEquals(targetLocation, entry.getCurrentLocation());
  }

  /**
   * Tests {@link FlightEntry#conflictLocationWith(PlanningEntry)}
   */
  @Test
  public void testConflictLocationWith() {
    FlightEntry entry1 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry2 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry3 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-02 07:45", "2020-05-02 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));

    assertFalse(entry1.conflictLocationWith(null));
    assertFalse(entry1.conflictLocationWith(entry1));
    assertFalse(entry1.conflictLocationWith(entry2));
    assertFalse(entry1.conflictLocationWith(entry3));
  }

  /**
   * Tests {@link FlightEntry#conflictResourceWith(PlanningEntry)}
   */
  @Test
  public void testConflictResourceWith() {
    FlightEntry entry1 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry2 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry3 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-02 07:45", "2020-05-02 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    Plane plane1 = new Plane("B6967", "A340", 332, 23.7);
    Plane plane2 = new Plane("B1554", "B787", 428, 26.1);

    assertFalse(entry1.conflictResourceWith(null));
    assertFalse(entry1.conflictResourceWith(entry2));

    entry1.setResource(plane1);
    entry2.setResource(plane1);
    assertTrue(entry1.conflictResourceWith(entry2));
    entry2.setResource(null);
    assertFalse(entry1.conflictResourceWith(entry2));
    entry2.setResource(plane2);
    assertFalse(entry1.conflictResourceWith(entry2));

    entry3.setResource(plane1);
    assertFalse(entry1.conflictResourceWith(entry3));
  }

  /**
   * Tests {@link FlightEntry#parse(File)}
   */
  @Test
  public void testPares() {
    try {
      assertEquals(34264,
        FlightEntry.parse(new File("test/flight/Spring2020_HITCS_SC_Lab3/FlightSchedule_1.txt")).size());
      assertEquals(305,
        FlightEntry.parse(new File("test/flight/Spring2020_HITCS_SC_Lab3/FlightSchedule_2.txt")).size());
      assertEquals(3175,
        FlightEntry.parse(new File("test/flight/Spring2020_HITCS_SC_Lab3/FlightSchedule_3.txt")).size());
      assertEquals(25989,
        FlightEntry.parse(new File("test/flight/Spring2020_HITCS_SC_Lab3/FlightSchedule_4.txt")).size());
      assertEquals(28, FlightEntry.parse(new File("test/flight/Spring2020_HITCS_SC_Lab3/FlightSchedule_5.txt")).size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
