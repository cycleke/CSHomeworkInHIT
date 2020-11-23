package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class {@link PairLocationsEntryTest} tests {@link PairLocationsEntry}.
 *
 * @author cycleke
 */
public abstract class PairLocationsEntryTest {
  public abstract boolean locationShareable();

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
   * Tests {@link PairLocationsEntry#setStartLocation(Location)}
   */
  @Test
  public void testSetStartLocation() {
    PairLocationsEntry entry = singleton();
    entry.setStartLocation(CommonLocation.of("Harbin", locationShareable()));
    assertEquals(CommonLocation.of("Harbin", locationShareable()), entry.getStartLocation());
  }

  /**
   * Tests {@link PairLocationsEntry#setTargetLocation(Location)}
   */
  @Test
  public void testSetTargetLocation() {
    PairLocationsEntry entry = singleton();
    entry.setTargetLocation(CommonLocation.of("Beijing", locationShareable()));
    assertEquals(CommonLocation.of("Beijing", locationShareable()), entry.getTargetLocation());
  }

  /**
   * Tests {@link PairLocationsEntry#setLocations(List)} )}
   *
   * <p>
   * Testing strategy
   * </p>
   * <p>
   * Partition the inputs as follows:
   * <q>start == target?: true, false<\q>
   */
  @Test
  public void testSetLocations() {
    PairLocationsEntry entry = singleton();
    List<Location> locations = Arrays.asList(CommonLocation.of("Harbin", locationShareable()),
      CommonLocation.of("Beijing", locationShareable()));
    entry.setLocations(locations);
    assertEquals(locations, entry.getLocations());

    locations.set(1, CommonLocation.of("Beijing", locationShareable()));
    entry.setLocations(locations);
    assertEquals(locations, entry.getLocations());
  }
}
