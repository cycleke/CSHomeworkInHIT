package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link AbstractAllocatedStateTest} tests {@link AbstractAllocatedState}.
 *
 * @author cycleke
 */
public abstract class AbstractAllocatedStateTest extends AbstractCommonEntryStateTest {
  public abstract AbstractAllocatedState singleton();

  /**
   * Tests {@link AbstractAllocatedState#toString()}
   */
  @Test
  public void testToString() {
    AbstractAllocatedState state = singleton();
    assertEquals("ALLOCATED STATE", state.toString());
  }
}
