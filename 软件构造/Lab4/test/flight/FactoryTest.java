package flight;

import base.CommonLocation;
import base.Timeslot;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FactoryTest {

  @Test
  public void testCreatePlanningEntry() {
    FlightPlanningEntry entry =
      Factory.FACTORY.createPlanningEntry("CZ6201", Collections.singletonList(Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55")), Arrays.asList(CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE), CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE)));
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }
}