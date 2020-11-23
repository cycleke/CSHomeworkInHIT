package base;

/**
 * This class {@link SingleResourceEntryImplIntegerTest} tests {@link SingleResourceEntryImpl}, with R = Integer.
 *
 * @author cycleke
 */
public class SingleResourceEntryImplIntegerTest extends SingleResourceEntryTest<Integer> {

  @Override
  public SingleResourceEntry<Integer> singleton() {
    return new SingleResourceEntryImpl<>();
  }

  @Override
  public Integer singleResource() {
    return Integer.MAX_VALUE;
  }

}
