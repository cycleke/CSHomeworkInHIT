package base;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * This class {@link SingleTimeslotEntryTest} tests {@link SingleTimeslotEntry}.
 *
 * @author cycleke
 */
public abstract class SingleTimeslotEntryTest {
  public abstract SingleTimeslotEntry singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  @Test
  public void testSetTimeslot() {
    SingleTimeslotEntry entry = singleton();
    LocalDateTime time1 = LocalDateTime.now();
    LocalDateTime time2 = LocalDateTime.now();
    entry.setTimeslot(Timeslot.of(time1, time2));
  }

  @Test
  public void testGetTimeslot() {
    SingleTimeslotEntry entry = singleton();
    LocalDateTime time1 = LocalDateTime.now();

    try {
      Thread.sleep(1000);
    } catch (Throwable e) {
      System.out.println("Error " + e.getMessage());
      e.printStackTrace();
    }

    LocalDateTime time2 = LocalDateTime.now();
    entry.setTimeslot(Timeslot.of(time1, time2));

    assertEquals(Timeslot.of(time1, time2), entry.getTimeslot());
  }
}
