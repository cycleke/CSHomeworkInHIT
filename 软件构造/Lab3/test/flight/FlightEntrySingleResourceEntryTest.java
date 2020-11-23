package flight;

import base.CommonLocation;
import base.SingleResourceEntry;
import base.SingleResourceEntryTest;
import base.Timeslot;

/**
 * This class {@link FlightEntrySingleResourceEntryTest} tests the interface {@link SingleResourceEntry} implication of
 * {@link FlightEntry}.
 *
 * @author cycleke
 */
public class FlightEntrySingleResourceEntryTest extends SingleResourceEntryTest<Plane> {

  @Override
  public SingleResourceEntry<Plane> singleton() {
    return new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
  }

  @Override
  public Plane singleResource() {
    return new Plane("B6967", "A340", 332, 23.7);
  }

}
