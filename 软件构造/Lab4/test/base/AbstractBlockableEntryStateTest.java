package base;

import org.junit.Test;

/**
 * This class {@link AbstractBlockableEntryStateTest} tests {@link AbstractBlockableEntryState}.
 *
 * @author cycleke
 */
public abstract class AbstractBlockableEntryStateTest extends AbstractCommonEntryStateTest {
  public abstract AbstractBlockableEntryState singleton();

  @Test(expected = IllegalStateTransitionException.class)
  public void testBlock() throws IllegalStateTransitionException {
    AbstractBlockableEntryState state = singleton();
    state.block();
  }
}
