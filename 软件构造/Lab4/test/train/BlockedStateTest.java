package train;

import base.AbstractBlockedStateTest;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlockedStateTest extends AbstractBlockedStateTest {

  public TrainPlanningEntry blockedEntry() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabin.TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabin.TrainCabinType.FIRST_CLASS, 20, 2020)));
    try {
      entry.allocated();
      entry.run();
      entry.block();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return entry;
  }

  @Override
  public BlockedState singleton() {
    return (BlockedState) blockedEntry().getState();
  }

  @Override
  public void testCancel() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = blockedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof BlockedState);
    BlockedState state = (BlockedState) object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  @Override
  public void testRun() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = blockedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof BlockedState);
    BlockedState state = (BlockedState) object;
    state.run();
    assertTrue(entry.getState() instanceof RunningState);
  }

  @Override
  @Test
  public void testBlock() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = blockedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof BlockedState);
    BlockedState state = (BlockedState) object;
    state.block();
    assertEquals(state, entry.getState());
  }
}