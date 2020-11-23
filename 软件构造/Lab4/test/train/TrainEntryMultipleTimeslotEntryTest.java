package train;

import base.CommonLocation;
import base.MultipleTimeslotEntry;
import base.MultipleTimeslotEntryTest;
import base.Timeslot;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link TrainEntryMultipleTimeslotEntryTest} tests the interface {@link MultipleTimeslotEntry} of
 * {@link TrainEntry}
 *
 * @author cycleke
 */
public class TrainEntryMultipleTimeslotEntryTest extends MultipleTimeslotEntryTest {

  @Override
  public MultipleTimeslotEntry singleton() {
    return new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
  }

  @Test
  public void testSetTimeslots() {
    MultipleTimeslotEntry entry = singleton();
    entry.setTimeslots(Arrays.asList(Timeslot.parse("2020-02-25 07:01", "2020-02-25 08:00"),
      Timeslot.parse("2020-02-25 08:03", "2020-02-25 08:45")));
  }

  @Test
  public void testGetTimeslots() {
    MultipleTimeslotEntry entry = singleton();
    assertEquals(Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
      Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")), entry.getTimeslots());
    List<Timeslot> list = Arrays.asList(Timeslot.parse("2020-02-25 07:01", "2020-02-25 08:00"),
      Timeslot.parse("2020-02-25 08:03", "2020-02-25 08:45"));
    entry.setTimeslots(list);
    assertEquals(list, entry.getTimeslots());
  }
}
