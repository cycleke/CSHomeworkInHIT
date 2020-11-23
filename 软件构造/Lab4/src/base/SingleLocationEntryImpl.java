package base;

/**
 * This class {@link SingleLocationEntryImpl} implies the basic functions of {@link SingleLocationEntry}.
 *
 * @author cycleke
 */
public class SingleLocationEntryImpl implements SingleLocationEntry {
  private Location location;

  // Abstraction function:
  // AF(i) = location
  // Rep invariant:
  // Field location is not null.
  // Safety from rep exposure:
  // The field are all private, and always returns and sets copies.

  public SingleLocationEntryImpl(Location location) {
    this.location = location;
    checkRep();
  }

  protected void checkRep() {
    assert location != null;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(Location location) {
    this.location = location;
    checkRep();
  }

}
