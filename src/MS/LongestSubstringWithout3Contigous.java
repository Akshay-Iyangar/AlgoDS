package MS;


/**
 * Given a string s containing only a and b,
 * find longest substring of s such that s does not contain more than two contiguous occurrences of a and b.
 *
 *
 * Input: "aabbaaa aabb"
 * Output: "aabbaa"
 *
 *
 * Input: "aabbaabbaabbaa"
 * Output: "aabbaabbaabbaa"
 *
 *
 */
public class LongestSubstringWithout3Contigous {


  private static String longestSubstring(String s){


    int start=0;
    int end=1;
    int prev=0;
    int counter=0;
    int maxLen=1;
    int finalStart=0;
    int[] map = new int[2];



    while (end < s.length()){
      char c = s.charAt(end);
      char c1 = s.charAt(prev);
      end++;
      if(c == c1) {

        map[c-'a']++;
        if(map[c-'a'] > 2) counter++;

        while (counter > 0){

          if(end - start > maxLen){
            maxLen = end - start;
            finalStart=start;

          }
          map[0] = 0;
          map[1] = 0;
          counter=0;
          start++;

          System.out.println(finalStart);

        }
      }
      else {
        map[0]=0;
        map[1]=0;
      }
      prev = end;
    }
    return s.substring(finalStart, finalStart+maxLen);

  }

  public static void main(String[] args) {

    String str = "aabbaabbaabbaa";

    System.out.println(longestSubstring(str));

  }
}
