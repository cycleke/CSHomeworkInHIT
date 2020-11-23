package base;

/**
 * This class {@link MultipleTimeslotEntryImplTest} tests {@link MultipleTimeslotEntryImpl}.
 *
 * @author cycleke
 */
public class MultipleTimeslotEntryImplTest extends MultipleTimeslotEntryTest {

  @Override
  public MultipleTimeslotEntry singleton() {
    return new MultipleTimeslotEntryImpl();
  }

}
