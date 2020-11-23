package base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class {@link CommonLocationTest} tests {@link CommonLocation}.
 *
 * @author cycleke
 */
public class CommonLocationTest extends LocationTest {

  @Override
  public Location constructor(String name, boolean shareable) {
    return new CommonLocation(name, shareable);
  }

  @Override
  public Location ofMethod(String name, boolean shareable) {
    return CommonLocation.of(name, shareable);
  }

  /**
   * Tests {@link CommonLocation#equals(Object)} and {@link CommonLocation#hashCode()}
   */
  @Test
  public void testEqualsAndHashCode() {
    Location location1 = ofMethod("Harbin", false);
    Location location2 = ofMethod("Harbin", false);
    Location location3 = ofMethod("Harbin", true);
    Location location4 = ofMethod("Beijing", false);
    assertTrue(location1.equals(location1));
    assertTrue(location1.equals(location2));
    assertTrue(location1.equals(location3));

    assertFalse(location1.equals(null));
    assertFalse(location1.equals("Harbin"));
    assertFalse(location1.equals(location4));

    assertEquals(location1.hashCode(), location2.hashCode());
    assertEquals(location1.hashCode(), location3.hashCode());
  }

  /**
   * Tests {@link CommonLocation#toString()}
   */
  @Test
  public void testToString() {
    assertEquals("", ofMethod("", false).toString());
    assertEquals("a", ofMethod("a", true).toString());
    assertEquals("Harbin", ofMethod("Harbin", true).toString());
  }

}
