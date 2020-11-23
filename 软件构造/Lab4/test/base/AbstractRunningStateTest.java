package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link AbstractRunningStateTest} tests {@link AbstractRunningState}.
 *
 * @author cycleke
 */
public abstract class AbstractRunningStateTest extends AbstractCommonEntryStateTest {
  public abstract AbstractRunningState singleton();

  /**
   * Tests {@link AbstractRunningState#toString()}
   */
  @Test
  public void testToString() {
    AbstractRunningState state = singleton();
    assertEquals("RUNNING STATE", state.toString());
  }

}
