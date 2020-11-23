package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class {@link CancelledStateTest} tests {@link CancelledState}.
 *
 * @author cycleke
 */
public abstract class CancelledStateTest extends CommonEntryStateTest {
  public abstract CancelledState singleton();

  /**
   * Tests {@link CancelledState#toString()}
   */
  @Test
  public void testToString() {
    CancelledState state = singleton();
    assertEquals("CANCELLED STATE", state.toString());
  }

}
