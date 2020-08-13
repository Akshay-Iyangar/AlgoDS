package MS;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given you one sorted array, please put them into n buckets, we need to ensure we get n sub array with approximately
 * equal weights.
 * Example;
 * input {1, 2, 3, 4, 5} n = 3
 * output [[[5],[1,4],[2,3]];
 */

public class PartitionToNSubsets {


  /**
   * This approach won't work.
   * @param nums
   * @param n
   * @return
   */
  private List<List<Integer>> partition(int[] nums, int n) {

    int[] sums = new int[n];

    PriorityQueue<Integer> pq = new PriorityQueue<>((e1,e2) -> sums[e1]-sums[e2]);
    List<List<Integer>> result = new ArrayList<>();

    /**
     * So at this point we have three buckets
     *
     * sums[0]
     * sums[1]
     * sums[2]
     *
     * and the order of the bucket is ascending order
     */

    for (int i = 0; i < n; i++) {
      result.add(new ArrayList<>());
      pq.add(i);
    }

    for (int i = nums.length - 1; i >= 0; i--) {

      int index = pq.poll();
      result.get(index).add(nums[i]);
      sums[index] += nums[i];
      pq.add(index);
    }
    return result;
  }





  public static void main(String[] args) {
    //new int[] {1,2,3,4,5,6,7,8,9,10}, 3

    List<List<Integer>> result = new PartitionToNSubsets().partition(new int[] {2,9,9,10,10}, 2);
    System.out.println(result);

  }
}
