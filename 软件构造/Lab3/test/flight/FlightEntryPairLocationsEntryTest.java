package flight;

import org.junit.Test;

import base.CommonLocation;
import base.PairLocationsEntry;
import base.PairLocationsEntryTest;
import base.Timeslot;

/**
 * This class {@link FlightEntryPairLocationsEntryTest} tests the interface {@link PairLocationsEntry}implication of
 * {@link FlightEntry}.
 *
 * @author cycleke
 */
public class FlightEntryPairLocationsEntryTest extends PairLocationsEntryTest {

  @Override
  public boolean locationShareabel() {
    return FlightEntry.LOCATION_SHAREABLE;
  }

  @Override
  public PairLocationsEntry singleton() {
    return new FlightEntry("CZ6201", Timeslot.parse("2020-05-01 07:45", "2020-05-01 09:55"),
      CommonLocation.of("Harbin", FlightEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", FlightEntry.LOCATION_SHAREABLE));
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLocations() {
    super.testSetLocations();
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetStartLocation() {
    super.testSetStartLocation();
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetTargetLocation() {
    super.testSetTargetLocation();
  }

}
