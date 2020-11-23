package course;

import base.CommonLocation;
import base.Timeslot;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FactoryTest {

  @Test
  public void createPlanningEntry() {
    CoursePlanningEntry entry = Factory.FACTORY.createPlanningEntry("ECO101", Collections.singletonList(Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55")),
      Collections.singletonList(CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE)));
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }
}