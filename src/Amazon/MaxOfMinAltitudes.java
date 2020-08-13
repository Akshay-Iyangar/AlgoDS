package Amazon;

/**
 * Solution:
 * Math.max(Math.min(dp[i][j],dp[i][j-1]) , Math.min(dp[i][j],dp[i-1][j])
 *
 *
 * Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1,
 * c-1]. The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.
 *
 * Don't include the first or final entry. You can only move either down or right at any point in time.
 *
 * Example 1:
 *
 * Input:
 * [[5, 1],
 *  [4, 5]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 5 → 1 → 5 => min value is 1
 * 5 → 4 → 5 => min value is 4
 * Return the max value among minimum values => max(4, 1) = 4.
 * Example 2:
 *
 * Input:
 * [[1, 2, 3]
 *  [4, 5, 1]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 1-> 2 -> 3 -> 1
 * 1-> 2 -> 5 -> 1
 * 1-> 4 -> 5 -> 1
 * So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
 * Return the max of that, so 4.
 */
public class MaxOfMinAltitudes {


  //ignore first and last element
  //Math.max(Math.min(dp[i][j],dp[i][j-1]) , Math.min(dp[i][j],dp[i-1][j])
  private static int calculateAltitude(int[][] matrix){

    int rowMin = Integer.MAX_VALUE;
    for(int i=1;i<matrix.length;i++){
      rowMin = Math.min(matrix[i][0], rowMin);
      matrix[i][0] = rowMin;
    }

    int colMin = Integer.MAX_VALUE;
    for(int i=1;i<matrix[0].length;i++){
      colMin = Math.min(matrix[0][i], colMin);
      matrix[0][i] = colMin;
    }

    int N = matrix.length-1;
    int M = matrix[0].length-1;
    int result=0;
    for (int i=1;i<matrix.length;i++){
      for (int j=1;j<matrix[0].length;j++){
        if(i==N && j==M) result = Math.max(matrix[i-1][j],matrix[i][j-1]);
        matrix[i][j] = Math.max(Math.min(matrix[i][j],matrix[i][j-1]) , Math.min(matrix[i][j],matrix[i-1][j]));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] matrix = {{1,2,3},{4,5,1}};
    int result = calculateAltitude(matrix);
    System.out.println(result);
  }

}
