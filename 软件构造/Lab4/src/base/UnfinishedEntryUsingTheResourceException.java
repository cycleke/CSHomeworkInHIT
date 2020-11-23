package base;

/**
 * This class {@link UnfinishedEntryUsingTheResourceException} will be thrown when the client delete a resource that a entry is
 * using it.
 *
 * @author cycleke
 */
public class UnfinishedEntryUsingTheResourceException extends Exception {
  private static final long serialVersionUID = 153016443719293488L;

  /**
   * Constructor of {@link UnfinishedEntryUsingTheResourceException}.
   *
   * @param entry    the entry using the resource
   * @param resource the resource being used
   */
  public UnfinishedEntryUsingTheResourceException(PlanningEntry<?> entry, Object resource) {
    super(entry + " is using the resource: " + resource);
  }
}
