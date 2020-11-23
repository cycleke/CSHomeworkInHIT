package flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
   * Tests {@link Plane#Plane(String)}
   */
  @Test
  public void testConstructor() {
    assertNotNull(new Plane("B6967", "A340", 332, 23.7));
  }

  /**
   * Tests {@link Plane#getId()}, {@link Plane#getType()} , {@link Plane#getSeats()} and {@link Plane#getAge()}
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

    assertTrue(plane1.equals(plane1));
    assertTrue(plane1.equals(plane2));
    assertTrue(plane2.equals(plane1));
    assertEquals(plane1.hashCode(), plane1.hashCode());
    assertEquals(plane1.hashCode(), plane2.hashCode());

    assertFalse(plane1.equals(null));
    assertFalse(plane1.equals(plane3));
    assertFalse(plane1.equals("B6967"));
  }

  /**
   * Tests {@link Plane#toString()}
   */
  @Test
  public void testToString() {
    assertEquals("B6967 [type=A340 ,seats=332 ,age=23.7]", new Plane("B6967", "A340", 332, 23.7).toString());
  }
}
