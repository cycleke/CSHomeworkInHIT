package base;

/**
 * This class {@link SingleLocationEntryUnmodifiableDecorator} is a decorator for {@link SingleLocationEntry}. This
 * will make the location of the entry immutable.
 *
 * @author cycleke
 */
public class SingleLocationEntryUnmodifiableDecorator implements SingleLocationEntry {
  private final SingleLocationEntry entry;

  // Abstraction function:
  // AF(d) = entry decorated
  // Rep invariant:
  // Field entry is not null.
  // Safety from rep exposure:
  // the field are all private and final.

  /**
   * Constructor of {@link SingleLocationEntry}.
   *
   * @param entry
   *          the entry to decorate
   */
  public SingleLocationEntryUnmodifiableDecorator(SingleLocationEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public Location getLocation() {
    return entry.getLocation();
  }

  @Override
  public void setLocation(Location location) {
    throw new UnsupportedOperationException();
  }
}
