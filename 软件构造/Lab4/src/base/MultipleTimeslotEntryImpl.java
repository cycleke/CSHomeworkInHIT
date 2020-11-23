package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class {@link MultipleTimeslotEntryImpl} implies the basic functions of {@link MultipleTimeslotEntry}.
 *
 * @author cycleke
 */
public class MultipleTimeslotEntryImpl implements MultipleTimeslotEntry {
  private List<Timeslot> timeslots;

  // Abstraction function:
  // AF(i) = {timeslots}
  // Rep invariant:
  // The resources are not null.
  // Safety from rep exposure:
  // The field are all private, and always returns and sets copies.

  public MultipleTimeslotEntryImpl() {
    timeslots = Collections.emptyList();
    checkRep();
  }

  protected void checkRep() {
    for (Timeslot timeslot : timeslots) {
      assert timeslot != null;
    }
  }

  @Override
  public List<Timeslot> getTimeslots() {
    return new ArrayList<>(timeslots);
  }

  @Override
  public void setTimeslots(List<Timeslot> timeslots) {
    assert timeslots != null;
    this.timeslots = new ArrayList<>(timeslots);
    checkRep();
  }

}
