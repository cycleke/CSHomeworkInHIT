package base;

import java.util.Arrays;
import java.util.List;

/**
 * This class {@link PairLocationsEntryImpl} is a basic implication of {@link PairLocationsEntry}.
 *
 * @author cycleke
 */
public class PairLocationsEntryImpl implements PairLocationsEntry {
  private Location startLocation, targetLocation;

  // Abstraction function:
  // AF(i) = (startLocation, targetLocation)
  // Rep invariant:
  // The locations are not null.
  // Safety from rep exposure:
  // The field are all private.

  /**
   * Constructor of {@link PairLocationsEntryImpl}.
   *
   * @param startLocation
   *          the start location of the entry, not null
   * @param targetLocation
   *          the target location of the entry, not null
   */
  public PairLocationsEntryImpl(Location startLocation, Location targetLocation) {
    this.startLocation = startLocation;
    this.targetLocation = targetLocation;
    checkRep();
  }

  protected void checkRep() {
    assert startLocation != null;
    assert targetLocation != null;
  }

  @Override
  public Location getStartLocation() {
    return startLocation;
  }

  @Override
  public Location getTargetLocation() {
    return targetLocation;
  }

  @Override
  public List<Location> getLocations() {
    return Arrays.asList(startLocation, targetLocation);
  }

  @Override
  public void setStartLocation(Location startLocation) {
    this.startLocation = startLocation;
    checkRep();
  }

  @Override
  public void setTargetLocation(Location targetLocation) {
    this.targetLocation = targetLocation;
    checkRep();
  }

  @Override
  public void setLocations(List<Location> locations) {
    if (locations.size() != 2)
      throw new IllegalArgumentException("The size of locations must be 2.");
    startLocation = locations.get(0);
    targetLocation = locations.get(1);
    checkRep();
  }

}
