package flight;

import base.AbstractRunningStateTest;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class {@link RunningStateTest} tests {@link RunningState}.
 *
 * @author cycleke
 */
public class RunningStateTest extends AbstractRunningStateTest {

  public FlightPlanningEntry runningEntry() {
    FlightEntry entry = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    try {
      entry.allocated();
      entry.run();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return entry;
  }

  @Override
  public RunningState singleton() {
    return (RunningState) runningEntry().getState();
  }

  @Override
  public void testRun() {
    FlightPlanningEntry entry = runningEntry();
    Object object = entry.getState();
    assertTrue(object instanceof RunningState);
    RunningState state = (RunningState) object;
    state.run();
    assertEquals(state, entry.getState());
  }

  @Override
  public void testEnd() throws IllegalStateTransitionException {
    FlightPlanningEntry entry = runningEntry();
    Object object = entry.getState();
    assertTrue(object instanceof RunningState);
    RunningState state = (RunningState) object;
    state.end();
    assertTrue(entry.getState() instanceof EndedState);
  }

}
