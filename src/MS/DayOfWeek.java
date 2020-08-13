package MS;


import java.util.Arrays;
import java.util.List;

/**
 * Return the day from the current day based on integer.
 */

public class DayOfWeek {


  private String dayAfterK(String current, int K){

    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    //Step 1 : make the current day as the pivot.
    int pivot=0;
    for(int i=0; i<days.length;i++){
      if(days[i].equals(current)) pivot = i;
    }

    // Step 2 : Modulo by 7.
    int addDays = K % 7;

    return days[(pivot + addDays)%7];
  }

  public static void main(String[] args) {

    DayOfWeek dayOfWeek = new DayOfWeek();
    System.out.println(dayOfWeek.dayAfterK("Wed", 2));

  }
}
