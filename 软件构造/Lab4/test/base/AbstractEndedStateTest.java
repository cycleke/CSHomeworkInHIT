package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link AbstractEndedStateTest} tests {@link AbstractEndedState}.
 *
 * @author cycleke
 */
public abstract class AbstractEndedStateTest extends AbstractCommonEntryStateTest {
  public abstract AbstractEndedState singleton();

  /**
   * Tests {@link AbstractEndedState#toString()}
   */
  @Test
  public void testToString() {
    AbstractEndedState state = singleton();
    assertEquals("ENDED STATE", state.toString());
  }

}
