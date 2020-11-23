package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link AbstractBlockedStateTest} tests {@link AbstractBlockedState}.
 *
 * @author cycleke
 */
public abstract class AbstractBlockedStateTest extends AbstractBlockableEntryStateTest {
  public abstract AbstractBlockedState singleton();

  /**
   * Tests {@link AbstractBlockedState#toString()}
   */
  @Test
  public void testToString() {
    AbstractBlockedState state = singleton();
    assertEquals("BLOCKED STATE", state.toString());
  }

}
