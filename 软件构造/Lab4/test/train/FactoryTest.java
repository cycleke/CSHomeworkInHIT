package train;

import base.CommonLocation;
import base.Timeslot;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FactoryTest {

  @Test
  public void createPlanningEntry() {
    TrainEntry entry = (TrainEntry) Factory.FACTORY.createPlanningEntry
      ("G1020", Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"), Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")), Arrays.asList(
        CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE), CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE), CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }
}