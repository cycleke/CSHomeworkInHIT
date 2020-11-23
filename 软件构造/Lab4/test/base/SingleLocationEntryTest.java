package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class {@link SingleLocationEntryTest} tests {@link SingleLocationEntry}.
 *
 * @author cycleke
 */
public abstract class SingleLocationEntryTest {
  public abstract boolean locationShareable();

  public abstract SingleLocationEntry singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link SingleLocationEntry#getLocation()}
   */
  @Test
  public void testGetLocation() {
    SingleLocationEntry entry = singleton();
    assertNotNull(entry.getLocation());
  }

  /**
   * Tests {@link SingleLocationEntry#setLocation(Location)}
   */
  @Test
  public void testSetLocation() {
    SingleLocationEntry entry = singleton();
    entry.setLocation(CommonLocation.of("Harbin", locationShareable()));
    assertEquals(CommonLocation.of("Harbin", locationShareable()), entry.getLocation());
    entry.setLocation(CommonLocation.of("Beijing", locationShareable()));
    assertEquals(CommonLocation.of("Beijing", locationShareable()), entry.getLocation());
  }
}
