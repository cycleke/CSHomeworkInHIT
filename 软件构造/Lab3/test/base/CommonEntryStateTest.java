package base;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class {@link CommonEntryStateTest} tests {@link CommonEntryState}.
 *
 * @implNote 由于 {@link CommonEntryState} 的方法总是抛出异常，所以 {@link CommonEntryStateTest} 只测试抛出异常，其子类需要对对应函数重载。
 * @author cycleke
 */
public abstract class CommonEntryStateTest {
  public abstract CommonEntryState singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link CommonEntryState#allocated()}
   */
  @Test
  public void testAllocated() throws IllegalStateTransitionException {
    try {
      CommonEntryState state = singleton();
      state.allocated();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    assertTrue(false);
  }

  /**
   * Tests {@link CommonEntryState#cancel()}
   */
  @Test
  public void testCancel() throws IllegalStateTransitionException {
    try {
      CommonEntryState state = singleton();
      state.cancel();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    assertTrue(false);
  }

  /**
   * Tests {@link CommonEntryState#end()}
   */
  @Test
  public void testEnd() throws IllegalStateTransitionException {
    try {
      CommonEntryState state = singleton();
      state.end();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    assertTrue(false);
  }

  /**
   * Tests {@link CommonEntryState#run()}
   */
  @Test
  public void testRun() throws IllegalStateTransitionException {
    try {
      CommonEntryState state = singleton();
      state.run();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    assertTrue(false);
  }

}
