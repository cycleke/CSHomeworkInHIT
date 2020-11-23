package train;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import base.BlockableEntry;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import train.TrainCabin.TrainCabinType;

/**
 * This class {@link AllocatedStateTest} tests {@link AllocatedState}.
 *
 * @author cycleke
 */
public class AllocatedStateTest extends base.AllocatedStateTest {

  public TrainPlanningEntry allocatedEntry() {
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
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return entry;
  }

  @Override
  public AllocatedState singleton() {
    return (AllocatedState)allocatedEntry().getState();
  }

  @Override
  @Test
  public void testAllocated() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = allocatedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof AllocatedState);
    AllocatedState state = (AllocatedState)object;
    state.allocated();
    assertEquals(state, entry.getState());
  }

  @Override
  @Test
  public void testRun() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = allocatedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof AllocatedState);
    AllocatedState state = (AllocatedState)object;
    state.run();
    assertTrue(entry.getState() instanceof RunningState);
  }

  @Override
  @Test
  public void testCancel() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = allocatedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof AllocatedState);
    AllocatedState state = (AllocatedState)object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  @Test(expected = IllegalStateTransitionException.class)
  public void testBlock() throws IllegalStateTransitionException {
    BlockableEntry state = singleton();
    state.block();
  }
}
