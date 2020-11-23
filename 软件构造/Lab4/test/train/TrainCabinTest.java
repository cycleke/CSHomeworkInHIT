package train;

import org.junit.Test;
import train.TrainCabin.TrainCabinType;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class {@link TrainCabinTest} tests {@link TrainCabin}.
 *
 * @author cycleke
 */
public class TrainCabinTest {
  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link TrainCabin#TrainCabin(int, train.TrainCabin.TrainCabinType, int, int)}
   */
  @Test
  public void testConstructor() {
    TrainCabin trainCabin = new TrainCabin(1, TrainCabinType.BUSINESS, 100, 2017);
    assertNotNull(trainCabin);
  }

  /**
   * Tests {@link TrainCabin#getId()}, {@link TrainCabin#getType()}, {@link TrainCabin#getSeats()} and
   * {@link TrainCabin#getFactoryYear()}
   */
  @Test
  public void testGetters() {
    TrainCabin trainCabin = new TrainCabin(1, TrainCabinType.BUSINESS, 100, 2017);
    assertEquals(1, trainCabin.getId());
    assertEquals(TrainCabinType.BUSINESS, trainCabin.getType());
    assertEquals(100, trainCabin.getSeats());
    assertEquals(2017, trainCabin.getFactoryYear());
  }

  /**
   * Tests {@link TrainCabin#equals(Object)} and {@link TrainCabin#hashCode()}
   */
  @Test
  public void testEqualsAndHashCode() {
    int[] ids = {1, 2, 3, 4, 5};
    TrainCabinType[] types =
      {TrainCabinType.BUSINESS, TrainCabinType.FIRST_CLASS, TrainCabinType.SECOND_CLASS, TrainCabinType.SOFT_SLEEPER,
        TrainCabinType.HARD_SLEEPER, TrainCabinType.HARD_SEAT, TrainCabinType.LUGGAGE_CAR, TrainCabinType.DINING_CAR};
    int[] seatsArray = {1, 10, 100, 1000, Integer.MAX_VALUE};
    int[] years = {2008, 2010, 2015, 2018, 2020};
    List<TrainCabin> cabins = new LinkedList<>();
    for (int id : ids)
      for (TrainCabinType type : types)
        for (int seats : seatsArray)
          for (int year : years) {
            TrainCabin cabin = new TrainCabin(id, type, seats, year);
            TrainCabin cabin1 = new TrainCabin(id, type, seats, year);
            cabins.add(cabin);
            assertEquals(cabin, cabin);
            assertEquals(cabin, cabin1);
            assertEquals(cabin1, cabin);

            assertNotEquals(cabin, null);
            assertNotEquals(cabin, type);

            assertEquals(cabin.hashCode(), cabin1.hashCode());
          }
    for (TrainCabin cabin : cabins)
      for (TrainCabin cabin2 : cabins) {
        if (cabin == cabin2)
          continue;
        assertNotEquals(cabin, cabin2);
      }
  }

  /**
   * Tests {@link TrainCabin#toString()}
   */
  @Test
  public void testToString() {
    TrainCabin trainCabin = new TrainCabin(1, TrainCabinType.BUSINESS, 100, 2017);
    assertEquals("TrainCabin [id=1, type=BUSINESS, seats=100, factory year=2017]", trainCabin.toString());
  }
}
