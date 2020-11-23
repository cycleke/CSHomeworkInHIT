package train;

import base.AbstractRunningStateTest;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import train.TrainCabin.TrainCabinType;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class {@link RunningStateTest} tests {@link RunningState}.
 *
 * @author cycleke
 */
public class RunningStateTest extends AbstractRunningStateTest {

  public TrainPlanningEntry runningEntry() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    try {
      entry.allocated();
      entry.run();
      entry.block();
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
    TrainPlanningEntry entry = runningEntry();
    Object object = entry.getState();
    assertTrue(object instanceof RunningState);
    RunningState state = (RunningState) object;
    state.run();
    assertEquals(state, entry.getState());
  }

  @Override
  public void testEnd() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = runningEntry();
    Object object = entry.getState();
    assertTrue(object instanceof RunningState);
    RunningState state = (RunningState) object;
    state.end();
    assertTrue(entry.getState() instanceof EndedState);
  }

}
