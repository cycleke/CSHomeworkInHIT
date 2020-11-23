package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class {@link RunningStateTest} tests {@link RunningState}.
 *
 * @author cycleke
 */
public abstract class RunningStateTest extends CommonEntryStateTest {
  public abstract RunningState singleton();

  /**
   * Tests {@link RunningState#toString()}
   */
  @Test
  public void testToString() {
    RunningState state = singleton();
    assertEquals("RUNNING STATE", state.toString());
  }

}
