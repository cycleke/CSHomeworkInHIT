package course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

/**
 * This class {@link AllocatedStateTest} tests {@link AllocatedState}.
 *
 * @author cycleke
 */
public class AllocatedStateTest extends base.AllocatedStateTest {

  public CoursePlanningEntry allocatedEntry() {
    CourseEntry entry = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
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
    CoursePlanningEntry entry = allocatedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof AllocatedState);
    AllocatedState state = (AllocatedState)object;
    state.allocated();
    assertEquals(state, entry.getState());
  }

  @Override
  @Test
  public void testRun() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = allocatedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof AllocatedState);
    AllocatedState state = (AllocatedState)object;
    state.run();
    assertTrue(entry.getState() instanceof RunningState);
  }

  @Override
  @Test
  public void testCancel() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = allocatedEntry();
    Object object = entry.getState();
    assertTrue(object instanceof AllocatedState);
    AllocatedState state = (AllocatedState)object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }
}
