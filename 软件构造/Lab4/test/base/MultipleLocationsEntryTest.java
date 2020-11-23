package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class {@link MultipleLocationsEntryTest} tests {@link MultipleLocationsEntry}.
 *
 * @author cycleke
 */
public abstract class MultipleLocationsEntryTest {

  public abstract MultipleLocationsEntry singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link MultipleLocationsEntry#setLocations(List)}
   */
  @Test
  public void testSetLocations() {
    MultipleLocationsEntry entry = singleton();
    entry.setLocations(Arrays.asList(CommonLocation.of("Harbin", false), CommonLocation.of("Beijing", false),
      CommonLocation.of("Shanghai", true)));
  }

  /**
   * Tests {@link MultipleLocationsEntry#getLocations()}
   */
  @Test
  public void testGetLocations() {
    MultipleLocationsEntry entry = singleton();
    assertEquals(Collections.EMPTY_LIST, entry.getLocations());
    entry.setLocations(Arrays.asList(CommonLocation.of("Harbin", false), CommonLocation.of("Beijing", false),
      CommonLocation.of("Shanghai", true)));
    List<Location> locations = entry.getLocations();
    assertEquals(3, locations.size());
    assertTrue(locations.contains(CommonLocation.of("Harbin", false)));
    assertTrue(locations.contains(CommonLocation.of("Beijing", false)));
    assertTrue(locations.contains(CommonLocation.of("Shanghai", true)));
    assertFalse(locations.contains(CommonLocation.of("Shenzhen", false)));
  }
}
