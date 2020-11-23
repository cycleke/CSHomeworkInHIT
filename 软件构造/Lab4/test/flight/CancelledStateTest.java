package flight;

import base.AbstractCancelledStateTest;
import base.CommonLocation;
import base.Timeslot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class {@link CancelledStateTest} tests {@link CancelledState}.
 *
 * @author cycleke
 */
public class CancelledStateTest extends AbstractCancelledStateTest {

  public FlightPlanningEntry cancelledEntry() {
    FlightEntry entry = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    try {
      entry.cancel();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return entry;
  }

  @Override
  public CancelledState singleton() {
    return (CancelledState) cancelledEntry().getState();
  }

  @Override
  public void testCancel() {
    FlightPlanningEntry entry = cancelledEntry();
    Object object = entry.getState();
    assertTrue(object instanceof CancelledState);
    CancelledState state = (CancelledState) object;
    state.cancel();
    assertEquals(state, entry.getState());
  }

}
