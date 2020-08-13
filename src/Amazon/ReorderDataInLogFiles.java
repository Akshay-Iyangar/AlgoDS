package Amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 937. Reorder Data in Log Files
 *
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 */

public class ReorderDataInLogFiles {

    public static String[] reorderLogFiles(String[] logs) {
        final List<String> logList = Arrays.asList(logs);
        Collections.sort(logList, (l1, l2) -> {
            String[] split1 = l1.split(" ", 2);
            String[] split2 = l2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

            if(!isDigit1 && !isDigit2){
                //it is a alphabet string.
                int diff = split1[1].compareTo(split2[1]);
                if(diff!=0) return diff;
                return split1[0].compareTo(split2[0]);
            }

            // 1 is a log 2 is digit (-1) , 1 is digit 2 is log (1), both are digit (0).
            if (isDigit1){
                if(isDigit2) return 0;
                return 1;
            }
            //meaning the first one is log and the second is a digit so log should be less than digit which is the
            //case so return -1;
            return -1;

        });
        System.out.println(logList);
        return logs;
    }

    public static void main(String[] args) {
        //String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] logs = {"mi2 jog mid pet", "wz3 34 54 398", "aw1 jog mid pet", "x4 45 21 1"};
        String[] result = reorderLogFiles(logs);
        System.out.println(Arrays.toString(result));
    }
}
