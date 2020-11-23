package base;

/**
 * This class {@link SingleResourceEntryImplStringTest} tests {@link SingleResourceEntryImpl}, with R = String.
 *
 * @author cycleke
 */
public class SingleResourceEntryImplStringTest extends SingleResourceEntryTest<String> {

  @Override
  public SingleResourceEntry<String> singleton() {
    return new SingleResourceEntryImpl<>();
  }

  @Override
  public String singleResource() {
    return "Free software means the users have the freedom to run, copy, distribute, study, change and improve the software.";
  }

}
