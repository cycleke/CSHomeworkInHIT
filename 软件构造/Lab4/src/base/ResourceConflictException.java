package base;

/**
 * This class {@link ResourceConflictException} is used to describe the exception that two entries share an unsharable
 * resource.
 *
 * @author cycleke
 */
public class ResourceConflictException extends Exception {
  private static final long serialVersionUID = -181118733994067439L;

  /**
   * Constructor of {@link ResourceConflictException}.
   *
   * @param entry        the first planning entry
   * @param anotherEntry the second planning entry
   * @param resource     the resource where the conflict happens
   */
  public ResourceConflictException(PlanningEntry<?> entry, PlanningEntry<?> anotherEntry, Object resource) {
    super(entry + " may conflict with " + anotherEntry + " on resource " + resource);
  }
}
