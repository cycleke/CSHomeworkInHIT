package base;

/**
 * This class {@link LocationConflictException} is used to describe the exception that two entries share an unsharable
 * location.
 *
 * @author cycleke
 */
public class LocationConflictException extends Exception {
  private static final long serialVersionUID = -8773252963313105784L;

  /**
   * Constructor of {@link LocationConflictException}.
   *
   * @param entry        the first planning entry
   * @param anotherEntry the second planning entry
   * @param location     the location where the conflict happens
   */
  public LocationConflictException(PlanningEntry<?> entry, PlanningEntry<?> anotherEntry, Location location) {
    super(entry + " may conflict with " + anotherEntry + " on location " + location);
  }
}
