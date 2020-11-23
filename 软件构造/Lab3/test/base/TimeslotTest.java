package base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.Test;

/**
 * This class {@link TimeslotTest} tests {@link Timeslot}.
 *
 * @author cycleke
 */
public class TimeslotTest {

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link Timeslot#Timeslot(LocalDateTime, LocalDateTime)}.
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>startTime compare to endTime : < 0 <\q>
   */

  /**
   * Covers:
   * <q>startTime compare to endTime : < 0 <\q>
   */
  @Test
  public void testConstructor_1() {
    LocalDateTime time1 = LocalDateTime.parse("2020-03-01T12:00");
    LocalDateTime time2 = LocalDateTime.parse("2020-03-01T13:00");
    Timeslot timeslot = new Timeslot(time1, time2);
    assertNotNull(timeslot);
  }

  @Test(expected = AssertionError.class)
  public void testConstructor_2() {
    new Timeslot(null, null);
  }

  @Test(expected = AssertionError.class)
  public void testConstructor_3() {
    LocalDateTime time1 = LocalDateTime.parse("2020-03-01T12:00");
    LocalDateTime time2 = LocalDateTime.parse("2020-03-01T13:00");
    new Timeslot(time2, time1);
  }

  @Test(expected = NullPointerException.class)
  public void testConstructor_4() {
    LocalDateTime time = LocalDateTime.parse("2020-03-01T13:00");
    new Timeslot(null, time);
  }

  @Test(expected = AssertionError.class)
  public void testConstructor_5() {
    LocalDateTime time = LocalDateTime.parse("2020-03-01T12:00");
    new Timeslot(time, time);
  }

  /**
   * Tests {@link Timeslot#of(LocalDateTime, LocalDateTime)}.
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>startTime compare to endTime : < 0 , 0 <\q>
   */

  /**
   * Covers:
   * <q>startTime compare to endTime : < 0, 0 <\q>
   */
  @Test
  public void testOf() {
    LocalDateTime time1 = LocalDateTime.parse("2020-03-01T12:00");
    LocalDateTime time2 = LocalDateTime.parse("2020-03-01T13:00");
    assertNotNull(Timeslot.of(time1, time2));
  }

  /**
   * Tests {@link Timeslot#parse(String, String)}.
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>startTime compare to endTime : < 0 , 0 <\q>
   */

  /**
   * Covers:
   * <q>startTime compare to endTime : < 0, 0 <\q>
   */
  @Test
  public void testParse_1() {
    assertNotNull(Timeslot.parse("2020-03-01 14:00", "2020-03-02 14:00"));
    assertNotNull(Timeslot.parse("2020-04-01 14:00", "9999-04-01 14:00"));
  }

  @Test(expected = DateTimeParseException.class)
  public void testParse_2() {
    Timeslot.parse("2020-03-01 00:00", "2020-02-2914:00");
  }

  @Test(expected = DateTimeParseException.class)
  public void testParse_3() {
    Timeslot.parse("2020-99-01 00:00", "2020-02-2914:00");
  }

  /**
   * Tests the getters of {@link Timeslot}.
   */
  @Test
  public void testGetters() {
    Timeslot timeslot = Timeslot.parse("2020-03-01 09:00", "2021-12-31 17:00");
    assertEquals(LocalDateTime.parse("2020-03-01T09:00"), timeslot.getStartTime());
    assertEquals(LocalDateTime.parse("2021-12-31T17:00"), timeslot.getEndTime());
  }

  /**
   * Tests {@link Timeslot#intersect(Timeslot)}
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>compare startTime to another timeslot: -1, -0, 0, +0, 1<\q>
   * <q>compare endTime to another timeslot: -1, -0, 0, +0, 1<\q>
   * <q>-1, -0, 0, +0, 1 mean left outside, left point, inside, right point, right outside<\q>
   */
  @Test
  public void testIntersect() {
    LocalDateTime time0 = LocalDateTime.parse("2020-03-01T08:00");
    LocalDateTime time1 = LocalDateTime.parse("2020-03-01T09:00");
    LocalDateTime time2 = LocalDateTime.parse("2020-03-01T10:00");
    LocalDateTime time3 = LocalDateTime.parse("2020-03-01T11:00");
    LocalDateTime time4 = LocalDateTime.parse("2020-03-01T12:00");
    LocalDateTime time5 = LocalDateTime.parse("2020-03-01T13:00");
    LocalDateTime time6 = LocalDateTime.parse("2020-03-01T14:00");
    LocalDateTime time7 = LocalDateTime.parse("2020-03-01T15:00");

    // self
    Timeslot timeslot = Timeslot.of(time2, time5);
    assertTrue(timeslot.intersect(timeslot));

    // (-1, -1)
    Timeslot timeslot0 = Timeslot.of(time0, time1);
    assertFalse(timeslot.intersect(timeslot0));

    // (-1, -0)
    Timeslot timeslot1 = Timeslot.of(time0, time2);
    assertFalse(timeslot.intersect(timeslot1));

    // (-1, 0)
    Timeslot timeslot2 = Timeslot.of(time0, time4);
    assertTrue(timeslot.intersect(timeslot2));

    // (-1, +0)
    Timeslot timeslot3 = Timeslot.of(time0, time5);
    assertTrue(timeslot.intersect(timeslot3));

    // (-1, 1)
    Timeslot timeslot4 = Timeslot.of(time0, time7);
    assertTrue(timeslot.intersect(timeslot4));

    // (-0, 0)
    Timeslot timeslot5 = Timeslot.of(time2, time4);
    assertTrue(timeslot.intersect(timeslot5));

    // (-0, +0)
    Timeslot timeslot6 = Timeslot.of(time2, time5);
    assertTrue(timeslot.intersect(timeslot6));

    // (-0, 1)
    Timeslot timeslot7 = Timeslot.of(time2, time7);
    assertTrue(timeslot.intersect(timeslot7));

    // (0, 0)
    Timeslot timeslot8 = Timeslot.of(time3, time4);
    assertTrue(timeslot.intersect(timeslot8));

    // (0, +0)
    Timeslot timeslot9 = Timeslot.of(time3, time5);
    assertTrue(timeslot.intersect(timeslot9));

    // (0, 1)
    Timeslot timeslot10 = Timeslot.of(time3, time7);
    assertTrue(timeslot.intersect(timeslot10));

    // (+0, 1)
    Timeslot timeslot11 = Timeslot.of(time5, time7);
    assertFalse(timeslot.intersect(timeslot11));

    // (1, 1)
    Timeslot timeslot12 = Timeslot.of(time6, time7);
    assertFalse(timeslot.intersect(timeslot12));

  }

  /**
   * Tests {@link Timeslot#toString()}
   */
  @Test
  public void testToString() {
    assertEquals("(2020-03-01 12:00, 2020-03-01 14:00)",
      Timeslot.parse("2020-03-01 12:00", "2020-03-01 14:00").toString());
  }

  /**
   * Tests {@link Timeslot#equals(Object)} and {@link Timeslot#hashCode()}.
   *
   */
  @Test
  public void testEqualsAndHashCode() {
    Timeslot timeslot1 = Timeslot.parse("2020-03-01 09:00", "2020-12-31 17:00");
    Timeslot timeslot2 = Timeslot.parse("2020-03-01 09:00", "2020-12-31 17:00");
    Timeslot timeslot3 = Timeslot.parse("2020-03-01 09:00", "2020-12-30 17:00");
    Timeslot timeslot4 = Timeslot.parse("2019-03-01 09:00", "2020-12-30 17:00");
    assertFalse(timeslot1.equals(null));
    assertFalse(timeslot1.equals("(2020-03-01 09:00, 2020-12-31 17:00"));
    assertTrue(timeslot1.equals(timeslot1));
    assertTrue(timeslot1.equals(timeslot2));
    assertTrue(timeslot2.equals(timeslot1));
    assertFalse(timeslot1.equals(timeslot3));
    assertFalse(timeslot1.equals(timeslot4));

    assertEquals(timeslot1.hashCode(), timeslot1.hashCode());
    assertEquals(timeslot1.hashCode(), timeslot2.hashCode());
  }
}
