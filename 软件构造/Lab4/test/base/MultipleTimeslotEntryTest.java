package base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.assertEquals;

/**
 * This class {@link MultipleTimeslotEntryTest} tests {@link MultipleTimeslotEntry}.
 *
 * @author cycleke
 */
public abstract class MultipleTimeslotEntryTest {
  public abstract MultipleTimeslotEntry singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  @Test
  public void testSetTimeslots() {
    MultipleTimeslotEntry entry = singleton();
    entry.setTimeslots(new ArrayList<>(0));
  }

  @Test
  public void testGetTimeslots() {
    MultipleTimeslotEntry entry = singleton();
    entry.setTimeslots(new ArrayList<>(0));
    assertEquals(EMPTY_LIST, entry.getTimeslots());
    List<Timeslot> list = Arrays.asList(Timeslot.parse("2020-05-01 00:00", "2020-05-02 00:00"),
      Timeslot.parse("2020-05-02 00:00", "2020-05-03 00:00"), Timeslot.parse("2020-05-03 00:00", "2020-05-04 00:00"),
      Timeslot.parse("2020-05-04 00:00", "2020-05-05 00:00"));
    entry.setTimeslots(list);
    assertEquals(list, entry.getTimeslots());
  }
}
