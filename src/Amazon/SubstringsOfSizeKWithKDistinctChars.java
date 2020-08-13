package Amazon;


import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "abcabc", k = 3
 * Output: ["abc", "bca", "cab"]
 * Example 2:
 *
 * Input: s = "abacab", k = 3
 * Output: ["bac", "cab"]
 * Example 3:
 *
 * Input: s = "awaglknagawunagwkwagl", k = 4
 * Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
 * Explanation:
 * Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk",
 * "kwag", "wagl"
 * "wagl" is repeated twice, but is included in the output once.
 * Constraints:
 *
 * The input string consists of only lowercase English letters [a-z]
 * 0 ≤ k ≤ 26
 */
public class SubstringsOfSizeKWithKDistinctChars {

  private static Set<String> substrings(String s, int K){

    int map[] = new int[26];
    int end=0;
    int start=0;
    int counter=0;
    Set<String> result = new HashSet<>();

    while(end < s.length()){
      char c = s.charAt(end);
      map[c-'a']++;
      if(map[c-'a']==1) counter++;
      end++;
      //this may not nessciarily have unique characters.
      while (counter==K){
        if(end - start == K) result.add(s.substring(start,end));
        char c1 = s.charAt(start);
        map[c1-'a']--;
        if(map[c1-'a']==0)counter--;
        start++;
        //System.out.println("start " + start + " end " + end);
      }
    }
    return result;

  }


  public static void main(String[] args) {

    Set<String> list = substrings("awaglknagawunagwkwagl", 4);
    System.out.println(list);

  }
}
