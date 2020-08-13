package Amazon;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken
 * disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible
 * from each other.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes connected by an edge.
 * edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is
 * currently broken and the cost of repearing that edge, respectively (e.g. [1, 2, 12] means to repear an edge
 * between nodes 1 and 2, the cost would be 12).
 * Example 1:
 *
 * Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
 * Output: 20
 * Explanation:
 * There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
 * We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1
 * and 5 at a minimum cost 12 + 8 = 20.
 * Example 2:
 *
 * Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
 * Output: 410
 * Example 3:
 *
 * Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2,
 * 4, 84], [3, 4, 79]]
 * Output: 79
 */

public class MinCostToRepairEdges {
  public static void main(String[] args) {

    //Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
    int[][] edges = {{1,2},{4,5},{2,3},{3,4},{1,5},{2,4},{5,6}};
    int[][] repairEdges = {{2,4,84},{3,4,79},{1,5,110}};
    int N = 6;

    // so use the existing edges and do union of all the available ones.
    //sort the newEdges based on it's cost.
    //keep adding the newEdges
    MinCostToRepairEdges minCostToRepairEdges = new MinCostToRepairEdges();
    int result = minCostToRepairEdges.calculateCost(edges, repairEdges, N);
    System.out.println(result);

  }

  private int calculateCost(int[][] edges, int[][] repairEdges, int N){

    Set<String> set = new HashSet<>();
    for (int[] repairEdge : repairEdges) {
      set.add(Arrays.toString(Arrays.copyOfRange(repairEdge,0,2)));
    }
    //System.out.println(set);

    DisjointSet disjointSet = new DisjointSet(N);
    //existing edges and do union of all the available ones.
    // everything that doesn't exist in the repair is good to go. to add them as good edges.
    for (int[] edge : edges) {
      if (!set.contains(Arrays.toString(edge)))
        disjointSet.union(edge[0]-1,edge[1]-1);
    }
    //sort the repairEdges based on it's cost.
    Arrays.sort(repairEdges, (e1, e2) -> e1[2]-e2[2]);

    //keep adding the newEdges with minimal cost;
    int minCost=0;
    for (int[] repairEdge : repairEdges) {
      if(disjointSet.find(repairEdge[0]-1) != disjointSet.find(repairEdge[1]-1)){
        disjointSet.union(repairEdge[0]-1,repairEdge[1]-1);
        minCost += repairEdge[2];
      }
    }
    return (disjointSet.getCount()==N-1) ? minCost : -1;
  }

  class DisjointSet {

    int[] parents;
    int[] ranks;
    int count;
    DisjointSet(int N){
      parents = new int[N];
      ranks = new int[N];
      count=0;
    }

    int getCount(){return count;}

    void union(int x, int y){
      int parentX = find(x);
      int parentY = find(y);
      if (ranks[parentX] > ranks[parentY]){
        parents[parentY] = parentX;
        ranks[parentX]++;
      } else {
        parents[parentX] = parentY;
        ranks[parentY]++;
      }
      count++;
    }

    int find(int x){
      if(parents[x]==0) return x;
      return parents[x] = find(parents[x]); //path compression.
    }
  }
}
