package base;

/**
 * This class {@link UnfinishedEntryMayReachThisLocationException} will be thrown when the client delete a resource that
 * a entry may reach the location in the future.
 *
 * @author cycleke
 */
public class UnfinishedEntryMayReachThisLocationException extends Exception {
  private static final long serialVersionUID = 3028604082661920284L;

  public UnfinishedEntryMayReachThisLocationException(PlanningEntry<?> entry, Location location) {
    super(entry + " may reach the location: " + location + " in the future.");
  }
}
