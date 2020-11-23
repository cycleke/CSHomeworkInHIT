package base;

import org.junit.Test;

/**
 * This class {@link SingleLocationEntryUnmodifiableDecoratorTest} tests
 * {@link SingleLocationEntryUnmodifiableDecorator}.
 *
 * @author cycleke
 */
public class SingleLocationEntryUnmodifiableDecoratorTest extends SingleLocationEntryTest {

  @Override
  public boolean locationShareable() {
    return false;
  }

  @Override
  public SingleLocationEntry singleton() {
    return new SingleLocationEntryUnmodifiableDecorator(new SingleLocationEntryImpl(CommonLocation.of("", false)));
  }

  @Override
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLocation() {
    super.testSetLocation();
  }

}
