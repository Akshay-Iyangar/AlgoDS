package MS;


/**
 *
 * Semi alternating substirng is a string that doesn't contain three
 * consecutive characters. like `aaa` or `bbb`
 * Find the longest substring substtring that doesn't follow this
 * pattern.
 *
 * a = "baaabbabbb" expected result is 7.
 *
 *
 *
 *
 */

public class LongestSemiALternatingSubstring {



  public static void main(String[] args) {

    String str = "baaabbabbb";
    System.out.println(longestMatching(str));

  }

  private static int longestMatching(String str) {

    int[] map = new int[26];
    int counter=0;
    int start=0;
    int end=0;
    int max = Integer.MIN_VALUE;

    while(end<str.length()){

      char c = str.charAt(end);
      map[c-'a']++;
      if(map[c-'a'] > 3) counter++;
      end++;
      while(counter>0){
        //make the counter valid again
        char c1 = str.charAt(start);
        map[c1-'a']--;
        if(map[c-'a']==0) counter--;
        start++;
      }
      max = Math.max(max, end-start+1);
    }
    return max;
  }

}
