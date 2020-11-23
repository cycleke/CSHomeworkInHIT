package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class {@link MultipleSortedResourceEntryImpl} implies the basic functions of
 * {@link MultipleSortedResourceEntry}.
 *
 * @param <R> the type of resource
 * @author cycleke
 */
public class MultipleSortedResourceEntryImpl<R> implements MultipleSortedResourceEntry<R> {
  private List<R> resources;

  // Abstraction function:
  // AF(i) = {resources}
  // Rep invariant:
  // The resources are not null.
  // Safety from rep exposure:
  // The field are all private, and always returns and sets copies.

  /**
   * Constructor of {@link MultipleSortedResourceEntryImpl}.
   */
  public MultipleSortedResourceEntryImpl() {
    resources = Collections.emptyList();
    checkRep();
  }

  @Override
  public List<R> getResources() {
    return new ArrayList<>(resources);
  }

  @Override
  public void setResources(List<R> resources) {
    assert resources != null;
    assert resources.parallelStream().allMatch(x -> x != null);
    this.resources = new ArrayList<>(resources);
    checkRep();
  }

  protected void checkRep() {
    for (R resource : resources) {
      assert resource != null;
    }
  }

}
