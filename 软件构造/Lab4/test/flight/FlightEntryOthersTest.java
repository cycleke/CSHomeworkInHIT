package flight;

import base.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * This class {@link FlightEntryOthersTest} tests the other functions of
 * {@link FlightEntry}.
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
   * Tests
   * {@link FlightEntry#FlightEntry(String, Timeslot, base.Location, base.Location)}
   */
  @Test
  public void testConstructor() {
    FlightPlanningEntry entry = singleton();
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }

  /**
   * Tests {@link FlightEntry#allocated()}
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

  /*
   * Tests {@link FlightEntry#cancel()}
   *
   * <p> Testing strategy: </p>
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

  /*
   * Tests {@link FlightEntry#parse(File)} <p> Testing strategy <p> Partition the
   * inputs as follows: <q>The files cover all the exceptions.<\q>
   */

  /**
   * Covers:
   * <q>The file doesn't exist.</q>
   */
  @Test(expected = FileNotFoundException.class)
  public void testParse_0() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_0.txt"));
  }

  /**
   * Covers:
   * <q>The file contains two plane with different attribute.</q>
   */
  @Test(expected = PlaneAttributeConflictException.class)
  public void testParse_1() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_1.txt"));
  }

  /**
   * Covers:
   * <q>The file contains two plane with different attribute.</q>
   */
  @Test(expected = PlaneAttributeConflictException.class)
  public void testParse_2() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_3.txt"));
  }

  /**
   * Covers:
   * <q>The file contains two plane with different attribute</q>
   */
  @Test(expected = PlaneAttributeConflictException.class)
  public void testParse_3() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_4.txt"));
  }

  /**
   * Covers:
   * <q>The valid file</q>
   */
  @Test
  public void testParse_4() throws FileNotFoundException, FileParseException {
    assertEquals(305, FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_2.txt")).size());
    assertEquals(28, FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_5.txt")).size());
  }

  /**
   * Covers:
   * <q>The value of the key missing</q>
   */
  @Test(expected = ValueNotFoundException.class)
  public void testParse_5() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_6.txt"));
  }

  /**
   * Covers:
   * <q>The case of the key mismatches.</q>
   */
  @Test(expected = CaseNotMatchException.class)
  public void testParse_6() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_7.txt"));
  }

  /**
   * Covers:
   * <q>The case of the value mismatches</q>
   */
  @Test(expected = CaseNotMatchException.class)
  public void testParse_7() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_8.txt"));
  }

  /**
   * Covers:
   * <q>The format of the date is wrong</q>
   */
  @Test(expected = DateFormatException.class)
  public void testParse_8() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_9.txt"));
  }

  /**
   * Covers:
   * <q>The format of the date is wrong</q>
   */
  @Test(expected = DateTimeFormatException.class)
  public void testParse_9() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_10.txt"));
  }

  /**
   * Covers:
   * <q>The departure time is bigger than arrival time.</q>
   */
  @Test(expected = DepartureTimeIsNotSmallerThanArrivalTimeException.class)
  public void testParse_10() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_11.txt"));
  }

  /**
   * Covers:
   * <q>a "{" not found.</q>
   */
  @Test(expected = LeftBracketNotFoundException.class)
  public void testParse_11() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_12.txt"));
  }

  /**
   * Covers:
   * <q>a "}" not found.</q>
   */
  @Test(expected = RightBracketNotFoundException.class)
  public void testParse_12() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_13.txt"));
  }

  /**
   * Covers:
   * <q>The format of the date is wrong.</q>
   */
  @Test(expected = MismatchedDateException.class)
  public void testParse_13() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_14.txt"));
  }

  /**
   * Covers:
   * <q>The key mismatched.</q>
   */
  @Test(expected = MismatchedKeyException.class)
  public void testParse_14() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_15.txt"));
  }

  /**
   * Covers:
   * <q>The seats is too larger.</q>
   */
  @Test(expected = NumberOutOfRangeException.class)
  public void testParse_15() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_16.txt"));
  }

  /**
   * Covers:
   * <q>The age is too larger.</q>
   */
  @Test(expected = NumberOutOfRangeException.class)
  public void testParse_16() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_17.txt"));
  }

  /**
   * Covers:
   * <q>The seats is too larger.</q>
   */
  @Test(expected = NumberOutOfRangeException.class)
  public void testParse_17() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_18.txt"));
  }

  /**
   * Covers:
   * <q>Two same entries in the same file.</q>
   */
  @Test(expected = SameElementException.class)
  public void testParse_18() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_19.txt"));
  }

  /**
   * Covers:
   * <q>The time gap is larger than a day.</q>
   */
  @Test(expected = TimeGapTooLargeException.class)
  public void testParse_19() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_20.txt"));
  }

  /**
   * Covers:
   * <q>The value format of plane errors.</q>
   */
  @Test(expected = ValueFormatException.class)
  public void testParse_20() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_21.txt"));
  }

  /**
   * Covers:
   * <q>The value of flight is not in order.</q>
   */
  @Test(expected = ValuesOutOfOrderException.class)
  public void testParse_21() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_22.txt"));
  }

  /**
   * Covers:
   * <q>The value of flight is 3, != 2.</q>
   */
  @Test(expected = WrongNumberOfValuesException.class)
  public void testParse_22() throws FileNotFoundException, FileParseException {
    FlightEntry.parse(new File("test/flight/parseTestData/FlightSchedule_23.txt"));
  }
}
