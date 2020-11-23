package debug;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class {@link LowestPriceTest} is used to test {@link LowestPrice}.
 *
 * @author cycleke
 */
public class LowestPriceTest {

  private static final LowestPrice LOWEST_PRICE = new LowestPrice();

  @Test
  public void testExample_1() {
    List<Integer> price = Arrays.asList(2, 5);
    List<List<Integer>> special = Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10));
    List<Integer> needs = Arrays.asList(3, 2);
    assertEquals(14, LOWEST_PRICE.shoppingOffers(price, special, needs));
  }

  @Test
  public void testExample_2() {
    List<Integer> price = Arrays.asList(2, 3, 4);
    List<List<Integer>> special = Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9));
    List<Integer> needs = Arrays.asList(1, 2, 1);
    assertEquals(11, LOWEST_PRICE.shoppingOffers(price, special, needs));
  }

  @Test
  public void testExample_4() {
    List<Integer> price = Arrays.asList(1, 2, 3, 4);
    List<List<Integer>> special = Arrays.asList(Arrays.asList(1, 1, 1, 1, 4), Arrays.asList(2, 2, 2, 2, 6),
      Arrays.asList(0, 0, 1, 4, 10));
    List<Integer> needs = Arrays.asList(3, 3, 4, 7);
    assertEquals(20, LOWEST_PRICE.shoppingOffers(price, special, needs));
  }

  @Test
  public void testExample_5() {
    List<Integer> price = Arrays.asList(1, 2, 3, 4);
    List<List<Integer>> special = Arrays.asList(Arrays.asList(1, 1, 1, 1, 11), Arrays.asList(2, 2, 2, 2, 21),
      Arrays.asList(0, 0, 1, 4, 100));
    List<Integer> needs = Arrays.asList(3, 3, 4, 7);
    assertEquals(49, LOWEST_PRICE.shoppingOffers(price, special, needs));
  }
}
