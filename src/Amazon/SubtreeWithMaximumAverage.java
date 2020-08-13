package Amazon;

import java.util.List;

/**
 * Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
 * A subtree of a tree is the node which have at least 1 child plus all its descendants. The average value of a
 * subtree is the sum of its values, divided by the number of nodes.
 *
 * Example 1:
 *
 * Input:
 * 		      20
 * 	     /      \
 * 	   12       18
 *   /  |  \   / \
 * 11   2   3 15  8
 *
 * Output: 18
 * Explanation:
 * There are 3 nodes which have children in this tree:
 * 12 => (11 + 2 + 3 + 12) / 4 = 7
 * 18 => (18 + 15 + 8) / 3 = 13.67
 * 20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125
 *
 * 18 has the maximum average so output 18.
 */

public class SubtreeWithMaximumAverage {
  double max = Integer.MIN_VALUE;
  TreeNode maxNode = null;
  public static void main(String[] args) {

  }

  // one child and all it's children
  private TreeNode calculateAverage(TreeNode root) {
    if (root==null) return null;
    calculateAverageHelper(root);
    return maxNode;
  }


  private double[] calculateAverageHelper(TreeNode root){

    if(root == null) return new double[]{0,0};
    double curTotal = root.val;
    double count = 1;
    for (TreeNode child : root.children) {
      double[] cur = calculateAverageHelper(child);
      curTotal += cur[0];
      count += cur[1];
    }
    double avg = curTotal / count;
    if (count > 1 && avg > max) { //taking "at least 1 child" into account
      max = avg;
      maxNode = root;
    }
    return new double[] {curTotal, count};
  }


  class TreeNode {
    int val;
    List<TreeNode> children;

    public TreeNode() {
    }
    public TreeNode(int val) {
      this.val = val;
    }
  }
}
