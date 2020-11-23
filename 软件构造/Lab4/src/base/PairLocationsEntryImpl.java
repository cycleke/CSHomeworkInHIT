package base;

import java.util.Arrays;
import java.util.List;

/**
 * This class {@link PairLocationsEntryImpl} is a basic implication of {@link PairLocationsEntry}.
 *
 * @author cycleke
 */
public class PairLocationsEntryImpl implements PairLocationsEntry {
  private final static int LOCATIONS_SIZE = 2;

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
   * @param startLocation  the start location of the entry, not null
   * @param targetLocation the target location of the entry, not null
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
  public void setStartLocation(Location startLocation) {
    this.startLocation = startLocation;
    checkRep();
  }

  @Override
  public Location getTargetLocation() {
    return targetLocation;
  }

  @Override
  public void setTargetLocation(Location targetLocation) {
    this.targetLocation = targetLocation;
    checkRep();
  }

  @Override
  public List<Location> getLocations() {
    List<Location> list = Arrays.asList(startLocation, targetLocation);
    assert list.size() == 2;
    return list;
  }

  @Override
  public void setLocations(List<Location> locations) {
    assert locations.size() == LOCATIONS_SIZE;
    startLocation = locations.get(0);
    targetLocation = locations.get(1);
    checkRep();
  }

}
