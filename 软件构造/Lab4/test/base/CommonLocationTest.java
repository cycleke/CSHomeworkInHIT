package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    assertEquals(location1, location1);
    assertEquals(location1, location2);
    assertEquals(location1, location3);

    assertNotEquals(location1, null);
    assertNotEquals(location1, "Harbin");
    assertNotEquals(location1, location4);

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
