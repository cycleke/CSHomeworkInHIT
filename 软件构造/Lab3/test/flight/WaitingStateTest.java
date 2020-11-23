package flight;

import static org.junit.Assert.assertTrue;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

/**
 * This class {@link WaitingStateTest} tests {@link WaitingState}.
 *
 * @author cycleke
 */
public class WaitingStateTest extends base.WaitingStateTest {

  public FlightPlanningEntry waitingEntry() {
    FlightEntry entry = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    return entry;
  }

  @Override
  public WaitingState singleton() {
    return (WaitingState)waitingEntry().getState();
  }

  @Override
  public void testAllocated() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState)object;
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    state.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  @Override
  public void testCancel() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState)object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

}
