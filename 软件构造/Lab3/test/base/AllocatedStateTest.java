package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class {@link AllocatedStateTest} tests {@link AllocatedState}.
 *
 * @author cycleke
 */
public abstract class AllocatedStateTest extends CommonEntryStateTest {
  public abstract AllocatedState singleton();

  /**
   * Tests {@link AllocatedState#toString()}
   */
  @Test
  public void testToString() {
    AllocatedState state = singleton();
    assertEquals("ALLOCATED STATE", state.toString());
  }
}
