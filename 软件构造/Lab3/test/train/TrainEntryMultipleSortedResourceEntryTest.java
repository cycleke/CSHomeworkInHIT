package train;

import java.util.Arrays;
import java.util.List;

import base.CommonLocation;
import base.MultipleSortedResourceEntry;
import base.MultipleSortedResourceEntryTest;
import base.Timeslot;
import train.TrainCabin.TrainCabinType;

/**
 * This class {@link TrainEntryMultipleSortedResourceEntryTest} tests the interface {@link MultipleSortedResourceEntry}
 * of {@link TrainEntry}
 *
 * @author cycleke
 */
public class TrainEntryMultipleSortedResourceEntryTest extends MultipleSortedResourceEntryTest<TrainCabin> {

  @Override
  public MultipleSortedResourceEntry<TrainCabin> singleton() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    return entry;
  }

  @Override
  public List<TrainCabin> multiResource() {
    return Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020));
  }

}
