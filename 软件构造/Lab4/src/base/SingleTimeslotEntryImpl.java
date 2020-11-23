package base;

/**
 * This class {@link SingleTimeslotEntryImpl} implies the basic functions of {@link SingleTimeslotEntry}.
 *
 * @author cycleke
 */
public class SingleTimeslotEntryImpl implements SingleTimeslotEntry {
  private Timeslot timeslot;

  // Abstraction function:
  // AF(i) = timeslot
  // Rep invariant:
  // Field timeslot is not null
  // Safety from rep exposure:
  // the field are all private.

  public SingleTimeslotEntryImpl(Timeslot timeslot) {
    this.timeslot = timeslot;
    checkRep();
  }

  protected void checkRep() {
    assert timeslot != null;
  }

  @Override
  public Timeslot getTimeslot() {
    return timeslot;
  }

  @Override
  public void setTimeslot(Timeslot timeslot) {
    this.timeslot = timeslot;
    checkRep();
  }

}
