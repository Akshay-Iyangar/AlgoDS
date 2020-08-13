package Amazon;

import java.util.Arrays;

/**
 *
 * Given an undirected graph with n nodes labeled 1..n. Some of the nodes are already connected. The i-th edge
 * connects nodes edges[i][0] and edges[i][1] together. Your task is to augment this set of edges with additional
 * edges to connect all the nodes. Find the minimum cost to add new edges between the nodes such that all the nodes
 * are accessible from each other.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes already connected by an edge.
 * newEdges, a list where each element is a triplet representing the pair of nodes between which an edge can be added
 * and the cost of addition, respectively (e.g. [1, 2, 5] means to add an edge between node 1 and 2, the cost would
 * be 5).
 * Example 1:
 *
 * Input: n = 6, edges = [[1, 4], [4, 5], [2, 3]], newEdges = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
 * Output: 7
 * Explanation:
 * There are 3 connected components [1, 4, 5], [2, 3] and [6].
 * We can connect these components into a single component by connecting node 1 to node 2 and node 1 to node 6 at a
 * minimum cost of 5 + 2 = 7.
 *
 */
public class MinCostToConnectAllNodes {

  public static void main(String[] args) {

    int[][] edges = {{1,4},{4,5},{2,3}};
    int[][] newEdges = {{1,2,5},{1,3,10},{1,6,2},{5,6,5}};
    int N = 6;

    // so use the existing edges and do union of all the available ones.
    //sort the newEdges based on it's cost.
    //keep adding the newEdges
    MinCostToConnectAllNodes minCostToConnectAllNodes = new MinCostToConnectAllNodes();
    int result = minCostToConnectAllNodes.calculateCost(edges, newEdges, N);
    System.out.println(result);

  }

  private int calculateCost(int[][] edges, int[][] newEdges, int N){
    DisjointSet disjointSet = new DisjointSet(N);
    //existing edges and do union of all the available ones.
    for (int[] edge : edges) {
      disjointSet.union(edge[0]-1,edge[1]-1);
    }
    //sort the newEdges based on it's cost.
    Arrays.sort(newEdges, (e1,e2) -> e1[2]-e2[2]);

    //keep adding the newEdges with minimal cost;
    int minCost=0;
    for (int[] newEdge : newEdges) {
      if(disjointSet.find(newEdge[0]-1) != disjointSet.find(newEdge[1]-1)){
        disjointSet.union(newEdge[0]-1,newEdge[1]-1);
        minCost += newEdge[2];
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


