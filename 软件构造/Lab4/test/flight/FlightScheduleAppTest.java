package flight;

import org.junit.Test;

/**
 * This class {@link FlightScheduleAppTest} tests {@link FlightScheduleApp}.
 *
 * @author cycleke
 */
public class FlightScheduleAppTest {
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
    FlightScheduleApp.main(new String[]{"FlightSchduleApp"});
  }
}
