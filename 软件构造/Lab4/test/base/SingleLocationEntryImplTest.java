package base;

/**
 * This class {@link SingleLocationEntryImplTest} tests {@link SingleLocationEntryTest}.
 *
 * @author cycleke
 */
public class SingleLocationEntryImplTest extends SingleLocationEntryTest {

  @Override
  public boolean locationShareable() {
    return false;
  }

  @Override
  public SingleLocationEntry singleton() {
    return new SingleLocationEntryImpl(CommonLocation.of("", false));
  }

}
