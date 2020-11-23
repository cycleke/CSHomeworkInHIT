package base;

import org.junit.Test;

/**
 * This class {@link PairLocationsEntryUnmodifiableDecoratorTest} tests {@link PairLocationsEntryUnmodifiableDecorator}.
 *
 * @author cycleke
 */
public class PairLocationsEntryUnmodifiableDecoratorTest extends PairLocationsEntryTest {

  @Override
  public boolean locationShareabel() {
    return false;
  }

  @Override
  public PairLocationsEntry singleton() {
    return new PairLocationsEntryUnmodifiableDecorator(
      new PairLocationsEntryImpl(CommonLocation.of("Arctic", false), CommonLocation.of("Antarctic", false)));
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetStartLocation() {
    super.testSetStartLocation();
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetTargetLocation() {
    super.testSetTargetLocation();
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLocations() {
    super.testSetLocations();
  }

}
