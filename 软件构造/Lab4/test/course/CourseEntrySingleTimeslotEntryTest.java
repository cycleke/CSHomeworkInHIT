package course;

import base.CommonLocation;
import base.SingleTimeslotEntry;
import base.SingleTimeslotEntryTest;
import base.Timeslot;

/**
 * This class {@link CourseEntrySingleTimeslotEntryTest} tests the interface {@link SingleTimeslotEntry} implication of
 * {@link CourseEntry}.
 *
 * @author cycleke
 */
public class CourseEntrySingleTimeslotEntryTest extends SingleTimeslotEntryTest {

  @Override
  public SingleTimeslotEntry singleton() {
    return new CourseEntry("CS101", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("正心25", CourseEntry.LOCATION_SHAREABLE));
  }

}
