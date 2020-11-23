package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link SingleResourceEntryTest} tests {@link SingleResourceEntry}.
 *
 * @param <R> the type of resource
 * @author cycleke
 */
public abstract class SingleResourceEntryTest<R> {
  public abstract SingleResourceEntry<R> singleton();

  public abstract R singleResource();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link SingleResourceEntry#setResource(Object)}
   */
  @Test
  public void testSetResource() {
    SingleResourceEntry<R> entry = singleton();
    entry.setResource(singleResource());
  }

  /**
   * Tests {@link SingleResourceEntry#getResource()}
   */
  @Test
  public void testGetResource() {
    SingleResourceEntry<R> entry = singleton();
    R resource = singleResource();
    entry.setResource(resource);
    assertEquals(resource, entry.getResource());
  }
}
