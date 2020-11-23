package base;

import java.util.Arrays;
import java.util.List;

/**
 * This class {@link MultipleSortedResourceEntryImplIntegerTest} tests {@link MultipleSortedResourceEntryImpl}, with R =
 * Integer.
 *
 * @author cycleke
 */
public class MultipleSortedResourceEntryImplIntegerTest extends MultipleSortedResourceEntryTest<Integer> {

  @Override
  public MultipleSortedResourceEntry<Integer> singleton() {
    return new MultipleSortedResourceEntryImpl<>();
  }

  @Override
  public List<Integer> multiResource() {
    return Arrays.asList(0, 1, -1, 2, -2, 3, -3);
  }

}
