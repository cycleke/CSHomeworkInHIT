package debug;

import java.util.*;

/**
 * Note that this class may use the other two class: Flight and Plane.
 * <p>
 * Debug and fix errors. DON'T change the initial logic of the code.
 *
 * @author cycleke
 */
public class FlightClient {

  /**
   * Given a list of flights and a list of planes, suppose each flight has not yet
   * been allocated a plane to, this method tries to allocate a plane to each
   * flight and ensures that there're no any time conflicts between all the
   * allocations. For example: Flight 1 (2020-01-01 8:00-10:00) and Flight 2
   * (2020-01-01 9:50-10:40) are all allocated the same plane B0001, then there's
   * conflict because from 9:50 to 10:00 the plane B0001 cannot serve for the two
   * flights simultaneously.
   *
   * @param planes  a list of planes
   * @param flights a list of flights each of which has no plane allocated
   * @return false if there's no allocation solution that could avoid any
   * conflicts
   */

  public boolean planeAllocation(List<Plane> planes, List<Flight> flights) {
    boolean bFeasible = true;
    Random r = new Random();
    int size = planes.size();

    // 为 sort 添加 compare 函数
    flights.sort(Comparator.comparing(Flight::getArrivalTime));

    for (Flight f : flights) {
      boolean bAllocated = false;

      // 添加一个数组用于标记 plane 是否尝试过
      boolean[] mark = new boolean[size];
      int count = 0;
      Arrays.fill(mark, false);
      while (count < size) {
        int nextInt = r.nextInt(size);
        if (mark[nextInt]) {
          continue;
        } else {
          ++count;
          mark[nextInt] = true;
        }

        Plane p = planes.get(nextInt);
        Calendar fStart = f.getDepartTime();
        Calendar fEnd = f.getArrivalTime();
        boolean bConflict = false;

        for (Flight t : flights) {
          Plane q = t.getPlane();
          // q 可能为 null
          if (q == null || !q.equals(p)) {
            continue;
          }

          Calendar tStart = t.getDepartTime();
          Calendar tEnd = t.getArrivalTime();

          // 使用 compareTo 而不是符号，同时添加等于的情况
          if ((fStart.equals(tStart) && fEnd.equals(tEnd))
            || (fStart.compareTo(tStart) > 0 && fStart.compareTo(tEnd) < 0)
            || (tStart.compareTo(fStart) > 0 && tStart.compareTo(fEnd) < 0)) {
            bConflict = true;
            break;
          }
        }

        if (!bConflict) {
          f.setPlane(p);
          bAllocated = true;
          break;
        }
      }

      // 所有尝试均失败，应当终止尝试
      if (!bAllocated) {
        bFeasible = false;
        break;
      }
    }
    return bFeasible;
  }
}
