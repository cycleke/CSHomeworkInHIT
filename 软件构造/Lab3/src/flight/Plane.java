package flight;

/**
 * This class {@link Plane} is an immutable class to describe a plane with ID number.
 *
 * @author cycleke
 */
public class Plane {
  private final String id;
  private final String type;
  private final int seats;
  private final double age;

  // Abstraction function:
  // AF(p) = the id number of the plane
  // Rep invariant:
  // id and type are not null, and seats > 0, age >= 0
  // Safety from rep exposure:
  // the field are all private and final. There are only getters, no setters.

  /**
   * Constructor of {@link Plane}.
   *
   * @param id
   *          the ID number of the plane, not null
   * @param type
   *          the type of the plane, not null
   * @param seats
   *          the number of seats of the plane, must be positive
   * @param age
   *          the age of the plane, nonnegative
   */
  public Plane(String id, String type, int seats, double age) {
    this.id = id;
    this.type = type;
    this.seats = seats;
    this.age = age;
    checkRep();
  }

  protected void checkRep() {
    assert id != null;
    assert type != null;
    assert seats > 0;
    assert age >= 0;
  }

  /**
   * Return the ID of the plane.
   *
   * @return the ID of the plane
   */
  public String getId() {
    return id;
  }

  /**
   * Return the age of the plane
   *
   * @return the age of the plane
   */
  public double getAge() {
    return age;
  }

  /**
   * Return the type of the plane
   *
   * @return the type of the plane
   */
  public String getType() {
    return type;
  }

  /**
   * Return the number of seats of the plane
   *
   * @return the number of seats of the plane
   */
  public int getSeats() {
    return seats;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id.hashCode();
    result = prime * result + type.hashCode();
    result = prime * result + Integer.hashCode(seats);
    result = prime * result + Double.hashCode(age);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Plane other = (Plane)obj;
    if (seats != other.seats)
      return false;
    if (age != other.age)
      return false;
    if (!id.equals(other.id))
      return false;
    if (!type.equals(other.type))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return id + " [type=" + type + " ,seats=" + seats + " ,age=" + age + "]";
  }

}
