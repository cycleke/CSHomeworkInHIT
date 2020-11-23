package base;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * This class {@link AbstractCommonEntryStateTest} tests {@link AbstractCommonEntryState}.
 *
 * @author cycleke
 * @implNote 由于 {@link AbstractCommonEntryState} 的方法总是抛出异常，所以 {@link AbstractCommonEntryStateTest} 只测试抛出异常，其子类需要对对应函数重载。
 */
public abstract class AbstractCommonEntryStateTest {
  public abstract AbstractCommonEntryState singleton();

  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link AbstractCommonEntryState#allocated()}
   */
  @Test
  public void testAllocated() throws IllegalStateTransitionException {
    try {
      AbstractCommonEntryState state = singleton();
      state.allocated();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    fail();
  }

  /**
   * Tests {@link AbstractCommonEntryState#cancel()}
   */
  @Test
  public void testCancel() throws IllegalStateTransitionException {
    try {
      AbstractCommonEntryState state = singleton();
      state.cancel();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    fail();
  }

  /**
   * Tests {@link AbstractCommonEntryState#end()}
   */
  @Test
  public void testEnd() throws IllegalStateTransitionException {
    try {
      AbstractCommonEntryState state = singleton();
      state.end();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    fail();
  }

  /**
   * Tests {@link AbstractCommonEntryState#run()}
   */
  @Test
  public void testRun() throws IllegalStateTransitionException {
    try {
      AbstractCommonEntryState state = singleton();
      state.run();
    } catch (IllegalStateTransitionException e) {
      return;
    }
    fail();
  }

}
