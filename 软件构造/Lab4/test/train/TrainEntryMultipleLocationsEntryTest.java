package train;

import base.CommonLocation;
import base.MultipleLocationsEntry;
import base.MultipleLocationsEntryTest;
import base.Timeslot;
import org.junit.Test;
import train.TrainCabin.TrainCabinType;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link TrainEntryMultipleLocationsEntryTest} tests the interface {@link MultipleLocationsEntry} of
 * {@link TrainEntry}
 *
 * @author cycleke
 */
public class TrainEntryMultipleLocationsEntryTest extends MultipleLocationsEntryTest {

  @Override
  public MultipleLocationsEntry singleton() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    return entry;
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLocations() {
    super.testSetLocations();
  }

  @Override
  public void testGetLocations() {
    assertEquals(Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
      CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)), singleton().getLocations());
  }

}
