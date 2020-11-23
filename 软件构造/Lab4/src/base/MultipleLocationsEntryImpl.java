package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class {@link MultipleLocationsEntryImpl} implies the basic functions of {@link MultipleLocationsEntry}.
 *
 * @author cycleke
 */
public class MultipleLocationsEntryImpl implements MultipleLocationsEntry {
  private List<Location> locations;

  // Abstraction function:
  // AF(i) = {locations}
  // Rep invariant:
  // The locations are not null.
  // Safety from rep exposure:
  // The field are all private, and always returns and sets copies.

  public MultipleLocationsEntryImpl() {
    locations = Collections.emptyList();
    checkRep();
  }

  @Override
  public List<Location> getLocations() {
    return new ArrayList<>(locations);
  }

  @Override
  public void setLocations(List<Location> locations) {
    assert locations != null;
    this.locations = new ArrayList<>(locations);
    checkRep();
  }

  protected void checkRep() {
    for (Location location : locations) {
      assert location != null;
    }
  }

}
