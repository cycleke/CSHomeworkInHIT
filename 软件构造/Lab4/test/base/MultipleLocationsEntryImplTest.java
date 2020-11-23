package base;

/**
 * This class {@link MultipleLocationsEntryImplTest} tests {@link MultipleLocationsEntryImpl}.
 *
 * @author cycleke
 */
public class MultipleLocationsEntryImplTest extends MultipleLocationsEntryTest {

  @Override
  public MultipleLocationsEntry singleton() {
    return new MultipleLocationsEntryImpl();
  }
}
