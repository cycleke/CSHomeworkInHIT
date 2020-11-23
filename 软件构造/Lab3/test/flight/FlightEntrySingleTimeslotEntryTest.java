package flight;

import base.CommonLocation;
import base.SingleTimeslotEntry;
import base.SingleTimeslotEntryTest;
import base.Timeslot;

/**
 * This class {@link FlightEntrySingleTimeslotEntryTest} tests the interface {@link SingleTimeslotEntry} implication of
 * {@link FlightEntry}.
 *
 * @author cycleke
 */
public class FlightEntrySingleTimeslotEntryTest extends SingleTimeslotEntryTest {

  @Override
  public SingleTimeslotEntry singleton() {
    return new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
  }

}
