package base;

import java.util.List;

/**
 * This class {@link MultipleLocationsEntryUnmodifiableDecorator} is a decorator for {@link MultipleLocationEntry}. This
 * will make the locations of the entry immutable.
 *
 * @author cycleke
 */
public class MultipleLocationsEntryUnmodifiableDecorator implements MultipleLocationsEntry {
  private final MultipleLocationsEntry entry;

  // Abstraction function:
  // AF(d) = entry decorated
  // Rep invariant:
  // Field entry is not null.
  // Safety from rep exposure:
  // the field are all private and final.

  /**
   * Constructor of {@link MultipleLocationsEntryUnmodifiableDecorator}.
   *
   * @param entry
   *          the entry to decorate
   */
  public MultipleLocationsEntryUnmodifiableDecorator(MultipleLocationsEntry entry) {
    this.entry = entry;
  }

  @Override
  public List<Location> getLocations() {
    return entry.getLocations();
  }

  @Override
  public void setLocations(List<Location> locations) {
    throw new UnsupportedOperationException();
  }

}
