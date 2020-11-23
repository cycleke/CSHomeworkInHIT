package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link AbstractCancelledStateTest} tests {@link AbstractCancelledState}.
 *
 * @author cycleke
 */
public abstract class AbstractCancelledStateTest extends AbstractCommonEntryStateTest {
  public abstract AbstractCancelledState singleton();

  /**
   * Tests {@link AbstractCancelledState#toString()}
   */
  @Test
  public void testToString() {
    AbstractCancelledState state = singleton();
    assertEquals("CANCELLED STATE", state.toString());
  }

}
