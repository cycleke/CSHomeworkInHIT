package train;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import base.BlockableEntry;
import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

/**
 * This class {@link CancelledStateTest} tests {@link CancelledState}.
 *
 * @author cycleke
 */
public class CancelledStateTest extends base.CancelledStateTest {

  public TrainPlanningEntry cancelledEntry() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
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
    return (CancelledState)cancelledEntry().getState();
  }

  @Override
  public void testCancel() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = cancelledEntry();
    Object object = entry.getState();
    assertTrue(object instanceof CancelledState);
    CancelledState state = (CancelledState)object;
    state.cancel();
    assertEquals(state, entry.getState());
  }

  @Test(expected = IllegalStateTransitionException.class)
  public void testBlock() throws IllegalStateTransitionException {
    BlockableEntry state = singleton();
    state.block();
  }
}
