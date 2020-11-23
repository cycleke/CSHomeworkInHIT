package train;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import base.BlockableEntry;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import train.TrainCabin.TrainCabinType;

/**
 * This class {@link WaitingStateTest} tests {@link WaitingState}.
 *
 * @author cycleke
 */
public class WaitingStateTest extends base.WaitingStateTest {

  public TrainPlanningEntry waitingEntry() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    return entry;
  }

  @Override
  public WaitingState singleton() {
    return (WaitingState)waitingEntry().getState();
  }

  @Override
  public void testAllocated() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState)object;
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    state.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  @Override
  public void testCancel() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState)object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  @Test(expected = IllegalStateTransitionException.class)
  public void testBlock() throws IllegalStateTransitionException {
    BlockableEntry state = singleton();
    state.block();
  }
}
