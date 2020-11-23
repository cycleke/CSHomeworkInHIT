package course;

import static org.junit.Assert.assertTrue;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

/**
 * This class {@link WaitingStateTest} tests {@link WaitingState}.
 *
 * @author cycleke
 */
public class WaitingStateTest extends base.WaitingStateTest {

  public CoursePlanningEntry waitingEntry() {
    CourseEntry entry = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
    return entry;
  }

  @Override
  public WaitingState singleton() {
    return (WaitingState)waitingEntry().getState();
  }

  @Override
  public void testAllocated() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState)object;
    entry.setResource(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
    state.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  @Override
  public void testCancel() throws IllegalStateTransitionException {
    CoursePlanningEntry entry = waitingEntry();
    Object object = entry.getState();
    assertTrue(object instanceof WaitingState);
    WaitingState state = (WaitingState)object;
    state.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

}
