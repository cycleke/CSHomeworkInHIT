package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class {@link WaitingStateTest} tests {@link WaitingState}.
 *
 * @author cycleke
 */
public abstract class WaitingStateTest extends CommonEntryStateTest {
  public abstract WaitingState singleton();

  /**
   * Tests {@link WaitingState#toString()}
   */
  @Test
  public void testToString() {
    WaitingState state = singleton();
    assertEquals("WAITING STATE", state.toString());
  }

}
