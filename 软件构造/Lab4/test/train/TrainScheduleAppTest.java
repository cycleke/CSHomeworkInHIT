package train;

import org.junit.Test;

/**
 * This class {@link TrainScheduleAppTest} tests {@link TrainScheduleApp}.
 *
 * @author cycleke
 */
public class TrainScheduleAppTest {
  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests launcher.
   */
  @Test
  public void testLauncher() {
    TrainScheduleApp.main(new String[]{"TrainScheduleApp"});
  }
}
