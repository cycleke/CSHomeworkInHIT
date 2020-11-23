package base;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * This class {@link MultipleSortedResourceEntryTest} tests {@link MultipleSortedResourceEntry}.
 *
 * @param <R>
 *          the type of resources
 * @author cycleke
 */
public abstract class MultipleSortedResourceEntryTest<R> {
  public abstract MultipleSortedResourceEntry<R> singleton();

  public abstract List<R> multiResource();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link MultipleSortedResourceEntry#setResources(List)}
   */
  @Test
  public void testStringSetResources() {
    MultipleSortedResourceEntry<R> entry = singleton();
    entry.setResources(multiResource());
  }

  /**
   * Tests {@link MultipleSortedResourceEntry#getResources()}
   */
  @Test
  public void testStringGetResources() {
    MultipleSortedResourceEntry<R> entry = singleton();
    assertEquals(Collections.EMPTY_LIST, entry.getResources());
    List<R> resiurceList = multiResource();
    entry.setResources(resiurceList);
    assertEquals(resiurceList, entry.getResources());
  }

}
