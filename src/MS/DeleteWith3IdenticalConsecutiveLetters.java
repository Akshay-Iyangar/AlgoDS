package MS;


/***
 * Given a string we are interested in only deleting those characters which has
 * three identical consecutive letters. by deleting the minimum letters.
 *
 * S = "eedaaad" -> eedaad
 * S = "xxxtxxx" -> xxtxx
 */

public class DeleteWith3IdenticalConsecutiveLetters {
  public static void main(String[] args) {

    System.out.println(deleteMinCharacters("xxxtxxx"));
  }


  private static String deleteMinCharacters(String s) {

    StringBuilder sb = new StringBuilder(s);

    int i=1;
    while (i < sb.length()-1) {
      if(sb.charAt(i)==sb.charAt(i-1) && sb.charAt(i)==sb.charAt(i+1)) sb.deleteCharAt(i);
      else i++;
    }
    return sb.toString();
  }

}
