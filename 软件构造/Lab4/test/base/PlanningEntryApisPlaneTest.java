package base;

import flight.FlightEntry;
import flight.FlightPlanningEntry;
import flight.Plane;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class {@link PlanningEntryApisPlaneTest} tests {@link PlanningEntryApis}.
 *
 * @author cycleke
 */
public abstract class PlanningEntryApisPlaneTest {

  public abstract PlanningEntryApis<Plane> singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link PlanningEntryApis#checkLocationConflict(List)}
   */
  @Test
  public void testCheckLocationConflict() {
    FlightEntry entry1 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry2 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    FlightEntry entry3 = new FlightEntry("CZ6201", Timeslot.parse("2020-05-02 07:45", "2020-05-02 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    assertFalse(singleton().checkLocationConflict(Arrays.asList(entry1, entry2, entry3)));
  }

  /**
   * Tests {@link PlanningEntryApis#checkResourceExclusiveConflict(List)}
   */
  @Test
  public void testCheckResourceExclusiveConflict() {
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

    PlanningEntryApis<Plane> entryAPIs = singleton();
    assertFalse(entryAPIs.checkResourceExclusiveConflict(Arrays.asList(entry1, entry2, entry3)));

    entry1.setResource(plane1);
    entry2.setResource(plane1);
    entry3.setResource(plane2);
    assertTrue(entryAPIs.checkResourceExclusiveConflict(Arrays.asList(entry1, entry2, entry3)));
  }

  /**
   * Tests {@link PlanningEntryApis#findPreEntryPerResource(Object, PlanningEntry, List)}
   */
  @Test
  public void testFindPreEntryPerResource() {
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

    entry1.setResource(plane1);
    entry2.setResource(plane2);
    entry2.setResource(plane2);
    PlanningEntryApis<Plane> entryAPIs = singleton();
    List<FlightPlanningEntry> entries = Arrays.asList(entry1, entry2, entry3);
    assertNull(entryAPIs.findPreEntryPerResource(plane1, entry1, entries));
    assertNull(entryAPIs.findPreEntryPerResource(null, entry1, entries));
    assertNull(entryAPIs.findPreEntryPerResource(plane1, null, entries));
    assertNull(entryAPIs.findPreEntryPerResource(plane1, entry1, null));
    assertEquals(entry2, entryAPIs.findPreEntryPerResource(plane2, entry3, entries));
  }
}
