package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class {@link MultipleLocationsEntryUnmodifiableDecoratorTest} tests
 * {@link MultipleLocationsEntryUnmodifiableDecorator}.
 *
 * @author cycleke
 */
public class MultipleLocationsEntryUnmodifiableDecoratorTest {

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link MultipleLocationsEntryUnmodifiableDecorator#setLocations(List)}
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLocations() {
    MultipleLocationsEntry entry = new MultipleLocationsEntryUnmodifiableDecorator(new MultipleLocationsEntryImpl());
    entry.setLocations(Arrays.asList(CommonLocation.of("Harbin", false), CommonLocation.of("Beijing", false),
      CommonLocation.of("Shanghai", true)));
  }

  /**
   * Tests {@link MultipleLocationsEntry#getLocations()}
   */
  @Test
  public void testGetLocations() {
    MultipleLocationsEntry entry = new MultipleLocationsEntryImpl();
    entry.setLocations(Arrays.asList(CommonLocation.of("Harbin", false), CommonLocation.of("Beijing", false),
      CommonLocation.of("Shanghai", true)));
    entry = new MultipleLocationsEntryUnmodifiableDecorator(entry);
    List<Location> locations = entry.getLocations();
    assertEquals(3, locations.size());
    assertTrue(locations.contains(CommonLocation.of("Harbin", false)));
    assertTrue(locations.contains(CommonLocation.of("Beijing", false)));
    assertTrue(locations.contains(CommonLocation.of("Shanghai", true)));
    assertFalse(locations.contains(CommonLocation.of("Shenzhen", false)));
  }

}
