package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class {@link EndedStateTest} tests {@link EndedState}.
 *
 * @author cycleke
 */
public abstract class EndedStateTest extends CommonEntryStateTest {
  public abstract EndedState singleton();

  /**
   * Tests {@link EndedState#toString()}
   */
  @Test
  public void testToString() {
    EndedState state = singleton();
    assertEquals("ENDED STATE", state.toString());
  }

}
