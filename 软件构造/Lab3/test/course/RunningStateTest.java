package course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

/**
 * This class {@link RunningStateTest} tests {@link RunningState}.
 *
 * @author cycleke
 */
public class RunningStateTest extends base.RunningStateTest {

  public CoursePlanningEntry runningEntry() {
    CourseEntry entry = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    try {
      entry.allocated();
      entry.run();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return entry;
  }

  @Override
  public RunningState singleton() {
    return (RunningState)runningEntry().getState();
  }

  @Override
  public void testRun() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = runningEntry();
    Object object = entry.getState();
    assertTrue(object instanceof RunningState);
    RunningState state = (RunningState)object;
    state.run();
    assertEquals(state, entry.getState());
  }

  @Override
  public void testEnd() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = runningEntry();
    Object object = entry.getState();
    assertTrue(object instanceof RunningState);
    RunningState state = (RunningState)object;
    state.end();
    assertTrue(entry.getState() instanceof EndedState);
  }

}
