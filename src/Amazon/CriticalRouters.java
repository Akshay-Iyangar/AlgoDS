package Amazon;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which,
 * when removed along with associated edges, makes the graph disconnected (or more precisely, increases the number of
 * connected components in the graph). The task is to find all articulation points in the given graph.
 *
 * Input:
 * The input to the function/method consists of three arguments:
 *
 * numNodes, an integer representing the number of nodes in the graph.
 * numEdges, an integer representing the number of edges in the graph.
 * edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
 * Output:
 * Return a list of integers representing the critical nodes.
 *
 * Example:
 *
 * Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3, 4]]
 *
 * Output: [2, 3, 5]
 *
 *
 *
 *
 * /**
 *  * Tarjan's algorithm
 *  *
 *  *
 *   steps
 *   1. Simply do a DFS and maintain
 *    - current time for the vertex
 *    - low time for the vertex.
 *    - Parent of the current vertex
 *
 *   2. Articulation point is a point which dissconnects the graph into two.
 *     i) Vertex is a root of the graph and has 2 independent children.(By independent it means they are
 *      not connected to each other except via this vertex). This condition is needed because if we
 *      started from corner vertex it will meet condition ii) but still is not an articulation point. To filter
 *      out those vertices we need this condition.
 *     ii) It is not root of DFS and if visitedTime of vertex <= lowTime of any adjacent vertex then its
 *  articulation point.
 *
 *
 *
 *  There is a similar sum to find the critical connections which is to find the edges that are critical.
 *  in that case i) won't be required.
 *  https://leetcode.com/discuss/interview-question/372581
 *  https://leetcode.com/problems/critical-connections-in-a-network
 *
 *
 *
 *  */

public class CriticalRouters {
  int time=0;
  public List<Integer> criticalConnections(int n, int[][] connections) {

    //prepare a graph (Adjacency List)
    List<List<Integer>> graph = new ArrayList<>();
    for(int i=0;i<=n;i++)
      graph.add(new ArrayList<>());

    //undirected so have it both ways.
    for(int[] conn : connections){
      graph.get(conn[0]).add(conn[1]);
      graph.get(conn[1]).add(conn[0]);
    }

    //System.out.println(graph);
    Set<Integer> result = new HashSet<>();
    // graph, visited, current_time, low_time, current, parent, result.
    int[] current_time = new int[n+1];
    int[] low_time = new int[n+1];
    boolean[] visited = new boolean[n+1];
    int[] parent = new int[n+1];

    for (int i = 1; i <= n; i++) {
      if (!visited[i]) {
        dfs(graph,visited,current_time,low_time,parent,i,0,result);
        //System.out.println(Arrays.toString(visited));
        //System.out.println(result);
      }
    }
    return new ArrayList<>(result);
  }

  private void dfs(List<List<Integer>> graph,
                   boolean[] visited,
                   int[] current_time,
                   int[] low_time,
                   int[] parent,
                   int current,
                   int previous,
                   Set<Integer> result){
    //when we have to find the critical edges and not nodes no need of checking condition 1.

    //mark the node visited
    visited[current]=true;
    current_time[current]=time;
    low_time[current]=time;
    time++;
    int childCount =0;
    boolean isArticulationPoint = false;
    for(int i=0; i<graph.get(current).size();i++){

      int adjacent = graph.get(current).get(i);

      if(previous == adjacent) continue;

      if(!visited[adjacent]){
        parent[adjacent] = current;
        childCount++;
        dfs(graph, visited, current_time, low_time, parent,adjacent, current,result);
        //System.out.println("current time " + current_time[current] + " low time " + low_time[adjacent] + " The current is "+current+ " The adjacent is "+adjacent+" parent of current is "+parent[current]);
        if(current_time[current] < low_time[adjacent]){
          isArticulationPoint = true;
          /**
           * Below code is only used when finding the edge.
           */
//          List<Integer> list= new ArrayList<>();
//          list.add(current);
//          list.add(adjacent);
//          result.add(list);
        }

        else{
          //lowTime[current] = min(lowTime[current], lowTime[adj]);
          low_time[current] = Math.min(low_time[current], low_time[adjacent]);
        }
      }
      else { //if adj is already visited see if you can get better low time.
        low_time[current] = Math.min(low_time[current], low_time[adjacent]);
      }

      //This code will not be needed when finding the edge.
      //checks if either condition 1 or condition 2 meets). If yes then it is articulation point.
      if(parent[current]==0 && childCount>=2 || parent[current]!=0 && isArticulationPoint){
        result.add(current);
      }
    }
  }




  public static void main(String[] args) {

    CriticalRouters criticalRouters = new CriticalRouters();
    int[][] connections = {{2, 3}, {1, 2}, {3, 4}, {6, 3}, {4, 5}};
    List<Integer> result = criticalRouters.criticalConnections(6, connections);
    System.out.println(result);
  }
}
