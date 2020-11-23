package train;

import static java.util.Objects.hash;

/**
 * This class {@link TrainCabin} is an immutable class to describe a train cabin.
 *
 * @author cycleke
 */
public class TrainCabin {

  private final int id;
  private final TrainCabinType type;
  private final int seats;
  private final int factoryYear;

  /**
   * Constructor of {@link TrainCabin}.
   *
   * @param id          the id of the train cabin, positive
   * @param type        the type of the train cabin, not null
   * @param seats       the seats of the train cabin, positive
   * @param factoryYear the factory year of the train cabin
   */
  public TrainCabin(int id, TrainCabinType type, int seats, int factoryYear) {
    this.id = id;
    this.type = type;
    this.seats = seats;
    this.factoryYear = factoryYear;
    checkRep();
  }

  // private final
  // Abstraction function:
  // AF(r) = {id, type, seats, factory year}
  // Rep invariant:
  // The field are not null, id and seats are positive.
  // Safety from rep exposure:
  // The field are all private and final.

  protected void checkRep() {
    assert id > 0;
    assert type != null;
    assert seats > 0;
  }

  public int getFactoryYear() {
    return factoryYear;
  }

  public int getSeats() {
    return seats;
  }

  public TrainCabinType getType() {
    return type;
  }

  public int getId() {
    return id;
  }

  @Override
  public int hashCode() {
    return hash(id, type, seats, factoryYear);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TrainCabin other = (TrainCabin) obj;
    return id == other.id && type == other.type && seats == other.seats && factoryYear == other.factoryYear;
  }

  @Override
  public String toString() {
    return "TrainCabin [id=" + id + ", type=" + type + ", seats=" + seats + ", factory year=" + factoryYear + "]";
  }

  public enum TrainCabinType {
    BUSINESS, FIRST_CLASS, SECOND_CLASS, SOFT_SLEEPER, HARD_SLEEPER, HARD_SEAT, LUGGAGE_CAR, DINING_CAR
  }

}
