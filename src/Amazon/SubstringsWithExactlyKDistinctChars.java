package Amazon;


/**
 * Given a string s and an int k, return an int representing the number of substrings (not unique) of s with exactly k distinct characters.
 * If the given string doesn't have k distinct characters, return 0.
 * https://leetcode.com/problems/subarrays-with-k-different-integers
 *
 *
 *
 *
 * Example 1:
 *
 * Input: s = "pqpqs", k = 2
 * Output: 7
 * Explanation: ["pq", "pqp", "pqpq", "qp", "qpq", "pq", "qs"]
 * Example 2:
 *
 * Input: s = "aabab", k = 3
 * Output: 0
 * Constraints:
 *
 * The input string consists of only lowercase English letters [a-z]
 * 0 ≤ k ≤ 26
 *
 *
 */

public class SubstringsWithExactlyKDistinctChars {


    private int exactlyKDistinctCharacters(String s, int K){
        int X = atmostKDistinctCharacters(s,K);
        if(X==0) return 0;
        return X - atmostKDistinctCharacters(s,K-1);
    }

    private int atmostKDistinctCharacters(String s, int K){

        int end=0;
        int start=0;
        int counter=0;
        int count=0;
        int[] map = new int[26];
        int N = s.length();

        while (end < N){
            char c = s.charAt(end);
            map[c-'a']++;
            if(map[c-'a']==1) counter++;
            end++;
            while (counter>K){
                char c1 = s.charAt(start);
                map[c1-'a']--;
                if(map[c1-'a']==0) counter--;
                start++;
            }
            count += end - start;
        }
        //System.out.println(count);
        return (start==0)?0:count;
    }

    public static void main(String[] args) {
        String s = "aabab";
        int k=3;

        SubstringsWithExactlyKDistinctChars substringsWithExactlyKDistinctChars = new SubstringsWithExactlyKDistinctChars();
        int result = substringsWithExactlyKDistinctChars.exactlyKDistinctCharacters(s, 2);
        System.out.println(result);
    }
}
