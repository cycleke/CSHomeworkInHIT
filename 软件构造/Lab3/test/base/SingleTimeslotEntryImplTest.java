package base;

/**
 * This class {@link SingleTimeslotEntryImplTest} tests {@link SingleTimeslotEntryImpl}.
 *
 * @author cycleke
 */
public class SingleTimeslotEntryImplTest extends SingleTimeslotEntryTest {

  @Override
  public SingleTimeslotEntry singleton() {
    return new SingleTimeslotEntryImpl(Timeslot.parse("2020-05-04 00:00", "2020-05-05 00:00"));
  }

}
