package base;

import org.junit.Test;

/**
 * This class {@link BlockableEntryStateTest} tests {@link BlockableEntryState}.
 *
 * @author cycleke
 */
public abstract class BlockableEntryStateTest extends CommonEntryStateTest {
  public abstract BlockableEntryState singleton();

  @Test(expected = IllegalStateTransitionException.class)
  public void testBlock() throws IllegalStateTransitionException {
    BlockableEntryState state = singleton();
    state.block();
  }
}
