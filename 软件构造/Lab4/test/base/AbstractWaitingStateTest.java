package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link AbstractWaitingStateTest} tests {@link AbstractWaitingState}.
 *
 * @author cycleke
 */
public abstract class AbstractWaitingStateTest extends AbstractCommonEntryStateTest {
  public abstract AbstractWaitingState singleton();

  /**
   * Tests {@link AbstractWaitingState#toString()}
   */
  @Test
  public void testToString() {
    AbstractWaitingState state = singleton();
    assertEquals("WAITING STATE", state.toString());
  }

}
