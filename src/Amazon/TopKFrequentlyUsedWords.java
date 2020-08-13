package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to
 * least frequently mentioned.
 *
 * The comparison of strings is case-insensitive.
 * Multiple occurances of a keyword in a review should be considred as a single mention.
 * If keywords are mentioned an equal number of times in reviews, sort alphabetically.
 *
 * Example 1:
 *
 * Input:
 * k = 2
 * keywords = ["anacell", "cetracular", "betacellular"]
 * reviews = [
 *   "Anacell provides the best services in the city",
 *   "betacellular has awesome services",
 *   "Best services provided by anacell, everyone should use anacell",
 * ]
 *
 * Output:
 * ["anacell", "betacellular"]
 *
 * Explanation:
 * "anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.
 * Example 2:
 *
 * Input:
 * k = 2
 * keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
 * reviews = [
 *   "I love anacell Best services; Best services provided by anacell",
 *   "betacellular has great services",
 *   "deltacellular provides much better services than betacellular",
 *   "cetracular is worse than anacell",
 *   "Betacellular is better than deltacellular.",
 * ]
 *
 * Output:
 * ["betacellular", "anacell"]
 *
 * Explanation:
 * "betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but
 * "anacell" is lexicographically smaller.
 */
public class TopKFrequentlyUsedWords {
  public static void main(String[] args) {


    int k=2;
    String[] keywords = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
    final List<String> strings = Arrays.asList(keywords);

    String[] reviews = {"I love 123 anacell Best services; Best services provided by anacell",
        "betacellular has great services",
        "deltacellular provides much better services than betacellular",
        "cetracular is worse than anacell",
        "Betacellular is better than deltacellular."};


    Map<String,Integer> map = new HashMap<>();

    for (int i = 0; i < reviews.length; i++) {
      final String[] tokens = reviews[i].toLowerCase().split("[^A-Z,a-z]+");
      System.out.println(Arrays.toString(tokens));
      for (String token : tokens) {
        if (strings.contains(token))
          map.put(token, map.getOrDefault(token,0)+1);
      }

    }

    final List<String> keys = new ArrayList<>(map.keySet());
    Collections.sort(keys, (e2, e1) -> (map.get(e1)).equals(map.get(e2)) ? e1.compareTo(e2) : map.get(e2) - map.get(e2));
    final List<String> result = keys.subList(0, k);
    System.out.println(result);
  }

}
