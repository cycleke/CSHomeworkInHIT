package course;

import org.junit.Test;

/**
 * This class {@link CourseScheduleAppTest} tests {@link CourseScheduleApp}.
 *
 * @author cycleke
 */
public class CourseScheduleAppTest {
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
    CourseScheduleApp.main(new String[]{"CourseScheduleApp"});
  }
}
