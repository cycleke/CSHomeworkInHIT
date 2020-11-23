package flight;

import base.AbstractWaitingStateTest;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This class {@link WaitingStateTest} tests {@link WaitingState}.
 *
 * @author cycleke
 */
public class WaitingStateTest extends AbstractWaitingStateTest {

  public FlightPlanningEntry waitingEntry() {
    return new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
  }

  @Override
  public WaitingState singleton() {
    return (WaitingState) waitingEntry().getState();
  }

  /*
   * Test {@link WaitingState#allocated()} <p> Testing strategy: <p> Partition the
   * inputs as follows: <q>resource: null, not null</q>
   */

  /**
   * Covers:
   * <q>resource: not null</q>
   */
  @Override
  public void testAllocated() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState) object;
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    state.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  /**
   * Covers:
   * <q>resource: null</q>
   */
  @Test(expected = IllegalStateTransitionException.class)
  public void testAllocated_1() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState) object;
    entry.setResource(null);
    state.allocated();
  }

  @Override
  public void testCancel() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState) object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

}
