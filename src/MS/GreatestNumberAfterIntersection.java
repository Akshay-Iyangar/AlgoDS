package MS;


/**
 * Insert '5' in the array
 * N=268 Result=5268
 * N=670 result=6750
 * N=-999 result=-5999
 * N=0 result=50
 */
public class GreatestNumberAfterIntersection {

  public static void main(String[] args) {
    System.out.println(getGreatestNumber(-999));
  }

  private static int getGreatestNumber(int number){


    StringBuilder str = new StringBuilder(String.valueOf(Math.abs(number)));

    //determine if positive or negative
    int flag = (number >= 0) ? 1 : -1;

    int i=0;
    if (flag==1){
        //number is positive
      while (i<str.length()){
        if(str.charAt(i)-'0' < 5) break;
        i++;
      }


    } else {
      while (i<str.length()){
        if(str.charAt(i)-'0' > 5) break;
        i++;
      }
    }
    str.insert(i,5);
    int val = Integer.parseInt(str.toString());
    return val * flag;
  }

}
