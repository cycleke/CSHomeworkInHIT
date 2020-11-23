package flight;

import base.AbstractEndedStateTest;
import base.CommonLocation;
import base.Timeslot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class {@link EndedStateTest} tests {@link EndedState}.
 *
 * @author cycleke
 */
public class EndedStateTest extends AbstractEndedStateTest {

  public FlightPlanningEntry endedEntry() {
    FlightEntry entry = new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
    entry.setResource(new Plane("B6967", "A340", 332, 23.7));
    try {
      entry.allocated();
      entry.run();
      entry.end();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return entry;
  }

  @Override
  public EndedState singleton() {
    return (EndedState) endedEntry().getState();
  }

  @Override
  public void testEnd() {
    FlightPlanningEntry entry = endedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof EndedState);
    EndedState state = (EndedState) object;
    state.end();
    assertEquals(state, entry.getState());
  }

}
