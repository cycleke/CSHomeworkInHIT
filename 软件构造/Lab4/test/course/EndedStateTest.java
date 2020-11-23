package course;

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

  public CoursePlanningEntry endedEntry() {
    CourseEntry entry = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
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
    CoursePlanningEntry entry = endedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof EndedState);
    EndedState state = (EndedState) object;
    state.end();
    assertEquals(state, entry.getState());
  }

}
