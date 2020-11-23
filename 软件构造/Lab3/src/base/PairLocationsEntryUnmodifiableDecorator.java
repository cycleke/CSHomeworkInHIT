package base;

import java.util.List;

/**
 * This class {@link PairLocationsEntryUnmodifiableDecorator} is a decorator for {@link PairLocationsEntry}. This will
 * make the locations of the entry immutable.
 *
 * @author cycleke
 */
public class PairLocationsEntryUnmodifiableDecorator implements PairLocationsEntry {
  private final PairLocationsEntry entry;

  // Abstraction function:
  // AF(d) = entry decorated
  // Rep invariant:
  // Field entry is not null.
  // Safety from rep exposure:
  // the field are all private and final.

  /**
   * Constructor of {@link PairLocationsEntryUnmodifiableDecorator}.
   *
   * @param entry
   *          the entry to decorate
   */
  public PairLocationsEntryUnmodifiableDecorator(PairLocationsEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public Location getStartLocation() {
    return entry.getStartLocation();
  }

  @Override
  public Location getTargetLocation() {
    return entry.getTargetLocation();
  }

  @Override
  public List<Location> getLocations() {
    return entry.getLocations();
  }

  @Override
  public void setStartLocation(Location startLocation) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTargetLocation(Location targetLocation) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLocations(List<Location> locations) {
    throw new UnsupportedOperationException();
  }

}
