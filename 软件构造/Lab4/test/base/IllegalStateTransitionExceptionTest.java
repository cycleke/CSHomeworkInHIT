package base;

import org.junit.Test;

/**
 * This class {@link IllegalStateTransitionExceptionTest} tests {@link IllegalStateTransitionException}.
 *
 * @author cycleke
 */
public class IllegalStateTransitionExceptionTest {
  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  @Test(expected = IllegalStateTransitionException.class)
  public void testThrow() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException();
  }

  @Test(expected = IllegalStateTransitionException.class)
  public void testThrowWithMessage() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException("Exception");
  }
}
