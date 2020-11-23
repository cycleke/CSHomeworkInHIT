package flight;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class {@link PlaneTest} tests {@link Plane}.
 *
 * @author cycleke
 */
public class PlaneTest {
  /**
   * Tests that assertions are enabled.
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests {@link Plane#Plane(String, String, int, double)}
   */
  @Test
  public void testConstructor() {
    Plane plane = new Plane("B6967", "A340", 332, 23.7);
    assertNotNull(plane);
  }

  /**
   * Tests {@link Plane#getId()}, {@link Plane#getType()} ,
   * {@link Plane#getSeats()} and {@link Plane#getAge()}
   */
  @Test
  public void testGetters() {
    Plane plane = new Plane("B6967", "A340", 332, 23.7);
    assertEquals("B6967", plane.getId());
    assertEquals("A340", plane.getType());
    assertEquals(332, plane.getSeats());
    assertEquals(23.7, plane.getAge(), 1e-9);
  }

  /**
   * Tests {@link Plane#equals(Object)} and {@link Plane#hashCode()}
   */
  @Test
  public void testEqualsAndHashCode() {
    Plane plane1 = new Plane("B6967", "A340", 332, 23.7);
    Plane plane2 = new Plane("B6967", "A340", 332, 23.7);
    Plane plane3 = new Plane("B1554", "B787", 428, 26.1);
    Plane plane4 = new Plane("B6968", "A340", 332, 23.7);
    Plane plane5 = new Plane("B6967", "A340", 332, 23.8);

    assertEquals(plane1, plane1);
    assertEquals(plane1, plane2);
    assertEquals(plane2, plane1);
    assertEquals(plane1.hashCode(), plane1.hashCode());
    assertEquals(plane1.hashCode(), plane2.hashCode());

    assertNotEquals(plane1, null);
    assertNotEquals(plane1, plane3);
    assertNotEquals(plane1, plane4);
    assertNotEquals(plane1, plane5);
    assertNotEquals(plane1, "B6967");
  }

  /**
   * Tests {@link Plane#toString()}
   */
  @Test
  public void testToString() {
    assertEquals("B6967 [type=A340 ,seats=332 ,age=23.7]", new Plane("B6967", "A340", 332, 23.7).toString());
  }
}
