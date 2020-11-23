package course;

import base.CommonLocation;
import base.SingleLocationEntry;
import base.SingleLocationEntryTest;
import base.Timeslot;

/**
 * This class {@link CourseEntrySingleLocationEntryTest} tests the interface {@link SingleLocationEntry} implication of
 * {@link CourseEntry}.
 *
 * @author cycleke
 */
public class CourseEntrySingleLocationEntryTest extends SingleLocationEntryTest {

  @Override
  public SingleLocationEntry singleton() {
    return new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
  }

  @Override
  public boolean locationShareable() {
    return false;
  }

}
