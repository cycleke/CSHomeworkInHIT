package base;

/**
 * This class {@link PairLocationsEntryImplTest} tests {@link PairLocationsEntryImpl}.
 *
 * @author cycleke
 */
public class PairLocationsEntryImplTest extends PairLocationsEntryTest {

  @Override
  public boolean locationShareable() {
    return false;
  }


  @Override
  public PairLocationsEntry singleton() {
    return new PairLocationsEntryImpl(CommonLocation.of("Arctic", false), CommonLocation.of("Antarctic", false));
  }

}
