package MS;


/*

  In this example we have airplane seats are told to
  allocate a family of 4 to the seats

  the seat has to be contiguous
    1. All four seats can be in the middle
    2. Two on the aisle and two in the center.

    Total 10 seats are in a row

    So it is pretty clear from this that we can
    ABC DEFG HJK

    so we can have
    BCDE
    DEFG
    FGHJ

    B and E
    D and G

    these are the only possibility for each row.
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlaneSeatReservation {


  int result=0;

  private int totalFamilyOfFourPossible(String reservation, int N){

    String[] seats = reservation.split("\\s+");
    Map<Integer,Set<Character>> map = new HashMap<>();

    for (int i = 1; i <= N; i++) {
      map.put(i, new HashSet<>());
    }


    for (String seat : seats) {
      map.get(Integer.parseInt(seat.substring(0,seat.length()-1))).add(seat.charAt(seat.length()-1));
    }

    map.forEach((k,v) -> {

      if(!v.contains('B') && !v.contains('C') && !v.contains('D') && !v.contains('E')){
        result++;
        v.addAll(Arrays.asList('B','C','D','E'));
      }
      if(!v.contains('D') && !v.contains('E') && !v.contains('F') && !v.contains('G')){
        result++;
        v.addAll(Arrays.asList('D','E','F','G'));
      }
      if(!v.contains('F') && !v.contains('G') && !v.contains('H') && !v.contains('J')){
        result++;
        v.addAll(Arrays.asList('F','G','H','J'));
      }
    });
    return result;
  }


  public static void main(String[] args) {

    String reservation = "1A 2F 1C";
    PlaneSeatReservation planeSeatReservation = new PlaneSeatReservation();
    int r = planeSeatReservation.totalFamilyOfFourPossible(reservation,2);

    System.out.println(r);

  }


}
