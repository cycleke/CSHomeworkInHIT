package course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;

/**
 * This class {@link CancelledStateTest} tests {@link CancelledState}.
 *
 * @author cycleke
 */
public class CancelledStateTest extends base.CancelledStateTest {

  public CoursePlanningEntry cancelledEntry() {
    CourseEntry entry = new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
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
    CoursePlanningEntry entry = cancelledEntry();
    Object object = entry.getState();
    assertTrue(object instanceof CancelledState);
    CancelledState state = (CancelledState)object;
    state.cancel();
    assertEquals(state, entry.getState());
  }

}
