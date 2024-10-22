package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value.
 * Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible.
 * Return a list of ids of selected elements. If no pair is possible, return an empty list.
 *
 * Example 1:
 *
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 *
 * Output: [[2, 1]]
 *
 * Explanation:
 * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
 * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 * Example 2:
 *
 * Input:
 * a = [[1, 3], [2, 5], [3, 7], [4, 10]]
 * b = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * target = 10
 *
 * Output: [[2, 4], [3, 2]]
 *
 * Explanation:
 * There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
 * Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
 * These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
 * Example 3:
 *
 * Input:
 * a = [[1, 8], [2, 7], [3, 14]]
 * b = [[1, 5], [2, 10], [3, 14]]
 * target = 20
 *
 * Output: [[3, 1]]
 * Example 4:
 *
 * Input:
 * a = [[1, 8], [2, 15], [3, 9]]
 * b = [[1, 8], [2, 11], [3, 12]]
 * target = 20
 *
 * Output: [[1, 3], [3, 2]]
 */

public class OptimizationUtilization {

    private static List<List<Integer>> findPairdClosestToTarget(int[][] a, int[][] b, int target){

        Arrays.sort(a, (e1,e2) -> e1[1] - e2[1]);
        Arrays.sort(b, (e1,e2) -> e1[1] - e2[1]);
        int max = Integer.MIN_VALUE;
        int k=0;

        List<List<Integer>> result = new ArrayList<>();

        int i=0;
        int j=b.length-1;

        while (i<a.length && j>=0){
            int sum = a[i][1] + b[j][1];
            if(sum > target) j--;
            else {
                if (max<sum){
                    result.clear();
                    max=sum;
                }
                List<Integer> list = new ArrayList<>();
                list.add(a[i][0]);list.add(b[j][0]);
                result.add(list);
                while(i+1<a.length && j-1>=0 && sum == a[i+1][1]+b[j-1][1]) {
                    List<Integer> list1 = new ArrayList<>();
                    i++;j--;
                    list1.add(a[i][0]);list1.add(b[j][0]);
                    result.add(list1);
                }
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

//       int[][] a = {{1, 2},{2, 4}, {3, 6}};
//       int[][] b = {{1, 2}};
//       int target = 7;

//        int[][] a = {{1, 3}, {2, 5}, {3, 7}, {4, 10}};
//        int[][] b = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
//        int target = 10;


//        int[][] a = {{1, 8},{2, 15}, {3, 9}};
//        int[][] b = {{1, 8}, {2,11}, {3,12}};
//        int target = 20;

        int[][] a = {{1, 8},{2, 7}, {3, 14}};
        int[][] b = {{1, 5}, {2,10}, {3,14}};
        int target = 20;


        List<List<Integer>> result = findPairdClosestToTarget(a, b, target);

        System.out.println(result);
    }
}
