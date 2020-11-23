package train;

import base.AbstractEndedStateTest;
import base.CommonLocation;
import base.Timeslot;
import train.TrainCabin.TrainCabinType;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class {@link EndedStateTest} tests {@link EndedState}.
 *
 * @author cycleke
 */
public class EndedStateTest extends AbstractEndedStateTest {

  public TrainPlanningEntry endedEntry() {
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
    TrainPlanningEntry entry = endedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof EndedState);
    EndedState state = (EndedState) object;
    state.end();
    assertEquals(state, entry.getState());
  }

}
