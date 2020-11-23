package course;

import base.CommonLocation;
import base.SingleResourceEntry;
import base.SingleResourceEntryTest;
import base.Timeslot;

/**
 * This class {@link CourseEntrySingleResourceEntryTest} tests the interface {@link SingleResourceEntry} implication of
 * {@link CourseEntry}.
 *
 * @author cycleke
 */
public class CourseEntrySingleResourceEntryTest extends SingleResourceEntryTest<Teacher> {

  @Override
  public SingleResourceEntry<Teacher> singleton() {
    return new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
  }

  @Override
  public Teacher singleResource() {
    return new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
  }

}
