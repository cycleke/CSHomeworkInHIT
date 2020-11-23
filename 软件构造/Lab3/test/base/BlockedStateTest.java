package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class {@link BlockedStateTest} tests {@link BlockedState}.
 *
 * @author cycleke
 */
public abstract class BlockedStateTest extends BlockableEntryStateTest {
  public abstract BlockedState singleton();

  /**
   * Tests {@link BlockedState#toString()}
   */
  @Test
  public void testToString() {
    BlockedState state = singleton();
    assertEquals("BLOCKED STATE", state.toString());
  }

}
