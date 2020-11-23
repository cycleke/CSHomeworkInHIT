package base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class {@link LocationTest}
 */
public abstract class LocationTest {

  /**
   * Overrided the constructor of Location.
   */
  public abstract Location constructor(String name, boolean shareable);

  /**
   * Overrided the of method of Location.
   */
  public abstract Location ofMethod(String name, boolean shareable);

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link Location#Location(String, boolean)}
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>the length of name: 0, 1, > 1<\q>
   * <q>the value of shareable: true, false<\q>
   */
  @Test
  public void testConstructor() {
    assertNotNull(constructor("", true));
    assertNotNull(constructor("", false));
    assertNotNull(constructor("a", true));
    assertNotNull(constructor("b", false));
    assertNotNull(constructor("New York", true));
    assertNotNull(constructor("Beijing", false));
  }

  /**
   * Tests {@link Location#of(String, boolean)}
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>the length of name: 0, 1, > 1<\q>
   * <q>the value of shareable: true, false<\q>
   */
  @Test
  public void testOf() {
    assertNotNull(ofMethod("", true));
    assertNotNull(ofMethod("", false));
    assertNotNull(ofMethod("a", true));
    assertNotNull(ofMethod("b", false));
    assertNotNull(ofMethod("New York", true));
    assertNotNull(ofMethod("Beijing", false));
  }

  /**
   * Tests {@link Location#getName()}
   */
  @Test
  public void testGetName() {
    assertEquals("", ofMethod("", true).getName());
    assertEquals("a", ofMethod("a", false).getName());
    assertEquals("Beijing", ofMethod("Beijing", false).getName());
  }

  /**
   * Tests {@link Location#isShareable()}
   */
  @Test
  public void testIsShareable() {
    assertTrue(ofMethod("Chengdu", true).isShareable());
    assertFalse(ofMethod("Harbin", false).isShareable());
  }
}
