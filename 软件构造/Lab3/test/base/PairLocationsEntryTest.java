package base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This class {@link PairLocationsEntryTest} tests {@link PairLocationsEntry}.
 *
 * @author cycleke
 */
public abstract class PairLocationsEntryTest {
  public abstract boolean locationShareabel();

  public abstract PairLocationsEntry singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link PairLocationsEntry#getStartLocation()}
   */
  @Test
  public void testGetStartLocation() {
    PairLocationsEntry entry = singleton();
    assertNotNull(entry.getStartLocation());
  }

  /**
   * Tests {@link PairLocationsEntry#getTargetLocation()}
   */
  @Test
  public void testGetTargetLocation() {
    PairLocationsEntry entry = singleton();
    assertNotNull(entry.getTargetLocation());
  }

  /**
   * Tests {@link PairLocationsEntry#getLocations()}
   */
  @Test
  public void testGetLocations() {
    PairLocationsEntry entry = singleton();
    assertNotNull(entry.getLocations());
    assertEquals(2, entry.getLocations().size());
  }

  /**
   * Tests {@link PairLocationsEntry#setStartLocation()}
   */
  @Test
  public void testSetStartLocation() {
    PairLocationsEntry entry = singleton();
    entry.setStartLocation(CommonLocation.of("Harbin", locationShareabel()));
    assertEquals(CommonLocation.of("Harbin", locationShareabel()), entry.getStartLocation());
  }

  /**
   * Tests {@link PairLocationsEntry#setTarsetLocation()}
   */
  @Test
  public void testSetTargetLocation() {
    PairLocationsEntry entry = singleton();
    entry.setTargetLocation(CommonLocation.of("Beijing", locationShareabel()));
    assertEquals(CommonLocation.of("Beijing", locationShareabel()), entry.getTargetLocation());
  }

  /**
   * Tests {@link PairLocationsEntry#setLocations()}
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>start == target?: true, false<\q>
   */
  @Test
  public void testSetLocations() {
    PairLocationsEntry entry = singleton();
    List<Location> locations = Arrays.asList(CommonLocation.of("Harbin", locationShareabel()),
      CommonLocation.of("Beijing", locationShareabel()));
    entry.setLocations(locations);
    assertEquals(locations, entry.getLocations());

    locations.set(1, CommonLocation.of("Beijing", locationShareabel()));
    entry.setLocations(locations);
    assertEquals(locations, entry.getLocations());
  }
}
