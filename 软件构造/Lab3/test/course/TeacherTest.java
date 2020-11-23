package course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class {@link TeacherTest} tests {@link Teacher}.
 *
 * @author cycleke
 */
public class TeacherTest {
  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link Teacher#Teacher(String)}
   */
  @Test
  public void testConstructor() {
    assertNotNull(new Teacher("110101199003077117", "Zhang San", "male", "Instructor"));
  }

  @Test
  public void testGetters() {
    Teacher teacher = new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
    assertEquals("110101199003077117", teacher.getIdNumber());
    assertEquals("Zhang San", teacher.getName());
    assertEquals("male", teacher.getSex());
    assertEquals("Instructor", teacher.getTitle());
  }

  /**
   * Tests {@link Teacher#equals(Object)} and {@link Teacher#hashCode()}
   */
  @Test
  public void testEqualsAndHashCode() {
    Teacher teacher1 = new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
    Teacher teacher2 = new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
    Teacher teacher3 = new Teacher("110101199003078849", "FF", "female", "Prof.");

    assertTrue(teacher1.equals(teacher1));
    assertTrue(teacher1.equals(teacher2));
    assertTrue(teacher2.equals(teacher1));
    assertEquals(teacher1.hashCode(), teacher1.hashCode());
    assertEquals(teacher1.hashCode(), teacher2.hashCode());

    assertFalse(teacher1.equals(null));
    assertFalse(teacher1.equals(teacher3));
    assertFalse(teacher1.equals("110101199003077117"));
  }

  /**
   * Tests {@link Teacher#toString()}
   */
  @Test
  public void testToString() {
    assertEquals("Zhang San [id number=110101199003077117, sex=male, title=Instructor]",
      new Teacher("110101199003077117", "Zhang San", "male", "Instructor").toString());
  }
}
