package debug;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link EventManagerTest} is used to test {@link EventManager}.
 *
 * @author cycleke
 */
public class EventManagerTest {

  @Test
  public void testBook() {
    assertEquals(1, EventManager.book(1, 10, 20));
    assertEquals(1, EventManager.book(1, 1, 7));
    assertEquals(2, EventManager.book(1, 10, 22));
    assertEquals(3, EventManager.book(1, 5, 15));
    assertEquals(4, EventManager.book(1, 5, 12));
    assertEquals(4, EventManager.book(1, 7, 10));

    assertEquals(1, EventManager.book(2, 10, 20));
    assertEquals(1, EventManager.book(2, 1, 7));
    assertEquals(2, EventManager.book(2, 10, 22));
    assertEquals(3, EventManager.book(2, 5, 15));
    assertEquals(4, EventManager.book(2, 5, 12));
    assertEquals(4, EventManager.book(2, 7, 10));

    assertEquals(1, EventManager.book(3, 7, 10));
  }
}
