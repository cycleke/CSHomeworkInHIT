package course;

import org.junit.Test;

import static org.junit.Assert.*;

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
   * Tests {@link Teacher#Teacher(String, String, String, String)}
   */
  @Test
  public void testConstructor() {
    Teacher teacher = new Teacher("110101199003077117", "Zhang San", "male", "Instructor");
    assertNotNull(teacher);
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

    assertEquals(teacher1, teacher1);
    assertEquals(teacher1, teacher2);
    assertEquals(teacher2, teacher1);
    assertEquals(teacher1.hashCode(), teacher1.hashCode());
    assertEquals(teacher1.hashCode(), teacher2.hashCode());

    assertNotEquals(teacher1, null);
    assertNotEquals(teacher1, teacher3);
    assertNotEquals(teacher1, "110101199003077117");
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
