package base;

/**
 * This class {@link IllegalStateTransitionException} is an exception when the client want to transition from a state to
 * another state which is unreachable.
 *
 * @author cycleke
 */
public class IllegalStateTransitionException extends Exception {
  private static final long serialVersionUID = -6120603711111154743L;

  public IllegalStateTransitionException() {
    super();
  }

  public IllegalStateTransitionException(String message) {
    super(message);
  }

}
