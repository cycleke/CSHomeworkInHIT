package base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class {@link MultipleSortedResourceEntryImplStringTest} tests {@link MultipleSortedResourceEntryImpl}, with R =
 * String.
 *
 * @author cycleke
 */
public class MultipleSortedResourceEntryImplStringTest extends MultipleSortedResourceEntryTest<String> {

  @Override
  public MultipleSortedResourceEntry<String> singleton() {
    return new MultipleSortedResourceEntryImpl<>();
  }

  @Override
  public List<String> multiResource() {
    return Arrays.stream(
      "Free software means the users have the freedom to run, copy, distribute, study, change and improve the software."
        .split(" "))
      .collect(Collectors.toList());
  }

}
